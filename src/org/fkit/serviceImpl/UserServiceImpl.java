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
		// ���չ������������ȡ�����б�
		List<Music> orderMusicList=musicMapper.getAllByPurchasesOrder();
		// ȡ��һ��ֵ��Ϊ��ʼ��mlmusic_id
		Integer mlmusic_id=orderMusicList.get(0).getId();
		System.out.println(mlmusic_id);
		// ע���û�
		return userMapper.insertForRegister(loginname, password, age, mlmusic_id);
	}
	@Override
	public Boolean checkRegister(String loginname)
	{
		if(userMapper.checkWithLoginname(loginname)!=null)
		{
			//�Ѿ���ͬ���ǳ�
			return false;
		}
		else
		{
			//û��ͬ���ģ�����ע��
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
	
	//���û����з��ࣨ��18�����
	@Override
	public int classifyUser(Integer id, Integer age)
	{
		// ��ȡ�û���Ϊ�б�
		List<Behavior> behaviorList = behaviorMapper.getByUserId(id);
		int[] resultList= {0,0,0};
		for(Behavior b: behaviorList)
		{
			switch(b.getMusic().getCategory())
			{
			case "������":
				if(b.getBehavior()==1)
				{
					resultList[0] += 1;//���
				}
				if(b.getBehavior()==2)
				{
					resultList[0] += 3;//����
				}
				break;
			case "ŷ����ҥ":
				if(b.getBehavior()==1)
				{
					resultList[1] += 1;//���
				}
				if(b.getBehavior()==2)
				{
					resultList[1] += 3;//����
				}
				break;
			case "����":
				if(b.getBehavior()==1)
				{
					resultList[2] += 1;//���
				}
				if(b.getBehavior()==2)
				{
					resultList[2] += 3;//����
				}
				break;
			default:
				return 0;
			}
		}
		
		// ���û���ϲ��������
		int mlCategory=0;
		for(int i=1;i<3;i++)
		{
			if(resultList[i]>resultList[mlCategory])
			{
				mlCategory=i;
			}
		}
		// �û�����
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
	
	//��ȡ������ϲ���������б�
	@Override
	public List<Music> getLikeMusic(Integer mlmusic_id) 
	{
		// ��ȡ����ֵ����
		String mlcategory=musicMapper.getById(mlmusic_id).getCategory();
		// ��������ȡ����������б�
		return musicMapper.getByCategory(mlcategory);
	}
	//��ȡ�����������������б�
	@Override
	public List<Music> getOtherLikeMusic(Integer classification) 
	{
		if(classification==0)
		{
			// ��δ���࣬�Ƽ�ȫ�ֳ�������
			return musicMapper.getAllByPurchasesOrder();
		}
		else
		{
			if(classification<=18 && classification>=1)
			{
				// ��������ȡ�û��б�
				List<User> cUserList=userMapper.getByClassification(classification);
				// ����ձ�
				List<Music> olMusicList=new ArrayList<Music>();
				int existFlag=0;
				for (User user: cUserList)
				{
					// ��ͬ���������ּ��뵽�б���
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
						// �����ظ�����
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
	// ��ȡ�û���ǩ
	@Override
	public List<String> getUserTag(Integer classification) 
	{
		List<String> userTagList= new ArrayList<String>();
		switch(classification)
		{
		case 0:
			userTagList.add("����С������");
			userTagList.add("�Ȱ�����");
			break;
		case 1:
		case 2:
			userTagList.add("����������");
			userTagList.add("��&����");
			userTagList.add("�Ȱ�����");
			break;
		case 3:
			userTagList.add("����������");
			userTagList.add("��&����");
			userTagList.add("׷������");
			break;
		case 4:
		case 5:
		case 6:
			userTagList.add("�����ִ���");
			userTagList.add("��&����");
			userTagList.add("�������");
			break;
		case 7:
		case 8:
			userTagList.add("ŷ���硤����");
			userTagList.add("���ݡ�����");
			userTagList.add("�Ȱ�����");
			break;
		case 9:
			userTagList.add("ŷ���硤����");
			userTagList.add("�����ഺ�����ĸ���");
			userTagList.add("�Ȱ�����");
			break;
		case 10:
		case 11:
		case 12:
			userTagList.add("�����Ȱ���");
			userTagList.add("���ĸ���");
			userTagList.add("ŷ�����");
			break;
		case 13:
		case 14:
			userTagList.add("�������");
			userTagList.add("�������֡��й����");
			userTagList.add("�Ȱ�����");
			break;
		case 15:
			userTagList.add("�������");
			userTagList.add("�������֡��й����");
			userTagList.add("�Ȱ�����");
			break;
		case 16:
		case 17:
		case 18:
			userTagList.add("�����Ȱ���");
			userTagList.add("��������");
			break;	
		default:
			userTagList.add("�û���ǩ��ȡ��������ϵ����Ա��");
			break;
		}
		return userTagList;
	}
}
