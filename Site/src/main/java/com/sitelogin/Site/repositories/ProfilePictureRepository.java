package com.sitelogin.Site.repositories;

import com.sitelogin.Site.domain.ProfilePicture;
import com.sitelogin.Site.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.java2d.cmm.Profile;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, String> {

    List<ProfilePicture> findAll();

    @Transactional
    @Query(value = "SELECT * from image_tbl i WHERE i.user_id = :userId", nativeQuery = true)
    Optional<ProfilePicture> findPictureById(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE [bookster].[dbo].image_tbl SET [profile_pic] = :profile_pic_param WHERE [user_id]=:user_id_param", nativeQuery = true)
    int updatePicture(@Param("user_id_param") int user_id, @Param("profile_pic_param") String profile_pic);
/*
    @Modifying
    @Query(value="INSERT INTO [bookster].[dbo].image_tbl VALUES (:id_param, :picture_param) ", nativeQuery = true)
    int save(@Param("user_id") int user_id, @Param("picture_param") String profilePicture);
    */

}
