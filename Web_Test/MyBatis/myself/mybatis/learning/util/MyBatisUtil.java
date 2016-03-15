package myself.mybatis.learning.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * My_Batis工具类
 * 
 * @author Huang
 * 
 */
public class MyBatisUtil {

	private static SqlSessionFactory sqlSessionFactory;

	/**
	 * 加载配置文件，并初始化Session工厂
	 * 
	 * @param resourcePath
	 * @return
	 * @throws Exception
	 */
	public static SqlSessionFactory getFactoryByResource(String resourcePath)
			throws Exception {
		if (MyBatisUtil.sqlSessionFactory != null) {
			throw new Exception(
					"configuration is loaded, and the SqlSessionFactory is created");
		} else {
			Reader reader = Resources.getResourceAsReader(resourcePath);
			MyBatisUtil.sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(reader);
		}
		return MyBatisUtil.sqlSessionFactory;
	}

}
