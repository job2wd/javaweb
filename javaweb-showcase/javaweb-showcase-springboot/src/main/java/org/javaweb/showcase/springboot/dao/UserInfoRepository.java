/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.dao;

import org.javaweb.showcase.springboot.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年9月5日 下午7:28:44$
 * @LastChanged $Author:$, $Date::                    #$
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    
}
