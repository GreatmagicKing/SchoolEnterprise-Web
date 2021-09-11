package com.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dao.UserDao;
import com.school.entity.User;
import com.school.service.UserService;


/*** @author: zhangjiajun
* @date: ----
* @version: 1.3.0
* @deion: 处理用户登录UserService
*/
@Service
public class UserServiceimpl implements UserService{

	@Autowired
	UserDao userDao;
	User user;
	@Override
	public User UserLogin(String userId) {
		// TODO Auto-generated method stub
		user=userDao.userLogin(userId);
		if(userId!=null) {
			return user;
		}else {
			return null;
		}
	}
}
