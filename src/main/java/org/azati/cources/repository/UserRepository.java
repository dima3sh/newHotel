package org.azati.cources.repository;

import org.azati.cources.entity.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<AppUser, Long> {

    AppUser findByUsername(String login);
}
