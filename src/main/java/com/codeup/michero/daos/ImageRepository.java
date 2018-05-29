package com.codeup.michero.daos;

import com.codeup.michero.models.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> findByConcert_id(long id);
}
