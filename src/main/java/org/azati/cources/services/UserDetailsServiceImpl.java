package org.azati.cources.services;

import org.azati.cources.entity.AppUser;
import org.azati.cources.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByLogin(username);
        UserDetails userDetails = (UserDetails) new User(user.getLogin(), user.getPassword()
                , Arrays.asList(new SimpleGrantedAuthority(user.getUserRole().getRoleStr())));
        return userDetails;
    }
}
