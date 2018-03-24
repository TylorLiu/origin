package org.tylor.origin;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.tylor.origin.service.UserService;

/**
 * @author liubin10  2018/3/24
 */
//@Component
public class InitSystem {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserService service;

    @PostConstruct
    void  init (){
        service.save("root",passwordEncoder.encode("12345"));

    }

}
