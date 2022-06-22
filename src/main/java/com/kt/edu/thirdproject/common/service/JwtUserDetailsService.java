package com.kt.edu.thirdproject.common.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("edu".equals(username)) {
        //    return new User("user_id", "$2a$10$m/enYHaLsCwH2dKMUAtQp.ksGOA6lq7Fd2pnMb4L.yT4GyeAPRPyS",
            return new User("edu", "db03de15b8000fc35ad975c1322f98124a22521e0616a55c926807eb7225fa38",
                    new ArrayList<>()); //edu1234

        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}