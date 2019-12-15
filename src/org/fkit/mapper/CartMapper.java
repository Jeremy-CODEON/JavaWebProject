package org.fkit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.mapping.FetchType;
import org.fkit.domain.Cart;

public interface CartMapper {

	@Select("select * from tb_cart")
	List<Cart> findAll();

	@Select("select * from tb_cart where user_id=#{user_id}")
	@Results({ @Result(id = true, column = "id", property = "id"),
			@Result(column = "user_id", property = "user", one = @One(select = "org.fkit.mapper.UserMapper.getById", fetchType = FetchType.EAGER)),
			@Result(column = "music_id", property = "music", one = @One(select = "org.fkit.mapper.MusicMapper.getById", fetchType = FetchType.EAGER)) })
	List<Cart> getByUserId(@Param("user_id") int user_id);
	
	@Select("select * from tb_cart where user_id=#{user_id} and music_id=#{music_id}")
	@Results({ @Result(id = true, column = "id", property = "id"),
			@Result(column = "user_id", property = "user", one = @One(select = "org.fkit.mapper.UserMapper.getById", fetchType = FetchType.EAGER)),
			@Result(column = "music_id", property = "music", one = @One(select = "org.fkit.mapper.MusicMapper.getById", fetchType = FetchType.EAGER)) })
	List<Cart> getByUserIdAndMusicId(@Param("user_id") int user_id,@Param("music_id")int music_id);

	@Insert("insert into tb_cart(user_id, music_id) values(#{user_id},#{music_id})")
	int insertWithUserIdAndMusicId(@Param("user_id") int user_id, @Param("music_id") int music_id);

	@Delete("delete from tb_cart where user_id=#{user_id} and music_id=#{music_id}")
	int deleteByUserIdAndMusicId(@Param("user_id") int user_id, @Param("music_id") int music_id);
	
	@Delete("delete from tb_cart where id=#{id}")
	int deleteById(@Param("id") int id);

}
