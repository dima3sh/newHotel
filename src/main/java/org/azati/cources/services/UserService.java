package org.azati.cources.services;

import org.azati.cources.dictionaries.UserRole;
import org.azati.cources.entity.AppUser;
import org.azati.cources.enums.UserRoles;
import org.azati.cources.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<AppUser> getAllUsers () {
        return (ArrayList)userRepository.findAll();
    }

    public boolean saveUser(AppUser user) {
        AppUser userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        UserRole userRole = new UserRole();
        userRole.setUserRole(UserRoles.USER);
        userRole.setRoleId(UserRoles.USER.getIndex());
        user.setUserRole(userRole);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        userRepository.save(user);
        return true;
    }

    public Long getCountRecords() {
        return userRepository.count();
    }
}
