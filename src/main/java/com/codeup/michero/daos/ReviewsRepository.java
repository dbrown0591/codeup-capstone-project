package com.codeup.michero.daos;

import com.codeup.michero.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends CrudRepository<Post, Long> {
}