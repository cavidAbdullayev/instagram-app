package org.example.instagramapp.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.example.instagramapp.helper.PostServiceHelper;
import org.example.instagramapp.helper.UserServiceHelper;
import org.example.instagramapp.model.entity.Post;
import org.example.instagramapp.model.entity.PostComment;
import org.example.instagramapp.model.entity.Users;
import org.example.instagramapp.repository.PostCommentRepository;
import org.example.instagramapp.repository.PostRepository;
import org.example.instagramapp.repository.UserRepository;
import org.example.instagramapp.service.PostService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Objects;

import static org.example.instagramapp.global.GlobalData.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PostServiceImpl implements PostService {
    PostRepository postRepository;
    UserRepository userRepository;
    PostCommentRepository postCommentRepository;
    PostServiceHelper postServiceHelper;
    UserServiceHelper userServiceHelper;

    @SneakyThrows
    @Transactional
    public ResponseEntity<String> sharing(MultipartFile file) {
        userServiceHelper.checkCurrentUser();
        byte[] bytes = file.getInputStream().readAllBytes();
        String[] fileNameParts = postServiceHelper.getPartsByDot(Objects.
                requireNonNull(file.getOriginalFilename()));
        String extension = Objects.requireNonNull(fileNameParts)[fileNameParts.length - 1];
        postServiceHelper.checkExtension(extension);
        Users user = userRepository.getReferenceById(currentUserId);
        String postName = postServiceHelper.createPostName(user, extension);
        try (FileOutputStream outputStream = new FileOutputStream(postName)) {
            outputStream.write(bytes);
        }
        Post post = new Post();
        post.setUser(user);
        post.setPostName(postName);
        postServiceHelper.setPostType(post, extension);
        postRepository.save(post);
        return ResponseEntity.ok(postSharing);
    }

    @SneakyThrows
    @Override
    public ResponseEntity<byte[]> showImage(Integer id) {
        userServiceHelper.checkCurrentUser();
        Post post = postServiceHelper.getPostById(id);
        String imageName = post.getPostName();
        String[] imageNameParts = imageName.split("\\\\");
        int length = imageNameParts.length;
        String imageExtension = postServiceHelper.getPartsByDot(imageNameParts[length - 1])
                [postServiceHelper.getPartsByDot(imageNameParts[length - 1]).length - 1];
        byte[] file = postServiceHelper.getFile(imageNameParts[length - 1]);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(postServiceHelper.getMediaType(imageExtension));
        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> like(Integer postId) {
        userServiceHelper.checkCurrentUser();
        Post post = postServiceHelper.getPostById(postId);
        Users user = userRepository.getReferenceById(currentUserId);
        postServiceHelper.checkIfLiked(user, post);
        post.getPostLikes().add(user);
        post.setTotalLikes(post.getTotalLikes() + 1);
        userRepository.save(user);
        postRepository.save(post);
        return ResponseEntity.ok(postLiked);
    }

    @Override
    public ResponseEntity<String> comment(Integer postId, String comment) {
        userServiceHelper.checkCurrentUser();
        Post post = postServiceHelper.getPostById(postId);
        Users user = userRepository.getReferenceById(currentUserId);
        PostComment postComment = new PostComment();
        postServiceHelper.setPostComment(postComment, comment, post, user);
        post.setTotalComments(post.getTotalComments() + 1);
        postRepository.save(post);
        postCommentRepository.save(postComment);
        return ResponseEntity.ok(commentPosted);
    }

    @Override
    public List<String> getAllComments(Integer postId) {
        userServiceHelper.checkCurrentUser();
        return postCommentRepository.findAllByPost_Id(postId)
                .stream()
                .map(postServiceHelper::getCommentResponse)
                .toList();
    }
}