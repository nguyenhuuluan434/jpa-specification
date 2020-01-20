package com.jpa.specification.specification;

import com.jpa.specification.repository.ClassRepository;
import com.jpa.specification.repository.domain.Class;
import com.jpa.specification.repository.domain.Member;
import com.jpa.specification.web.model.FilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.SetJoin;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class MemberSpecification extends BaseSpecification<Member, FilterRequest> implements Serializable {

    @Qualifier("classRepository")
    @Autowired
    private ClassRepository classRepository;

    @Override
    public Specification<Member> getFilter(FilterRequest filterRequest) {
        return (root, criteriaQuery, criteriaBuilder) ->
        {
            criteriaQuery.distinct(true);
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get("lastName")));
            return where(isActive(filterRequest.getActive()).and(inZipCode(filterRequest.getZipCodePattern()))).toPredicate(root, criteriaQuery, criteriaBuilder);
        };
    }

    private Specification<Member> isActive(Boolean isActive) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            if (Objects.nonNull(isActive))
                return criteriaBuilder.equal(root.get("active"), isActive);
            else
                return null;
        });
    }

    private Specification<Member> inZipCode(String zipCodePattern) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            if (Objects.nonNull(zipCodePattern) && !zipCodePattern.isEmpty())
                return criteriaBuilder.like(root.get("zipCode"), criteriaBuilder.literal(zipCodePattern + "%"));
            else
                return null;
        });
    }

    public Specification<Member> hasString(String searchString) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.distinct(true);
            if (Objects.nonNull(searchString) && !searchString.isEmpty())
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("interests")),
                        criteriaBuilder.lower(criteriaBuilder.lower(criteriaBuilder.literal("%" + searchString + "%"))));
            else
                return null;
        };
    }


    public Specification<Member> hasClasses(String searchString) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.distinct(true);
            if (Objects.isNull(searchString) || !searchString.isEmpty())
                return null;
            List<Class> classes = classRepository.findAllByNameContainsIgnoreCase(searchString);
            if (Objects.isNull(classes) || classes.isEmpty())
                return null;
            SetJoin<Member, Class> joinCriteria = root.joinSet("classes", JoinType.LEFT);
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(joinCriteria.in(new HashSet<>(classes)));
            Predicate[] p = predicates.toArray(new Predicate[predicates.size()]);
            return criteriaBuilder.or(p);
        };
    }

}
