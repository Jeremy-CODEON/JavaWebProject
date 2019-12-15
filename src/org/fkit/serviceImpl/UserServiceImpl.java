package org.fkit.serviceImpl;
import org.fkit.domain.User;
import org.fkit.mapper.UserMapper;
import org.fkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	@Override
	public User login(String loginname, String password)
	{
		return userMapper.findWithLoginnameAndPassword(loginname, password);
	}
	@Override
	public int register(String loginname, String password)
	{
		return userMapper.insertWithLoginnameAndPassword(loginname, password);
	}
	@Override
	public Boolean checkRegister(String loginname)
	{
		if(userMapper.checkWithLoginname(loginname)!=null)
		{
			//已经有同名昵称
			return false;
		}
		else
		{
			//没有同名的，可以注册
			return true;
		}
	}
	
	@Override
	public int updateLoginname(String loginname, Integer id)
	{
		return userMapper.updateLoginname(loginname, id);
	}
	
	@Override
	public int updateUsername(String username, Integer id)
	{
		return userMapper.updateUsername(username, id);
	}
	@Override
	public int updatePhone(String phone, Integer id)
	{
		return userMapper.updatePhone(phone, id);
	}
	@Override
	public int updateMail(String mail, Integer id)
	{
		return userMapper.updateMail(mail, id);
	}
	@Override
	public int updatePassword(String password,Integer id)
	{
		return userMapper.updatePassword(password, id);
	}
	
	@Override
	public User getById(Integer id)
	{
		return userMapper.getById(id);
	}
	
	@Override
	public int updateAvatar(String avatar, Integer id)
	{
		return userMapper.updateAvatar(avatar, id);
	}
}
