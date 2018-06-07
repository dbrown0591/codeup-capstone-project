package com.codeup.michero.controller;

import com.codeup.michero.models.Concert;
import com.codeup.michero.models.Image;
import com.codeup.michero.services.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class FileUploadController {

    private final ImageService imageService;

    public FileUploadController(ImageService is){
        this.imageService = is;
    }


//    private String uploadPath = "/Users/damionbrown/IdeaProjects/michero/src/main/resources/static/uploads/images";

    @Value("${file-upload-path}")
    private String uploadPath;

    @RequestMapping("/imageupload")
    public void uploadImages(HttpServletRequest req,
                             @ModelAttribute Concert c, Model m,
                             List<MultipartFile> images_list){
        // get images
        List<MultipartFile> images = images_list;



        // store images
        if(images != null && images.size() > 0){
            for(MultipartFile mf : images){

                // get file name
                String fileName = mf.getOriginalFilename();

                // get file path
                //String filePath = Paths.get(uploadPath, fileName).toString();
                //String destPath = this.getClass().getClassLoader().getResource("static/uploads/images").toString();

                System.out.println("value of original filename:"+fileName); // debug
                System.out.println("value of directory:"+uploadPath); // debug

                // transfer file to destination
                File destFile = new File(uploadPath + "/" + fileName);
                System.out.println("destFile: " + destFile);
                try{
                    mf.transferTo(destFile);
                } catch(IOException e){
                    e.printStackTrace();
                }

                // save the image and file path in the database
                Image image = new Image();
                image.setUrl("/"+fileName);
                image.setConcert(c);
                this.imageService.save(image);
            }
        }


    } // end uploadImages()

} // end class