package org.tylor.origin.service;

import java.util.Optional;
import jdk.nashorn.internal.runtime.options.Option;
import org.tylor.origin.pojo.UserInfo;

/**
 * @author liubin10  2018/3/20
 */
public interface UserService {


    void deleteAll();

    boolean checkUserPassword(String userName, String ticket);

    void save(String username,String password);

    Optional<UserInfo> findUserByUsername(String username);

    void save(UserInfo userInfo);
}
