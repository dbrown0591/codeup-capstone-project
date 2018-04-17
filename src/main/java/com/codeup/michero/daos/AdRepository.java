package com.codeup.michero.daos;

import com.codeup.michero.models.Ad;
import org.springframework.data.repository.CrudRepository;

public interface AdRepository extends CrudRepository<Ad, Long> {
}
