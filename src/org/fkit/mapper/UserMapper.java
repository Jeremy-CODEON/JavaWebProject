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
import org.fkit.domain.User;

public interface UserMapper {
	@Select("select * from tb_user where loginname=#{loginname} and password=#{password}")
	@Results({
		@Result(id=true, column="id",property="id"),
		@Result(column="loginname", property="loginname"),
		@Result(column="password", property="password"),
		@Result(column="username", property="username"),
		@Result(column="phone", property="phone"),
		@Result(column="age", property="age"),
		@Result(column="mail", property="mail"),
		//一对多关系，用懒加载lazy
		@Result(column="id", property="cartList",
		many=@Many(
				select="org.fkit.mapper.CartMapper.getByUserId",
				fetchType=FetchType.LAZY)),
		@Result(column="browsetime", property="browsetime"),
		@Result(column="logintime", property="logintime"),
		@Result(column="classification", property="classification"),
		@Result(column="mlmusic_id", property="mlmusic_id")
	})
	User findWithLoginnameAndPassword(@Param("loginname")String loginname, @Param("password") String password);
	
	@Insert("insert into tb_user(loginname,password,age,logintime,browsetime,classification,mlmusic_id) values (#{loginname},#{password},#{age},0,0,0,#{mlmusic_id})")
	int insertForRegister(@Param("loginname")String loginname, @Param("password") String password, @Param("age")Integer age, @Param("mlmusic_id")Integer mlmusic_id);
	
	@Select("select * from tb_user where loginname=#{loginname}")
	@Results({
		@Result(id=true, column="id",property="id"),
		@Result(column="loginname", property="loginname"),
		@Result(column="password", property="password"),
		@Result(column="username", property="username"),
		@Result(column="phone", property="phone"),
		@Result(column="age", property="age"),
		@Result(column="mail", property="mail"),
		//一对多关系，用懒加载lazy
		@Result(column="id", property="cartList",
		many=@Many(
				select="org.fkit.mapper.CartMapper.getByUserId",
				fetchType=FetchType.LAZY)),
		@Result(column="browsetime", property="browsetime"),
		@Result(column="logintime", property="logintime"),
		@Result(column="classification", property="classification"),
		@Result(column="mlmusic_id", property="mlmusic_id")
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
		@Result(column="age", property="age"),
		@Result(column="mail", property="mail"),
		//一对多关系，用懒加载lazy
		@Result(column="id", property="cartList",
		many=@Many(
				select="org.fkit.mapper.CartMapper.getByUserId",
				fetchType=FetchType.LAZY)),
		@Result(column="browsetime", property="browsetime"),
		@Result(column="logintime", property="logintime"),
		@Result(column="classification", property="classification"),
		@Result(column="mlmusic_id", property="mlmusic_id")
	})
	User getById(@Param("id")Integer id);
	
	@Select("select * from tb_user where classification=#{classification}")
	@Results({
		@Result(id=true, column="id",property="id"),
		@Result(column="loginname", property="loginname"),
		@Result(column="password", property="password"),
		@Result(column="username", property="username"),
		@Result(column="phone", property="phone"),
		@Result(column="age", property="age"),
		@Result(column="mail", property="mail"),
		//一对多关系，用懒加载lazy
		@Result(column="id", property="cartList",
		many=@Many(
				select="org.fkit.mapper.CartMapper.getByUserId",
				fetchType=FetchType.LAZY)),
		@Result(column="browsetime", property="browsetime"),
		@Result(column="logintime", property="logintime"),
		@Result(column="classification", property="classification"),
		@Result(column="mlmusic_id", property="mlmusic_id")
	})
	List<User> getByClassification(@Param("classification")Integer classification);
	
	@Select("select * from tb_user")
	@Results({
		@Result(id=true, column="id",property="id"),
		@Result(column="loginname", property="loginname"),
		@Result(column="password", property="password"),
		@Result(column="username", property="username"),
		@Result(column="phone", property="phone"),
		@Result(column="age", property="age"),
		@Result(column="mail", property="mail"),
		//一对多关系，用懒加载lazy
		@Result(column="id", property="cartList",
		many=@Many(
				select="org.fkit.mapper.CartMapper.getByUserId",
				fetchType=FetchType.LAZY)),
		@Result(column="browsetime", property="browsetime"),
		@Result(column="logintime", property="logintime"),
		@Result(column="classification", property="classification"),
		@Result(column="mlmusic_id", property="mlmusic_id")
	})
	List<User> getAllUser();
	
	@Update("update tb_user set age=#{age} where id={id}")
	int updateAge(@Param("age")Integer age, @Param("id")Integer id);
	
	@Update("update tb_user set avatar=#{avatar} where id=#{id}")
	int updateAvatar(@Param("avatar")String avatar,@Param("id")Integer id);
	
	@Update("update tb_user set classification=#{classification} where id=#{id}")
	int updateClassification(@Param("classification")Integer classification, @Param("id")Integer id);
	
	@Update("update tb_user set logintime=#{logintime} where id=#{id}")
	int updateLogintime(@Param("logintime")Integer logintime, @Param("id")Integer id);
	
	@Update("update tb_user set browsetime=#{browsetime} where id=#{id}")
	int updateBrowsetime(@Param("browsetime")Double browsetime, @Param("id")Integer id);
	
	@Update("update tb_user set mlmusic_id=#{mlmusic_id} where id=#{id}")
	int updateMlmusicId(@Param("mlmusic_id")Integer mlmusic_id, @Param("id")Integer id);
	
}
