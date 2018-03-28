package com.codeup.michero.daos;

import com.codeup.michero.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostDao extends CrudRepository<Post, Long> {
}
