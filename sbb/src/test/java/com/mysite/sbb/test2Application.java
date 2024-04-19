package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.user2.Test2Entity;
import com.mysite.sbb.user2.Test2Repository;


@SpringBootTest
class test2Application {
	
	@Autowired
	private Test2Repository test2Repository;
	
	@Test
	void testJpa() {
		Test2Entity table1 = new Test2Entity();
		table1.setName("길길동");
		table1.setAge("1살");
		table1.setCity("광주광역시서구치평동1");
		this.test2Repository.save(table1);
	}

}
