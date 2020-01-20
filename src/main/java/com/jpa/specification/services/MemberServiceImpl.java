package com.jpa.specification.services;

import com.jpa.specification.repository.MemberRepository;
import com.jpa.specification.repository.domain.Member;
import com.jpa.specification.specification.MemberSpecification;
import com.jpa.specification.web.model.FilterRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    private MemberSpecification memberSpecification;

    public MemberServiceImpl(MemberRepository memberRepository, MemberSpecification memberSpecification) {
        this.memberRepository = memberRepository;
        this.memberSpecification = memberSpecification;
    }

    @Override
    public List<Member> getMembers(FilterRequest filterRequest, String searchString) throws Exception {
        try {
            return memberRepository.findAll(Specification.where(memberSpecification.hasString(searchString))
                    .or(memberSpecification.hasClasses(searchString))
                    .and(memberSpecification.getFilter(filterRequest)));
        } catch (Throwable t) {
            throw new Exception("could not find member", t);
        }
    }
}
