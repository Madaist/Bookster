package com.sitelogin.Site.services;

import com.sitelogin.Site.domain.Booking;
import com.sitelogin.Site.domain.User;
import com.sitelogin.Site.repositories.UserRepository;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingService bookingService;

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void addUser(User user) throws Exception {
        try {
            userRepository.save(user);
        } catch (Exception exception) {
            log.error("could not save the user", user.getEmail(), exception);
            throw new Exception("could not save the user ");
        }
    }

    public void updateUser(int ID, User user){
        int result = userRepository.updateUser(ID, user.getFirstName(), user.getLastName(),
                                                user.getEmail(), user.getPassword(), user.getUserName());
    }

    public void deleteUser(int userId){
        userRepository.deleteById(userId);
    }

    public User findUserById(int id) {
        User user = null;
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent())
            user = optionalUser.get();

        return user;
    }


    public String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }



    public BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }


    public boolean deleteAccount(HttpSession session){
        int userId = (Integer)session.getAttribute("userId");
        boolean userExists = bookingService.checkBookingForUser(userId);
        if(userExists) //userul are carti imprumutate, deci nu are voie sa isi stearga contul
            return false; //nu s-a putut sterge contul
        else {
            deleteUser(userId);
            return true;
        }
    }


}
