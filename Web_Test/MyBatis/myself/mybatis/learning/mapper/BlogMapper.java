package myself.mybatis.learning.mapper;

import org.apache.ibatis.annotations.Select;

import myself.mybatis.learning.domain.Blog;

public interface BlogMapper {

	@Select("select * from blog")
	Blog selectBlog(int i);

}
