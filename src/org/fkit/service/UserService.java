package org.fkit.service;

import java.util.List;

import org.fkit.domain.Music;
import org.fkit.domain.User;

public interface UserService {
	User login(String loginname, String password);

	int register(String loginname, String password, Integer age);

	Boolean checkRegister(String loginname);
	
	int updateLoginname(String loginname, Integer id);

	int updateUsername(String username, Integer id);

	int updatePhone(String phone, Integer id);

	int updateMail(String mail, Integer id);

	int updatePassword(String password, Integer id);
	
	User getById(Integer id);
	
	int updateAvatar(String avatar, Integer id);
	
	int updateAge(Integer age, Integer id);
	
	int updateClassification(Integer classification, Integer id);
	
	int updateLogintime(Integer logintime, Integer id);
	
	int updateBrowsetime(Double browsetime, Integer id);
	
	int updateMlmusicId(Integer mlmusic_id, Integer id);
	
	int classifyUser(Integer id, Integer age);
	
	List<Music> getLikeMusic(Integer mlmusic_id);
	
	List<Music> getOtherLikeMusic(Integer id);
	
	List<String> getUserTag(Integer classification);
}
