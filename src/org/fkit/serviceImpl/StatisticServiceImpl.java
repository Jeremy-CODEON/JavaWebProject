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
		//�����ǻ�ȡ�������ͷ���Ľ�����б�
		List<StatisCategoryInfo> cateList=statisMapper.getStatisticCategoryAndNum();
		
		for(StatisCategoryInfo cate:cateList)
		{
			//����ÿһ�����ͽ��������б�Ļ�ȡ
			List<Music> musicList=musicMapper.getByCategory(cate.getCategory());
			for(Music music:musicList)
			{
				//��ȡ���ֵ��������͵��ۣ��������ۼ�ͳ��
				cate.setTotalSale(cate.getTotalSale()+music.getPurchases()*music.getCost());
				cate.setTotalSaleNum(cate.getTotalSaleNum()+music.getPurchases());
			}
		}
		
		return cateList;
	}

}
