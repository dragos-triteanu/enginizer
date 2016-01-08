package com.startup.enginizer.services;

import com.startup.enginizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Dragos on 9/13/2015.
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${users.predefined.password}")
    private String definedInitialPassword;


}
