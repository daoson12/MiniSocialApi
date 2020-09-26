package com.VictorDaodu.SocialAPI.repository;

import com.VictorDaodu.SocialAPI.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository <Post, Long> {
}
