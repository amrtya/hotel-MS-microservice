package com.hotelms.backend.usermodule.repository;

import com.hotelms.backend.usermodule.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<UserModel, String> {

    @Query(value = "SELECT u FROM UserModel u WHERE u.email = ?1 AND u.mobileNo = ?2")
    Optional<UserModel> findByEmailAndMobile(String email, String mobile);

    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByMobileNo(String mobile);
}
