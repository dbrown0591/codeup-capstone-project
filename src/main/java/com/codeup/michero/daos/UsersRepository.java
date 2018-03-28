package com.codeup.michero.daos;


import com.codeup.michero.models.Users;
import org.apache.tomcat.jni.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
    User findByUsername(String username);//Is it User or Users???
}
