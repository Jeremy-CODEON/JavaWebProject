package org.fkit.service;

import org.fkit.domain.User;

public interface UserService {
	User login(String loginname, String password);

	int register(String loginname, String password);

	Boolean checkRegister(String loginname);
	
	int updateLoginname(String loginname, Integer id);

	int updateUsername(String username, Integer id);

	int updatePhone(String phone, Integer id);

	int updateMail(String mail, Integer id);

	int updatePassword(String password, Integer id);
	
	User getById(Integer id);
	
	int updateAvatar(String avatar, Integer id);
}
