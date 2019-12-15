package org.fkit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.mapping.FetchType;
import org.fkit.domain.Saler;

public interface SalerMapper {
	
	@Insert("insert into tb_saler(password) values(#{password})")
	int insertWithPassword(@Param("password")String password);
	
	@Select("select * from tb_saler where id=#{id} and password=#{password}")
	Saler findWithIdAndPassword(@Param("id")Integer id,@Param("password")String password);

}
