package com.school.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.school.entity.Response;
import com.school.entity.User;



/*** @author: zhangjiajun
* @date: ----
* @version: 1.3.0
* @deion: 处理用户登录Dao
*/
@Mapper
public interface UserDao {
	User userLogin(@Param("userId") String userId);
}
