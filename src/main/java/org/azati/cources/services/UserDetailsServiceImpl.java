package org.azati.cources.services;

import org.azati.cources.entity.AppUser;
import org.azati.cources.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);
        return (UserDetails) new User(user.getUsername(), user.getPassword()
                , Collections.singletonList(new SimpleGrantedAuthority(user.getUserRole().getUserRole().toString())));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(AppUser user) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(user.getUserRole().getUserRole().toString());
    }
}
