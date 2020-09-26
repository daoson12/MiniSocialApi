package com.VictorDaodu.SocialAPI.controller;

import com.VictorDaodu.SocialAPI.exception.ResourceNotFoundException;
import com.VictorDaodu.SocialAPI.model.Post;
import com.VictorDaodu.SocialAPI.model.User;
import com.VictorDaodu.SocialAPI.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {
    @Autowired
    private PostService postService;


    @GetMapping("/posts")
    public List<Post> getAllPost() {
        return postService.getPostRepository().findAll();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "id") Long postId) throws ResourceNotFoundException {
        Post post = postService.getPostRepository().findById(postId).orElseThrow(() -> new ResourceNotFoundException("post not Found for this id ::" + postId));
        return ResponseEntity.ok().body(post);
    }

    @PostMapping("/add/post")
    public Post createUser(@RequestBody Post post) {
        return postService.getPostRepository().save(post);
    }

    @PutMapping("/edit/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable(value = "id") Long postId, @RequestBody Post postDetails)
            throws ResourceNotFoundException {
        Post post = postService.getPostRepository().findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id:: " + postId));

        post.setDetails(postDetails.getDetails());
        post.setPostdDate(postDetails.getPostdDate());
        post.setUser(postDetails.getUser());

//        user.setEmail(userDetails.getEmail());
//        user.setFirstName(userDetails.getFirstName());
//        user.setLastName(userDetails.getLastName());
//        user.setLocation(userDetails.getLocation());
//        final User updatedUser = userService.getUserRepository().save(user);

        return ResponseEntity.ok(postDetails);
    }

    @DeleteMapping("/posts/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long postId)
            throws ResourceNotFoundException {
        Post post = postService.getPostRepository().findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + postId));

        postService.getPostRepository().delete(post);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}

