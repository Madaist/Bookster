package com.sitelogin.Site.services;

import com.sitelogin.Site.domain.ProfilePicture;
import com.sitelogin.Site.repositories.ProfilePictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfilePicService {

    @Autowired
    private ProfilePictureRepository pictureRepository;
/*
    public List<String> getAllPictures(){
        List<String> pictures = new ArrayList<>();
        pictureRepository.findAll().forEach(pictures::add);
        return pictures;
    }
*/
    public void addPicture(ProfilePicture picture) throws Exception {
        try {
            pictureRepository.save(picture);
        } catch (Exception exception) {
            throw new Exception("could not save the picture");
        }
    }

    public void updatePicture(int ID, String picture){
        int result = pictureRepository.updatePicture(ID, picture);
    }
/*
    public void deletePiture(int id){
        pictureRepository.deleteById(id);
    }
*/
/*
    public String findPictureById(int id) {
        String picture = null;
        Optional<String> optionalPicture = pictureRepository.findPictureById(id);
        if (optionalPicture.isPresent())
            picture = optionalPicture.get();

        return picture;
    }

 */
}
