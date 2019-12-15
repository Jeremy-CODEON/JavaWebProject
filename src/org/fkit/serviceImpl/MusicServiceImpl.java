package org.fkit.serviceImpl;

import java.util.List;

import org.fkit.domain.Music;
import org.fkit.mapper.MusicMapper;
import org.fkit.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("musicService")
public class MusicServiceImpl implements MusicService {
	@Autowired
	private MusicMapper musicMapper;
	@Override
	public List<Music> getAll()
	{
		return musicMapper.findAll();
	}
	@Override
	public List<Music> getByWord(String input)
	{
		return musicMapper.getByWord(input);
	}
	@Override
	public List<Music> getByCategory(String category)
	{
		return musicMapper.getByCategory(category);
	}
	@Override
	public Music getById(int id)
	{
		return musicMapper.getById(id);
	}
	@Override
	public int updateName(String name,int id)
	{
		return musicMapper.updateName(name, id);
	}
	@Override
	public int updateSinger(String singer,int id)
	{
		return musicMapper.updateSinger(singer, id);
	}
	@Override
	public int updateDescription(String description,int id)
	{
		return musicMapper.updateDescription(description, id);
	}
	@Override
	public int updateAlbum(String album,int id)
	{
		return musicMapper.updateAlbum(album, id);
	}
	@Override
	public int updateDuration(String duration,int id)
	{
		return musicMapper.updateDuration(duration, id);
	}
	@Override
	public int updateCategory(String category,int id)
	{
		return musicMapper.updateCategory(category, id);
	}
	@Override
	public int updateCost(double cost,int id)
	{
		return musicMapper.updateCost(cost, id);
	}
	@Override
	public int updateState(int state,int id)
	{
		return musicMapper.updateState(state, id);
	}
	@Override
	public int addMusic(String name, String singer, String description, String album, String duration, String category,
			double cost, int state)
	{
		return musicMapper.insertMusic(name, singer, description, album, duration, category, cost, state);
	}
	@Override
	public int deleteMusic(int id)
	{
		return musicMapper.deleteById(id);
	}
	
}

