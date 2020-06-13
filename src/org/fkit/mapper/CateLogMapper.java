package org.fkit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.fkit.domain.CateLog;

public interface CateLogMapper {
	
	@Insert("insert into tb_catelog(user_id,category,message,time) values (#{user_id},#{category},#{message},#{time})")
	int insertLog(@Param("user_id") int user_id, @Param("category")String category, @Param("message")String message, @Param("time")String time);

	@Select("select * from tb_catelog")
	List<CateLog> getAllLog();
	
	@Select("select * from tb_catelog where category=#{category}")
	List<CateLog> getByCategory(@Param("category")String category);
	
}
