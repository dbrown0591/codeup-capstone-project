package com.codeup.michero.daos;

import com.codeup.michero.controller.Ad;
import org.springframework.data.repository.CrudRepository;

public interface AdRepository extends CrudRepository<Ad, Long> {
}
