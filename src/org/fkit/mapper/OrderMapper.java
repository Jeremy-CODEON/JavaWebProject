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
import org.fkit.domain.Order;
import org.fkit.domain.User;

public interface OrderMapper {

	@Select("select * from tb_order where user_id=#{user_id}")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "costsum", property = "costSum"),
			@Result(column = "state", property = "state"),
			@Result(column = "user_id", property = "user", one = @One(select = "org.fkit.mapper.UserMapper.getById", fetchType = FetchType.EAGER)),
			@Result(column = "id", property = "orderMusicList", many = @Many(select = "org.fkit.mapper.OrderMusicMapper.getByOrderId", fetchType = FetchType.LAZY)) })
	List<Order> getByUserId(@Param("user_id") int user_id);

	@Select("select * from tb_order where id=#{id}")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "costsum", property = "costSum"),
			@Result(column = "state", property = "state"),
			@Result(column = "user_id", property = "user", one = @One(select = "org.fkit.mapper.UserMapper.getById", fetchType = FetchType.EAGER)),
			@Result(column = "id", property = "orderMusicList", many = @Many(select = "org.fkit.mapper.OrderMusicMapper.getByOrderId", fetchType = FetchType.EAGER)) })
	Order getById(@Param("id") int id);

	// 创建订单，初始没有放进音乐的时候状态设置为0
	@Insert("insert into tb_order(state,costsum, user_id) values(0,0.0,#{user_id})")
	int insertWithUserId(@Param("user_id") int user_id);

	// 为订单添加音乐，同时将其状态置为1
	@Update("update tb_order set state=1,costsum=costsum+#{cost} where id=#{id}")
	void updateWithCost(@Param("cost") double cost, @Param("id") int id);

	// 获取未添加音乐的订单
	@Select("select * from tb_order where state=0")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "costsum", property = "costSum"),
			@Result(column = "state", property = "state") })
	Order getOrderWithStateZero();

	// 更新订单状态
	@Update("update tb_order set state=#{state} where id=#{id}")
	void updateStateWithId(@Param("state") int state, @Param("id") int id);

}
