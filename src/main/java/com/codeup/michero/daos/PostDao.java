package com.codeup.michero.daos;

import com.codeup.michero.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface PostDao extends CrudRepository<Review, Long> {
}
