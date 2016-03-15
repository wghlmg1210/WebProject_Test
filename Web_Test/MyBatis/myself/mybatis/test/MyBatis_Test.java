package myself.mybatis.test;

import javax.sql.DataSource;

import myself.mybatis.learning.mapper.BlogMapper;
import myself.mybatis.learning.util.BlogDataSourceFactory;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

public class MyBatis_Test {

	@Test
	public void intiByClass() {
		DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development",
				transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		configuration.addMapper(BlogMapper.class);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(configuration);
		sqlSessionFactory.openSession().getMapper(BlogMapper.class);
		System.out.println(sqlSessionFactory);
	}

}
