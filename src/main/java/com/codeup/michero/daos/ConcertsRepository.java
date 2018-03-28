package com.codeup.michero.daos;

import com.codeup.michero.models.Concerts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertsRepository extends CrudRepository<Concerts, Long> {
}
