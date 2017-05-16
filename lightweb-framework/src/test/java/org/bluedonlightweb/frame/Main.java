package org.bluedonlightweb.frame;

import org.bluedon.lightweb.frame.service.impl.SysUserServiceImpl;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:D:\\workspace2\\lightweb\\lightweb-web\\src\\main\\resources\\spring.xml"})
@Transactional
public class Main {
	Logger log = LoggerFactory.getLogger(Main.class);
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	SysUserServiceImpl userService;
	
	@Test
	public void findUser(){
//		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery("from User");
//		List<User> list = query.list();
//		System.out.println(list);
		
	}

}
