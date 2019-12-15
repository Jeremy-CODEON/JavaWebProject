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
import org.fkit.domain.Music;
import org.fkit.domain.Order;
import org.fkit.domain.OrderMusic;
import org.fkit.domain.User;

public interface OrderMusicMapper {
	
	@Select("select * from tb_order_music where order_id=#{order_id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="order_id",property="order",
		one=@One(
				select="org.fkit.mapper.OrderMapper.getById",
				fetchType=FetchType.EAGER)),
		@Result(column="music_id",property="music",
		one=@One(
				select="org.fkit.mapper.MusicMapper.getById",
				fetchType=FetchType.EAGER))
	})
	List<OrderMusic> getByOrderId(@Param("order_id") int order_id);
	
	@Insert("insert into tb_order_music(order_id,music_id) values (#{order_id},#{music_id})")
	int insert(@Param("order_id")int order_id,@Param("music_id")int music_id);

}
