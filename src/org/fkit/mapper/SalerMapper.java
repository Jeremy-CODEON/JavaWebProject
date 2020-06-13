package org.fkit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
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
	
	//����������
	@Insert("insert into tb_saler(password,sale_category) values(#{password},#{sale_category})")
	int insertWithPassword(@Param("password")String password, @Param("sale_category")String sale_category);
	
	//��������
	@Update("update tb_saler set password=#{password} where id=#{id}")
	int updatePassword(@Param("password")String password, @Param("id")Integer id);
	
	//��id��ȡ
	@Select("select * from tb_saler where id=#{id}")
	Saler findWithId(@Param("id")Integer id);
	
	@Select("select * from tb_saler where id=#{id} and password=#{password}")
	Saler findWithIdAndPassword(@Param("id")Integer id,@Param("password")String password);
	
	//ɾ������
	@Delete("delete from tb_saler where id=#{id}")
	int deleteById(@Param("id")int id);
	
	@Select("select * from tb_saler")
	List<Saler> getAllSaler();
}
