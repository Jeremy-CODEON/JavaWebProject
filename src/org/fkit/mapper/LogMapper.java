package org.fkit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.mapping.FetchType;
import org.fkit.domain.Log;

public interface LogMapper {

	@Insert("insert into tb_log(user_id,message,time) values (#{user_id},#{message},#{time})")
	int insertLog(@Param("user_id") int user_id, @Param("message") String message, @Param("time") String time);
	
	@Select("select * from tb_log where user_id=#{user_id}")
	List<Log> getByUserId(@Param("user_id")int user_id);
	
	@Select("select * from tb_log")
	List<Log> getAllLog();
	
}
