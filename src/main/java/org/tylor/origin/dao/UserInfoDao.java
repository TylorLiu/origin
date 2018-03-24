package org.tylor.origin.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tylor.origin.pojo.UserInfo;

/**
 * @author liubin10  2018/3/20
 */
@Repository
public interface UserInfoDao extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByUsername(String username);
}
