package org.example.instagramapp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    ResponseEntity<String> sharing(MultipartFile file) throws IOException;

    ResponseEntity<byte[]> showImage(Integer id);

    ResponseEntity<String> like(Integer postId);

    ResponseEntity<String> comment(Integer postId, String comment);

    List<String> getAllComments(Integer postId);
}