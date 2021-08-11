package org.azati.cources.dto;

public class UserDTO {
    private Long UserId;
    private String Username;
    private String userRole;

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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRoles(String userRoles) {
        this.userRole = userRoles;
    }
}
