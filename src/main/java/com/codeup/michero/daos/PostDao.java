package com.codeup.michero.daos;

import com.codeup.michero.models.Reviews;
import org.springframework.data.repository.CrudRepository;

public interface PostDao extends CrudRepository<Reviews, Long> {
}
