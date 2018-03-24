package org.tylor.origin.service.impl;

import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.tylor.origin.dao.UserInfoDao;
import org.tylor.origin.pojo.UserInfo;
import org.tylor.origin.service.UserService;
import org.tylor.origin.util.PwdUtil;

/**
 * @author liubin10  2018/3/24
 */
@Service("userService")
public class UserDetailsServiceImpl implements UserDetailsService ,UserService {

    @Autowired
    UserInfoDao userInfoDao;



    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInfoDao.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("用户名不存在"));
    }

    @Override
    public boolean checkUserPassword(String userName, String ticket) {
        return PwdUtil.encrypt(ticket).equals(
            this.userInfoDao.findByUsername(userName).orElseGet(UserInfo::new)
                .getPassword());
    }

    @Override
    public void save(String username, String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        this.userInfoDao.save(userInfo);
    }

    @Override
    public Optional<UserInfo> findUserByUsername(String username) {
        return userInfoDao.findByUsername(username);
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoDao.save(userInfo);
    }

    @Override
    public void deleteAll() {
        this.userInfoDao.deleteAll();
    }


}
