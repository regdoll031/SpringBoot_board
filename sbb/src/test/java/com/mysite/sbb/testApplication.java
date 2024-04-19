package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.user1.TestRepository;
import com.mysite.sbb.user1.TextEntity;


@SpringBootTest
class testApplication {

    @Autowired
    private TestRepository testRepository;

    @Test
    void testJpa() {        
    	TextEntity q1 = new TextEntity();
        q1.setName("홍길동1");
        this.testRepository.save(q1);  // 첫번째 질문 저장

        TextEntity q2 = new TextEntity();
        q2.setName("홍길동1");
        this.testRepository.save(q2);  // 첫번째 질문 저장
    }
}