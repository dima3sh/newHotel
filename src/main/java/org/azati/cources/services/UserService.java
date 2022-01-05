package org.azati.cources.services;

import org.azati.cources.dictionaries.UserRole;
import org.azati.cources.dto.UserDTO;
import org.azati.cources.entity.AppUser;
import org.azati.cources.enums.UserRoles;
import org.azati.cources.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUser getUser(Long userId) {
        return userRepository.findById(userId).get();
    }

    public List<AppUser> getAllUsers() {
        return (List<AppUser>) userRepository.findAll();
    }

    public List<AppUser> getUserWithoutRole(UserRoles userRole) {
        return (List<AppUser>) userRepository.findAllByUserRole_UserRole(userRole);
    }

    public AppUser updateUser(AppUser user) {
        userRepository.save(user);
        return user;
    }

    public AppUser updateUser(UserDTO user) {
        AppUser appUser = getUser(user.getUserId());
        if (appUser != null) {
            appUser.setEmailAddress(user.getEmailAddress());
            appUser.setPhoneNumber((user.getPhoneNumber()));
            appUser.setUsername(user.getUsername());
            appUser.setName(user.getName());
            appUser.getUserRole().setUserRole(user.getUserRole());
            userRepository.save(appUser);
        }
        return appUser;
    }

    public boolean saveUser(AppUser user) {
        AppUser userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        UserRole userRole = new UserRole();
        userRole.setUserRole(UserRoles.NONE);
        userRole.setRoleId(UserRoles.NONE.getIndex());
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
