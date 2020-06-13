package org.fkit.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fkit.domain.Manager;
import org.fkit.domain.User;
import org.fkit.mapper.ManagerMapper;
import org.fkit.mapper.UserMapper;
import org.fkit.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService{
	
	@Autowired
	private ManagerMapper managerMapper;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public Manager login(Integer id, String password)
	{
		return managerMapper.findWithIdAndPassword(id, password);
	}

	@Override
	public List<Double> getBrowseStatistic() 
	{
		// 记录平均登录时间
		List<Double> browseList=Arrays.asList(new Double[6]);
		for(int i=0;i<browseList.size();i++)
		{
			browseList.set(i, (double)0);
		}
		// 记录人数
		List<Double> countList=Arrays.asList(new Double[6]);
		for(int i=0;i<countList.size();i++)
		{
			countList.set(i, (double)0);
		}
		
		// 分类统计所有用户的登录时长
		List<User> userList=userMapper.getAllUser();
		for(User u:userList)
		{
			switch(u.getClassification())
			{
			case 0:
				break;
			case 1:
			case 7:
			case 13:
				browseList.set(0, browseList.get(0)+u.getBrowsetime());
				countList.set(0, countList.get(0)+1);
				break;
			case 2:
			case 8:
			case 14:
				browseList.set(1, browseList.get(1)+u.getBrowsetime());
				countList.set(1, countList.get(1)+1);
				break;
			case 3:
			case 9:
			case 15:
				browseList.set(2, browseList.get(2)+u.getBrowsetime());
				countList.set(2, countList.get(2)+1);
				break;
			case 4:
			case 10:
			case 16:
				browseList.set(3, browseList.get(3)+u.getBrowsetime());
				countList.set(3, countList.get(3)+1);
				break;
			case 5:
			case 11:
			case 17:
				browseList.set(4, browseList.get(4)+u.getBrowsetime());
				countList.set(4, countList.get(4)+1);
				break;
			case 6:
			case 12:
			case 18:
				browseList.set(5, browseList.get(5)+u.getBrowsetime());
				countList.set(5, countList.get(5)+1);
				break;
			default:
				return Arrays.asList(new Double[6]);
			}
		}
		
		for(int i=0;i<browseList.size();i++)
		{
			if(countList.get(i)==0)
			{
				browseList.set(i, (double)0);
			}
			else
			{
				// 求平均(转换单位为min)
				browseList.set(i, browseList.get(i)/countList.get(i)/60);
			}
		}
		
		return browseList;
	}

	@Override
	public List<Double> getActiveStatistic() 
	{
		// 记录活跃度
		List<Double> browseList=Arrays.asList(new Double[6]);
		for(int i=0;i<browseList.size();i++)
		{
			browseList.set(i, (double)0);
		}
		
		// 分类统计所有用户的登录时长和登录次数
		List<User> userList=userMapper.getAllUser();
		for(User u:userList)
		{
			switch(u.getClassification())
			{
			case 0:
				break;
			case 1:
			case 7:
			case 13:
				browseList.set(0, browseList.get(0)+u.getBrowsetime()*u.getLogintime());
				break;
			case 2:
			case 8:
			case 14:
				browseList.set(1, browseList.get(1)+u.getBrowsetime()*u.getLogintime());
				break;
			case 3:
			case 9:
			case 15:
				browseList.set(2, browseList.get(2)+u.getBrowsetime()*u.getLogintime());
				break;
			case 4:
			case 10:
			case 16:
				browseList.set(3, browseList.get(3)+u.getBrowsetime()*u.getLogintime());
				break;
			case 5:
			case 11:
			case 17:
				browseList.set(4, browseList.get(4)+u.getBrowsetime()*u.getLogintime());
				break;
			case 6:
			case 12:
			case 18:
				browseList.set(5, browseList.get(5)+u.getBrowsetime()*u.getLogintime());
				break;
			default:
				return Arrays.asList(new Double[6]);
			}
		}
		
		return browseList;
	}		

}
