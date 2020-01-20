package com.jpa.specification;

import com.jpa.specification.repository.ClassRepository;
import com.jpa.specification.repository.MemberRepository;
import com.jpa.specification.repository.domain.Class;
import com.jpa.specification.repository.domain.Member;
import com.jpa.specification.specification.MemberSpecification;
import com.jpa.specification.web.model.FilterRequest;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JpaSpecificationApplicationTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private MemberSpecification memberSpecification;

    @BeforeEach
    public void init(){
        memberRepository.deleteAll();
        classRepository.deleteAll();

        Class classWaterPolo = new Class();
        classWaterPolo.setName("Water Polo");
        classRepository.save(classWaterPolo);

        Class classSwimming = new Class();
        classSwimming.setName("Swimming");
        classRepository.save(classSwimming);

        Class classLifting = new Class();
        classLifting.setName("Lifting");
        classRepository.save(classLifting);

        Class classPilates = new Class();
        classPilates.setName("Pilates");
        classRepository.save(classPilates);

        Class classZumba = new Class();
        classZumba.setName("Zumba");
        classRepository.save(classZumba);

        Set<Class> gregSet = new HashSet<>();
        gregSet.add(classWaterPolo);
        gregSet.add(classLifting);

        Member memberGreg = new Member();
        memberGreg.setActive(true);
        memberGreg.setFirstName("Greg");
        memberGreg.setLastName("Brady");
        memberGreg.setInterests("I love to cycle and swim");
        memberGreg.setZipCode("90210");
        memberGreg.setClasses(gregSet);
        memberRepository.save(memberGreg);

        Set<Class> marshaSet = new HashSet<>();
        marshaSet.add(classSwimming);
        marshaSet.add(classZumba);

        Member memberMarsha = new Member();
        memberMarsha.setActive(true);
        memberMarsha.setFirstName("Marsha");
        memberMarsha.setLastName("Brady");
        memberMarsha.setInterests("I love to do zumba and pilates");
        memberMarsha.setZipCode("90211");
        memberMarsha.setClasses(marshaSet);
        memberRepository.save(memberMarsha);

        Set<Class> aliceSet = new HashSet<>();
        aliceSet.add(classSwimming);

        Member memberAlice = new Member();
        memberAlice.setActive(false);
        memberAlice.setFirstName("Alice");
        memberAlice.setLastName("Nelson");
        memberAlice.setInterests("I used to love that belt machine-y thing");
        memberAlice.setZipCode("90201");
        memberAlice.setClasses(aliceSet);
        memberRepository.save(memberAlice);

    }
    @Test
    public void testMembersActive() {
        FilterRequest filter = new FilterRequest();
        filter.setActive(true);

        List<Member> memberList = memberRepository.findAll(memberSpecification.getFilter(filter));

        assertEquals(2, memberList.size());
    }




}
