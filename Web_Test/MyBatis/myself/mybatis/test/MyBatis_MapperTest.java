package myself.mybatis.test;

import java.io.InputStream;
import java.util.List;

import myself.mybatis.learning.common.interceptor.Page;
import myself.mybatis.learning.domain.User;
import myself.mybatis.learning.mapper.UserMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class MyBatis_MapperTest {

	private SqlSessionFactory sessionFactory = null;

	@Before
	public void before() {
		String resource = "conf.xml";
		InputStream is = MyBatis_Test.class.getClassLoader()
				.getResourceAsStream(resource);
		sessionFactory = new SqlSessionFactoryBuilder().build(is);
	}

	@Test
	public void getAllUsers() {
//		SqlSession session = sessionFactory.openSession();
//
//		UserMapper userMapper = session.getMapper(UserMapper.class);
//		Page<User> page = new Page<User>();
//		page.setSortColumn("name");
//		page.setSort("desc");
//		List<User> users = userMapper.findUserPage1(27, page);
//		session.close();
//		System.out.println(users);
	}
	
	@Test
	public void getUser() {
//		SqlSession session = sessionFactory.openSession();
//
//		UserMapper userMapper = session.getMapper(UserMapper.class);
//		Map<String, Object> params = new HashMap<String, Object>();
//		Page<User> page = new Page<User>();
//		page.setSortColumn("name");
//		page.setSort("desc");
//		params.put("page", page);
//		List<User> user = userMapper.findUser(params);
//		session.close();
//		System.out.println(user);
	}
	
	@Test
	public void likeTest() {
//		SqlSession session = sessionFactory.openSession();
//		
//		UserMapper userMapper = session.getMapper(UserMapper.class);
//		List<User> user = userMapper.findLike("孤傲");
//		session.close();
//		System.out.println(user);
	}
	
	@Test
	public void getPageTest() {
		SqlSession session = sessionFactory.openSession();
		
		UserMapper userMapper = session.getMapper(UserMapper.class);
		Page<User> page = new Page<User>();
		User user = new User();
		user.setPhonenum("1");
		page.setT(user);
		page.setSort("desc");
		page.setSortColumn("us.user_id");
		List<User> users = userMapper.getUserPager(page);
		session.close();
		System.out.println(users);
	}
}
