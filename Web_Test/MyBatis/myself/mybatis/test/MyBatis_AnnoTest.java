package myself.mybatis.test;

import java.io.InputStream;

import myself.mybatis.learning.domain.User_MySQL;
import myself.mybatis.learning.mapper.annotation.UserMapperAnno;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class MyBatis_AnnoTest {

	private SqlSessionFactory sessionFactory = null;

	@Before
	public void before() {
		String resource = "conf.xml";
		InputStream is = MyBatis_Test.class.getClassLoader()
				.getResourceAsStream(resource);
		sessionFactory = new SqlSessionFactoryBuilder().build(is);
	}

	@Test
	public void getById() {
		SqlSession session = sessionFactory.openSession();
		UserMapperAnno userMapper = session.getMapper(UserMapperAnno.class);
		User_MySQL user = userMapper.getById(2);
		session.close();
		System.out.println(user);
	}
}
