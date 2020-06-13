package org.fkit.serviceImpl;
import java.util.ArrayList;
import java.util.List;

import org.fkit.domain.Behavior;
import org.fkit.domain.Music;
import org.fkit.domain.User;
import org.fkit.mapper.BehaviorMapper;
import org.fkit.mapper.MusicMapper;
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
	@Autowired
	private MusicMapper musicMapper;
	@Autowired
	private BehaviorMapper behaviorMapper;
	
	@Override
	public User login(String loginname, String password)
	{
		return userMapper.findWithLoginnameAndPassword(loginname, password);
	}
	@Override
	public int register(String loginname, String password, Integer age)
	{
		// 按照购买数量排序获取音乐列表
		List<Music> orderMusicList=musicMapper.getAllByPurchasesOrder();
		// 取第一个值作为初始化mlmusic_id
		Integer mlmusic_id=orderMusicList.get(0).getId();
		System.out.println(mlmusic_id);
		// 注册用户
		return userMapper.insertForRegister(loginname, password, age, mlmusic_id);
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
	
	@Override
	public int updateAge(Integer age, Integer id)
	{
		return userMapper.updateAge(age, id);
	}
	
	@Override
	public int updateClassification(Integer classification, Integer id)
	{
		return userMapper.updateClassification(classification, id);
	}
	
	@Override
	public int updateLogintime(Integer logintime, Integer id)
	{
		return userMapper.updateLogintime(logintime, id);
	}
	
	@Override
	public int updateBrowsetime(Double browsetime, Integer id)
	{
		return userMapper.updateBrowsetime(browsetime, id);
	}
	
	@Override
	public int updateMlmusicId(Integer mlmusic_id, Integer id)
	{
		return userMapper.updateMlmusicId(mlmusic_id, id);
	}
	
	//对用户进行分类（共18个类别）
	@Override
	public int classifyUser(Integer id, Integer age)
	{
		// 获取用户行为列表
		List<Behavior> behaviorList = behaviorMapper.getByUserId(id);
		int[] resultList= {0,0,0};
		for(Behavior b: behaviorList)
		{
			switch(b.getMusic().getCategory())
			{
			case "纯音乐":
				if(b.getBehavior()==1)
				{
					resultList[0] += 1;//浏览
				}
				if(b.getBehavior()==2)
				{
					resultList[0] += 3;//购买
				}
				break;
			case "欧美民谣":
				if(b.getBehavior()==1)
				{
					resultList[1] += 1;//浏览
				}
				if(b.getBehavior()==2)
				{
					resultList[1] += 3;//购买
				}
				break;
			case "国语":
				if(b.getBehavior()==1)
				{
					resultList[2] += 1;//浏览
				}
				if(b.getBehavior()==2)
				{
					resultList[2] += 3;//购买
				}
				break;
			default:
				return 0;
			}
		}
		
		// 找用户最喜爱的类型
		int mlCategory=0;
		for(int i=1;i<3;i++)
		{
			if(resultList[i]>resultList[mlCategory])
			{
				mlCategory=i;
			}
		}
		// 用户分类
		switch(mlCategory)
		{
		case 0:
			if(0<age&&age<=10)
			{
				userMapper.updateClassification(1, id);
			}
			if(10<age&&age<=20)
			{
				userMapper.updateClassification(2, id);
			}
			if(20<age&&age<=30)
			{
				userMapper.updateClassification(3, id);
			}
			if(30<age&&age<=40)
			{
				userMapper.updateClassification(4, id);
			}
			if(40<age&&age<=50)
			{
				userMapper.updateClassification(5, id);
			}
			if(50<age)
			{
				userMapper.updateClassification(6, id);
			}
			break;
		case 1:
			if(0<age&&age<=10)
			{
				userMapper.updateClassification(7, id);
			}
			if(10<age&&age<=20)
			{
				userMapper.updateClassification(8, id);
			}
			if(20<age&&age<=30)
			{
				userMapper.updateClassification(9, id);
			}
			if(30<age&&age<=40)
			{
				userMapper.updateClassification(10, id);
			}
			if(40<age&&age<=50)
			{
				userMapper.updateClassification(11, id);
			}
			if(50<age)
			{
				userMapper.updateClassification(12, id);
			}
			break;
		case 2:
			if(0<age&&age<=10)
			{
				userMapper.updateClassification(13, id);
			}
			if(10<age&&age<=20)
			{
				userMapper.updateClassification(14, id);
			}
			if(20<age&&age<=30)
			{
				userMapper.updateClassification(15, id);
			}
			if(30<age&&age<=40)
			{
				userMapper.updateClassification(16, id);
			}
			if(40<age&&age<=50)
			{
				userMapper.updateClassification(17, id);
			}
			if(50<age)
			{
				userMapper.updateClassification(18, id);
			}
			break;
		default:
			return 0;
		}
		
		return 1;
	}
	
	//获取“猜你喜欢”音乐列表
	@Override
	public List<Music> getLikeMusic(Integer mlmusic_id) 
	{
		// 获取最爱音乐的类别
		String mlcategory=musicMapper.getById(mlmusic_id).getCategory();
		// 按照类别获取降序的音乐列表
		return musicMapper.getByCategory(mlcategory);
	}
	//获取“别人在听”音乐列表
	@Override
	public List<Music> getOtherLikeMusic(Integer classification) 
	{
		if(classification==0)
		{
			// 尚未分类，推荐全局畅销音乐
			return musicMapper.getAllByPurchasesOrder();
		}
		else
		{
			if(classification<=18 && classification>=1)
			{
				// 按照类别获取用户列表
				List<User> cUserList=userMapper.getByClassification(classification);
				// 定义空表
				List<Music> olMusicList=new ArrayList<Music>();
				int existFlag=0;
				for (User user: cUserList)
				{
					// 将同类的最爱听音乐加入到列表中
					Music m=musicMapper.getById(user.getMlmusic_id());
					for(Music existMusic: olMusicList)
					{
						if(existMusic.getId()==m.getId())
						{
							existFlag=1;
							break;
						}
					}
					if(existFlag==0)
					{
						// 避免重复加入
						olMusicList.add(m);
					}
				}
				return olMusicList;
			}
			else
			{
				return new ArrayList<Music>();
			}
		}
	}
	// 获取用户标签
	@Override
	public List<String> getUserTag(Integer classification) 
	{
		List<String> userTagList= new ArrayList<String>();
		switch(classification)
		{
		case 0:
			userTagList.add("音乐小店新人");
			userTagList.add("热爱音乐");
			break;
		case 1:
		case 2:
			userTagList.add("轻音乐少年");
			userTagList.add("简单&纯粹");
			userTagList.add("热爱音乐");
			break;
		case 3:
			userTagList.add("轻音乐青年");
			userTagList.add("简单&纯粹");
			userTagList.add("追求自由");
			break;
		case 4:
		case 5:
		case 6:
			userTagList.add("轻音乐达人");
			userTagList.add("简单&纯粹");
			userTagList.add("大道至简");
			break;
		case 7:
		case 8:
			userTagList.add("欧美风·少年");
			userTagList.add("包容·独立");
			userTagList.add("热爱音乐");
			break;
		case 9:
			userTagList.add("欧美风·青年");
			userTagList.add("张扬青春·外文歌曲");
			userTagList.add("热爱音乐");
			break;
		case 10:
		case 11:
		case 12:
			userTagList.add("音乐热爱者");
			userTagList.add("外文歌曲");
			userTagList.add("欧美风格");
			break;
		case 13:
		case 14:
			userTagList.add("国语·少年");
			userTagList.add("中文音乐·中国风格");
			userTagList.add("热爱音乐");
			break;
		case 15:
			userTagList.add("国语·青年");
			userTagList.add("中文音乐·中国风格");
			userTagList.add("热爱音乐");
			break;
		case 16:
		case 17:
		case 18:
			userTagList.add("音乐热爱者");
			userTagList.add("中文音乐");
			break;	
		default:
			userTagList.add("用户标签获取出错，请联系管理员！");
			break;
		}
		return userTagList;
	}
}
