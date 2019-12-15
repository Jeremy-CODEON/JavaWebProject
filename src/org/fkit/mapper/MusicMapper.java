package org.fkit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.fkit.domain.Music;

public interface MusicMapper {
	@Select("select * from tb_music")
	List<Music> findAll();

	@Select("select * from tb_music where name like concat('%',#{input},'%') or singer like concat('%',#{input},'%') or description like concat('%',#{input},'%') or album like concat('%',#{input},'%')")
	List<Music> getByWord(@Param("input") String input);

	@Select("select * from tb_music where category=#{category}")
	List<Music> getByCategory(@Param("category") String category);

	@Select("select * from tb_music where id=#{id}")
	Music getById(@Param("id") int id);

	@Update("update tb_music set name=#{name} where id=#{id}")
	int updateName(@Param("name") String name, @Param("id") int id);

	@Update("update tb_music set singer=#{singer} where id=#{id}")
	int updateSinger(@Param("singer") String singer, @Param("id") int id);

	@Update("update tb_music set description=#{description} where id=#{id}")
	int updateDescription(@Param("description") String description, @Param("id") int id);

	@Update("update tb_music set album=#{album} where id=#{id}")
	int updateAlbum(@Param("album") String album, @Param("id") int id);

	@Update("update tb_music set duration=#{duration} where id=#{id}")
	int updateDuration(@Param("duration") String duration, @Param("id") int id);

	@Update("update tb_music set category=#{category} where id=#{id}")
	int updateCategory(@Param("category") String category, @Param("id") int id);

	@Update("update tb_music set cost=#{cost} where id=#{id}")
	int updateCost(@Param("cost") double cost, @Param("id") int id);

	@Update("update tb_music set state=#{state} where id=#{id}")
	int updateState(@Param("state") int state, @Param("id") int id);

	// 用于更新购买量
	@Update("update tb_music set purchases=purchases+1 where id=#{id}")
	int updatePurchasesById(@Param("id") int id);

	@Insert("insert into tb_music(name,singer,description,album,duration,category,cost,state,purchases) values(#{name},#{singer},#{description},#{album},#{duration},#{category},#{cost},#{state},0)")
	int insertMusic(@Param("name") String name, @Param("singer") String singer,
			@Param("description") String description, @Param("album") String album, @Param("duration") String duration,
			@Param("category") String category, @Param("cost") double cost, @Param("state") int state);
	
	@Delete("delete from tb_music where id=#{id}")
	int deleteById(@Param("id")int id);

}