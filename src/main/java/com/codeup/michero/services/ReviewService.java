package com.codeup.michero.services;

import com.codeup.michero.daos.PostDao;
import com.codeup.michero.models.Review;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private PostDao postDao;

    public ReviewService(PostDao postDao) {
        this.postDao = postDao;
    }

    // note that we don't need separate insert and update methods.
    // the save method is smart enough to figure out which it needs to do
    // i.e. if the passed object already has an `id` property, update an
    // existing record, if it does not, insert a new record
    public void save(Review post) {
        postDao.save(post);
    }

    // we'll need to define the return type as `Iterable` as that is
    // what the CrudRepository defines. You can think of `Iterable` as
    // an even more generic list, it is still a collection of items
    public Iterable<Review> findAll() {
        return postDao.findAll();
    }

    public Review findOne(long id) {
        return postDao.findOne(id);
    }

    public void delete(long id) {
        postDao.delete(id);
    }
}
