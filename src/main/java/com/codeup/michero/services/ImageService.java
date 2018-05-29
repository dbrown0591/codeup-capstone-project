package com.codeup.michero.services;

import com.codeup.michero.daos.ImageRepository;
import com.codeup.michero.models.Image;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    // note that we don't need separate insert and update methods.
    // the save method is smart enough to figure out which it needs to do
    // i.e. if the passed object already has an `id` property, update an
    // existing record, if it does not, insert a new record
    public void save(Image post) {
        imageRepository.save(post);
    }

    // we'll need to define the return type as `Iterable` as that is
    // what the CrudRepository defines. You can think of `Iterable` as
    // an even more generic list, it is still a collection of items
    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

    public Image findOne(long id) {
        return imageRepository.findOne(id);
    }

    public void delete(long id) {
        imageRepository.delete(id);
    }

    public Iterable<Image> findByConcert_id(long id){ return imageRepository.findByConcert_id(id); }
}
