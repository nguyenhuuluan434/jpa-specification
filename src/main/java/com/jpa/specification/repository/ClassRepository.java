package com.jpa.specification.repository;

import com.jpa.specification.repository.domain.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long>, JpaSpecificationExecutor {
    List<Class> findAllByNameContainsIgnoreCase(String pattern);
}
