package myself.mybatis.learning.mapper;

import java.util.List;
import java.util.Map;

import myself.mybatis.learning.common.interceptor.Page;
import myself.mybatis.learning.domain.User;
import myself.mybatis.learning.domain.User_MySQL;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

	List<User_MySQL> findUserPage(Page<User_MySQL> page);

	List<User_MySQL> findUserPage1(int age, Page<User_MySQL> page);
	
//	List<User> findUser(@Param(value="name") String o);
	
	List<User_MySQL> findUser(Map<String, Object> params);
	
	List<User_MySQL> findLike(@Param(value="name") String o);
	
	List<User> getUserPager(Page<User> page);
	
//	User findUser(@Param(value="name") String o);
	
}
