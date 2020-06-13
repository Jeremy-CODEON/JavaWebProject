package org.fkit.service;

import java.util.List;

import org.fkit.domain.Behavior;

public interface BehaviorService {
	
	int addBehavior(int user_id, int music_id, int behavior, int year, int month, int day);
	
	List<Behavior> getByUserId(int user_id);
	
	List<Behavior> getAllBehavior();
	
	List<Behavior> getByMusicId(int music_id);
	
	double getPredictionValue(int music_id);
	
	List<Integer> getCountValue(int music_id);
	
	int getMostLikeMusicId(int user_id);

}
