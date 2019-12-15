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
import org.fkit.domain.User;

public interface UserMapper {
	@Select("select * from tb_user where loginname=#{loginname} and password=#{password}")
	@Results({
		@Result(id=true, column="id",property="id"),
		@Result(column="loginname", property="loginname"),
		@Result(column="password", property="password"),
		@Result(column="username", property="username"),
		@Result(column="phone", property="phone"),
		@Result(column="mail", property="mail"),
		//一对多关系，用懒加载lazy
		@Result(column="id", property="cartList",
		many=@Many(
				select="org.fkit.mapper.CartMapper.getByUserId",fetchType=FetchType.LAZY))
	})
	User findWithLoginnameAndPassword(@Param("loginname")String loginname, @Param("password") String password);
	
	@Insert("insert into tb_user(loginname,password) values (#{loginname}, #{password})")
	int insertWithLoginnameAndPassword(@Param("loginname")String loginname, @Param("password") String password);
	
	@Select("select * from tb_user where loginname=#{loginname}")
	@Results({
		@Result(id=true, column="id",property="id"),
		@Result(column="loginname", property="loginname"),
		@Result(column="password", property="password"),
		@Result(column="username", property="username"),
		@Result(column="phone", property="phone"),
		@Result(column="mail", property="mail"),
		//一对多关系，用懒加载lazy
		@Result(column="id", property="cartList",
		many=@Many(
				select="org.fkit.mapper.CartMapper.getByUserId",fetchType=FetchType.LAZY))
	})
	User checkWithLoginname(@Param("loginname")String loginname);
	
	@Update("update tb_user set loginname=#{loginname} where id=#{id}")
	int updateLoginname(@Param("loginname")String loginname,@Param("id")Integer id);
	
	@Update("update tb_user set username=#{username} where id=#{id}")
	int updateUsername(@Param("username")String username,@Param("id")Integer id);
	
	@Update("update tb_user set phone=#{phone} where id=#{id}")
	int updatePhone(@Param("phone")String phone,@Param("id")Integer id);
	
	@Update("update tb_user set mail=#{mail} where id=#{id}")
	int updateMail(@Param("mail")String mail,@Param("id")Integer id);
	
	@Update("update tb_user set password=#{password} where id=#{id}")
	int updatePassword(@Param("password")String password,@Param("id")Integer id);
	
	@Select("select * from tb_user where id=#{id}")
	@Results({
		@Result(id=true, column="id",property="id"),
		@Result(column="loginname", property="loginname"),
		@Result(column="password", property="password"),
		@Result(column="username", property="username"),
		@Result(column="phone", property="phone"),
		@Result(column="mail", property="mail"),
		//一对多关系，用懒加载lazy
		@Result(column="cart_id", property="cartList",
		many=@Many(
				select="org.fkit.mapper.CartMapper.getByUserId",fetchType=FetchType.LAZY))
	})
	User getById(@Param("id")Integer id);
	
	@Update("update tb_user set avatar=#{avatar} where id=#{id}")
	int updateAvatar(@Param("avatar")String avatar,@Param("id")Integer id);
	
}
