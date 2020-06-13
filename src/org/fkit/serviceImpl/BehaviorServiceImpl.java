package org.fkit.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import org.fkit.domain.Behavior;
import org.fkit.mapper.BehaviorMapper;
import org.fkit.service.BehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("behaviorService")
public class BehaviorServiceImpl implements BehaviorService{
	
	@Autowired
	private BehaviorMapper behaviorMapper;
	
	@Override
	public int addBehavior(int user_id, int music_id, int behavior, int year, int month, int day)
	{
		return behaviorMapper.insertBehavior(user_id, music_id, behavior, year, month, day);
	}

	@Override
	public List<Behavior> getByUserId(int user_id) 
	{
		return behaviorMapper.getByUserId(user_id);
	}

	@Override
	public List<Behavior> getAllBehavior() 
	{
		return behaviorMapper.getAllBehavior();
	}

	@Override
	public List<Behavior> getByMusicId(int music_id) 
	{
		return behaviorMapper.getByMusicId(music_id);
	}

	@Override
	public double getPredictionValue(int music_id) 
	{
		// 获取当前音乐的购买记录(年月已排序)
		List<Behavior> behaviorList=this.getByMusicId(music_id);
		
		if(behaviorList.size()==0)
		{
			return (double)0;
		}
		
		Calendar cal=Calendar.getInstance();
		int curYear=cal.get(Calendar.YEAR);
		int curMonth=cal.get(Calendar.MONTH)+1;
		
		int y=behaviorList.get(0).getYear();
		int m=behaviorList.get(0).getMonth();
		
		ArrayList<Integer> countByMonth = new ArrayList<Integer>();
		int total=0;
		
		for(Behavior b:behaviorList)
		{
			if(b.getYear()==curYear&&b.getMonth()==curMonth)
			{
				//当前月数据不统计
				break;
			}
			if(b.getYear()!=y||b.getMonth()!=m)
			{
				countByMonth.add(total);
				total=1;
				y=b.getYear();
				m=b.getMonth();
			}
			else
			{
				total+=1;
			}
		}
		
		if(total!=0)
		{
			countByMonth.add(total);
		}
		
		if(countByMonth.size()==0)
		{
			return (double)0;
		}
		
		// 多项式拟合
		WeightedObservedPoints points=new WeightedObservedPoints();
		for(int i=0;i<countByMonth.size();i++)
		{
			points.add(i+1, countByMonth.get(i));
		}
		PolynomialCurveFitter fitter=PolynomialCurveFitter.create(2);//拟合的次数为2

		double[] result=fitter.fit(points.toList());
		
		// 计算预测值
		double predictionValue=0;
		predictionValue=result[0]+result[1]*(countByMonth.size()+1)+result[2]*(countByMonth.size()+1)*(countByMonth.size()+1);
		
		// 边界处理
		if(predictionValue<0)
		{
			predictionValue=0;
		}
		
		return predictionValue;
	}

	@Override
	public List<Integer> getCountValue(int music_id)
	{
		// 获取当前音乐的购买记录(年月已排序)
		List<Behavior> behaviorList=this.getByMusicId(music_id);
		
		if(behaviorList.size()==0)
		{
			return new ArrayList<Integer>();
		}
		
		Calendar cal=Calendar.getInstance();
		int curYear=cal.get(Calendar.YEAR);
		int curMonth=cal.get(Calendar.MONTH)+1;
		
		int y=behaviorList.get(0).getYear();
		int m=behaviorList.get(0).getMonth();
		
		ArrayList<Integer> countByMonth = new ArrayList<Integer>();
		int total=0;
		
		for(Behavior b:behaviorList)
		{
			if(b.getYear()==curYear&&b.getMonth()==curMonth)
			{
				//当前月数据不统计
				break;
			}
			if(b.getYear()!=y||b.getMonth()!=m)
			{
				countByMonth.add(total);
				total=1;
				y=b.getYear();
				m=b.getMonth();
			}
			else
			{
				total+=1;
			}
		}
		if(total!=0)
		{
			countByMonth.add(total);
		}
		
		return countByMonth;
	}

	@Override
	public int getMostLikeMusicId(int user_id) 
	{
		List<Behavior> behaviorList=behaviorMapper.getByUserId(user_id);
		if(behaviorList.size()==0)
		{
			return -1;
		}
		
		ArrayList<Integer> countList=new ArrayList<Integer>();
		ArrayList<Integer> indexList=new ArrayList<Integer>();
		
		int index=-1;
		int increase=0;
		
		for(Behavior b:behaviorList)
		{
			if(b.getBehavior()==1)
			{
				increase=1;
			}
			if(b.getBehavior()==2)
			{
				increase=3;
			}
			
			for(int i=0;i<countList.size();i++)
			{
				if(indexList.get(i)==b.getMusic().getId())
				{
					index=i;
					break;
				}
			}
			if(index==-1)
			{
				// 记录新的下标
				indexList.add(b.getMusic().getId());
				countList.add(increase);
			}
			else
			{
				// 更新记录
				countList.set(index, countList.get(index)+increase);
				index=-1;
			}
		}
		
		int mlmusic_index=0;
		for(int i=1;i<countList.size();i++)
		{
			if(countList.get(i)>countList.get(mlmusic_index))
			{
				// 找最大值
				mlmusic_index=i;
			}
		}
		return indexList.get(mlmusic_index);
	}

}
