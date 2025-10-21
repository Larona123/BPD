package com.bitri.co.bw.Bitri_Projects_Dash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
