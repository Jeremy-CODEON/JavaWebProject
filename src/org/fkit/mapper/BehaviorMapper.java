package org.fkit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.fkit.domain.Behavior;
import org.apache.ibatis.annotations.One;

public interface BehaviorMapper {
	
	@Insert("insert into tb_behavior(user_id,music_id,behavior,year,month,day) values (#{user_id},#{music_id},#{behavior},#{year},#{month},#{day})")
	int insertBehavior(@Param("user_id")Integer user_id, @Param("music_id")Integer music_id, @Param("behavior")Integer behavior, @Param("year")Integer year, @Param("month")Integer month, @Param("day")Integer day);
	
	@Select("select * from tb_behavior where user_id=#{user_id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="user_id",property="user",
		one=@One(
				select="org.fkit.mapper.UserMapper.getById",
				fetchType=FetchType.LAZY)),
		@Result(column="music_id",property="music",
		one=@One(
				select="org.fkit.mapper.MusicMapper.getById",
				fetchType=FetchType.LAZY)),
		@Result(column="year", property="year"),
		@Result(column="month", property="month"),
		@Result(column="day", property="day")
	})
	List<Behavior> getByUserId(@Param("user_id")int user_id);
	
	@Select("select * from tb_behavior where music_id=#{music_id} and behavior=2 order by year asc, month asc")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="user_id",property="user",
		one=@One(
				select="org.fkit.mapper.UserMapper.getById",
				fetchType=FetchType.LAZY)),
		@Result(column="music_id",property="music",
		one=@One(
				select="org.fkit.mapper.MusicMapper.getById",
				fetchType=FetchType.LAZY)),
		@Result(column="year", property="year"),
		@Result(column="month", property="month"),
		@Result(column="day", property="day")
	})
	List<Behavior> getByMusicId(@Param("music_id")int music_id);
	
	@Select("select * from tb_behavior")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="user_id",property="user",
		one=@One(
				select="org.fkit.mapper.UserMapper.getById",
				fetchType=FetchType.LAZY)),
		@Result(column="music_id",property="music",
		one=@One(
				select="org.fkit.mapper.MusicMapper.getById",
				fetchType=FetchType.LAZY)),
		@Result(column="year", property="year"),
		@Result(column="month", property="month"),
		@Result(column="day", property="day")
	})
	List<Behavior> getAllBehavior();

}
