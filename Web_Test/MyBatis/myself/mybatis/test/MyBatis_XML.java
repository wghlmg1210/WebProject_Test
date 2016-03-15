package myself.mybatis.test;

import myself.mybatis.learning.domain.Blog;
import myself.mybatis.learning.mapper.BlogMapper;
import myself.mybatis.learning.util.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

public class MyBatis_XML {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void initByXML() {
		String resourcePath = "mybatis-config.xml";
		try {
			sqlSessionFactory = MyBatisUtil.getFactoryByResource(resourcePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void executeByMapperXML() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Blog blog = (Blog) session.selectOne(
					"myself.mybatis.learning.sql.BlogMapper.selectBlog", 101);
			System.out.println(blog);
		} finally {
			session.close();
		}
	}

	@Test
	public void executeByMapperInterface() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			BlogMapper mapper = session.getMapper(BlogMapper.class);
			Blog blog = mapper.selectBlog(101);
			System.out.println(blog);
		} finally {
			session.close();
		}
	}

}
