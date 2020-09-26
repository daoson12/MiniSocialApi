package com.VictorDaodu.SocialAPI.repository;

import com.VictorDaodu.SocialAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
