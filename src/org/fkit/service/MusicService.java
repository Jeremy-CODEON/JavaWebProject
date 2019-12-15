package org.fkit.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.fkit.domain.Music;

public interface MusicService {
	List<Music> getAll();

	List<Music> getByWord(String input);

	List<Music> getByCategory(String category);

	Music getById(int id);

	int updateName(String name, int id);

	int updateSinger(String singer, int id);

	int updateDescription(String description, int id);

	int updateAlbum(String album, int id);

	int updateDuration(String duration, int id);

	int updateCategory(String category, int id);

	int updateCost(double cost, int id);

	int updateState(int state, int id);

	int addMusic(String name, String singer, String description, String album, String duration, String category,
			double cost, int state);
	
	int deleteMusic(int id);
}
