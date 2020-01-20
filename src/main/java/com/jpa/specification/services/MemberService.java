package com.jpa.specification.services;

import com.jpa.specification.repository.domain.Member;
import com.jpa.specification.web.model.FilterRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MemberService {
    List<Member> getMembers(FilterRequest filterRequest, String searchString) throws Exception;
}
