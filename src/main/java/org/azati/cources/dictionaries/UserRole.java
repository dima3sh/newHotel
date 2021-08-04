package org.azati.cources.dictionaries;

import org.azati.cources.entity.AppUser;
import org.azati.cources.enums.UserRoles;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class UserRole {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role_varchar")
    private String roleStr;

    @Column(name = "user_role")
    private UserRoles userRole;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "userRole")
    private List<AppUser> appUsers;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleStr() {
        return roleStr;
    }

    public void setRoleStr(String roleStr) {
        this.roleStr = roleStr;
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoles userRole) {
        this.userRole = userRole;
    }
}
