package com.codeup.michero.daos;

import com.codeup.michero.models.Concert;
import com.codeup.michero.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ConcertsRepository extends CrudRepository<Concert, Long> {
    List<Concert> findByUser(User u);

}

