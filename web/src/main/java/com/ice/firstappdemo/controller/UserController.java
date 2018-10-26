package com.ice.firstappdemo.controller;

import com.ice.firstappdemo.domain.User;
import com.ice.firstappdemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author ice
 * @date 2018/10/25
 */
@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserRepository userRepository ;

    @Autowired
    public UserController (UserRepository userRepository){
        this.userRepository = userRepository ;
    }

    @PostMapping("/person/save")
    public User save(@RequestParam String name){
        User user = new User();
        user.setName(name);
        userRepository.save(user) ;
        logger.info("user={}",user.getId() + "," + user.getName());
        //重写toString方法
        System.out.println(user);
        return user ;
    }

    @GetMapping("/person/findAll")
    public Collection findAll(){
        return userRepository.findAll();
    }
}
