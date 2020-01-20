package com.jpa.specification.specification;

import org.springframework.data.jpa.domain.Specification;

public abstract class BaseSpecification<T, U> {
    abstract Specification<T> getFilter(U filterRequest);
}
