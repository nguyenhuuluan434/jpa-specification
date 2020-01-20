package com.jpa.specification.web;

import com.jpa.specification.repository.domain.Member;
import com.jpa.specification.services.MemberService;
import com.jpa.specification.web.model.FilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/members")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("")
    public ResponseEntity<List<Member>> getAllMembers(@RequestParam(required = false) String searchString, FilterRequest filter) throws Exception {
        return new ResponseEntity<>(memberService.getMembers(filter, searchString), HttpStatus.OK);
    }
}
