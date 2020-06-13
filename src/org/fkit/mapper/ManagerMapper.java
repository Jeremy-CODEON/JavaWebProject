package org.fkit.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.fkit.domain.Manager;

public interface ManagerMapper {
	
	@Select("select * from tb_manager where id=#{id} and password=#{password}")
	Manager findWithIdAndPassword(@Param("id")Integer id, @Param("password")String password);

}
