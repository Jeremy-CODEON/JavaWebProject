package org.fkit.serviceImpl;

import java.util.List;

import org.fkit.domain.Music;
import org.fkit.mapper.MusicMapper;
import org.fkit.mapper.StatisticMapper;
import org.fkit.mapper.UserMapper;
import org.fkit.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import othersPOJO.StatisCategoryInfo;

@Service("statisticService")
public class StatisticServiceImpl implements StatisticService{
	
	@Autowired
	private StatisticMapper statisMapper;
	@Autowired
	private MusicMapper musicMapper;
	@Override
	public List<StatisCategoryInfo> getStatisticCategoryInfo()
	{
		//首先是获取按照类型分类的结果的列表
		List<StatisCategoryInfo> cateList=statisMapper.getStatisticCategoryAndNum();
		
		for(StatisCategoryInfo cate:cateList)
		{
			//按照每一个类型进行音乐列表的获取
			List<Music> musicList=musicMapper.getByCategory(cate.getCategory());
			for(Music music:musicList)
			{
				//获取音乐的销售量和单价，并进行累加统计
				cate.setTotalSale(cate.getTotalSale()+music.getPurchases()*music.getCost());
				cate.setTotalSaleNum(cate.getTotalSaleNum()+music.getPurchases());
			}
		}
		
		return cateList;
	}

}
