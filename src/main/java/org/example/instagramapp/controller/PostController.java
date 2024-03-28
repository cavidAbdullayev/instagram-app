package org.example.instagramapp.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.instagramapp.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PostController {
    PostService postService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestPart("file") MultipartFile file) throws IOException {
        return postService.sharing(file);
    }

    @GetMapping("/show-image/{id}")
    public ResponseEntity<byte[]> showImage(@PathVariable Integer id) {
        return postService.showImage(id);
    }

    @PostMapping("/like/{postId}")
    public ResponseEntity<String> like(@PathVariable Integer postId) {
        return postService.like(postId);
    }

    @PostMapping("/comment/{postId}")
    public ResponseEntity<String> comment(@PathVariable Integer postId,
                                          @RequestParam String comment) {
        return postService.comment(postId, comment);
    }

    @GetMapping("/show-all-comments/{postId}")
    public List<String> showAllComments(@PathVariable Integer postId) {
        return postService.getAllComments(postId);
    }
}