package org.azati.cources.repository;

import org.azati.cources.dictionaries.UserRole;
import org.azati.cources.entity.AppUser;
import org.azati.cources.enums.UserRoles;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<AppUser, Long> {

    AppUser findByUsername(String login);

    List<AppUser> findAllByUserRole_UserRole(UserRoles userRole);
}
