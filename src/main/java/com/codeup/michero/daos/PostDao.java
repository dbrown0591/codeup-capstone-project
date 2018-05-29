package com.codeup.michero.daos;

import com.codeup.michero.models.Image;
import com.codeup.michero.models.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostDao extends CrudRepository<Review, Long> {
    List<Review> findByConcert_id(long id);
}
