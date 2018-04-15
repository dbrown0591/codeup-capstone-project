package com.codeup.michero.daos;


import com.codeup.michero.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
