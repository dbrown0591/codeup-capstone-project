package com.codeup.michero.daos;

import com.codeup.michero.models.Concert;
import org.springframework.data.repository.CrudRepository;


public interface ConcertsRepository extends CrudRepository<Concert, Long> {
}
