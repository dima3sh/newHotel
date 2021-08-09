package org.azati.cources.dto;

import org.azati.cources.enums.UserRoles;

public class UserDTO {
    private Long UserId;
    private String Username;
    private UserRoles userRoles;

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public UserRoles getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(UserRoles userRoles) {
        this.userRoles = userRoles;
    }
}
