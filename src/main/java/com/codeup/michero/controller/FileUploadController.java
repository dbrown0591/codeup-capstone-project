package com.codeup.michero.controller;

import com.codeup.michero.daos.ImageRepository;
import com.codeup.michero.models.Concert;
import com.codeup.michero.models.Image;
import com.codeup.michero.services.ImageService;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileUploadController {

    @Value("${file-upload-path}")
    private static String uploadPath = "/Users/tmp/uploads";
    private final ImageService imageService;

    public FileUploadController(ImageService is){
        this.imageService = is;
    }

    @RequestMapping("/imageupload")
    public void uploadImages(HttpServletRequest req,
                             @ModelAttribute Concert c, Model m,
                             List<MultipartFile> images_list){
        // get images
        List<MultipartFile> images = images_list;
        List<String> imageFileNames = new ArrayList<String>();

        // store images
        if(images != null && images.size() > 0){
            for(MultipartFile mf : images){
                // save file name
                String fileName = mf.getOriginalFilename();
                imageFileNames.add(fileName);

                // save image
                //String url = req.getServletContext().getRealPath("/image")+fileName;
                //File imageFile = new File(uploadPath+"/image/"+fileName);
                String filePath = Paths.get(uploadPath, fileName).toString();
                File destFile = new File(filePath);
                try{
                    mf.transferTo(destFile);
                } catch(IOException e){
                    e.printStackTrace();
                }

                // save the image, url, and relationship in the database
                Image image = new Image();
                image.setUrl(filePath);
                image.setConcert(c);
                this.imageService.save(image);
            }
        }


    } // end uploadImages()

} // end class
