package org.example.instagramapp.helper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.instagramapp.enums.ErrorResponseEnum;
import org.example.instagramapp.enums.PostType;
import org.example.instagramapp.model.entity.Post;
import org.example.instagramapp.model.entity.PostComment;
import org.example.instagramapp.model.entity.Users;
import org.example.instagramapp.model.exception.GeneralException;
import org.example.instagramapp.repository.PostRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.example.instagramapp.global.GlobalData.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PostServiceHelper {
    PostRepository postRepository;

    public Post getPostById(Integer id) {
        return postRepository.findById(id).orElseThrow(() ->
                new GeneralException(ErrorResponseEnum.POST_NOT_FOUND_ID));
    }

    public String[] getPartsByDot(String fileName) {
        return fileName.split("\\.");
    }

    public void checkExtension(String extension) {
        if (!(imageExtensions.contains(extension) ||
                videoExtensions.contains(extension)))
            throw new GeneralException(ErrorResponseEnum.FILE_TYPE_ERROR);
    }

    public String createPostName(Users user, String extension) {
        return mediaPath + user.getUsername() + "_" + user.getPosts().size() + "." + extension;
    }

    public void setPostType(Post post, String extension) {
        if (imageExtensions.contains(extension))
            post.setPostType(PostType.IMAGE.name());
        else
            post.setPostType(PostType.VIDEO.name());
    }

    public byte[] getFile(String fileName) throws IOException {
        return new ClassPathResource(mediaPathInResources + fileName)
                .getInputStream()
                .readAllBytes();
    }

    public MediaType getMediaType(String type) {
        return switch (type) {
            case "jpeg", "jpg" -> MediaType.IMAGE_JPEG;
            case "png" -> MediaType.IMAGE_PNG;
            default -> throw new GeneralException(ErrorResponseEnum.POST_NOT_SUITABLE_ERROR);
        };
    }

    public String getCommentResponse(PostComment postComment) {
        return postComment.getUser().getUsername() + ": "
                + postComment.getComment() + " "
                + ChronoUnit.HOURS.between(postComment.getCreatedAt(), LocalDateTime.now()) + "h";
    }

    public void checkIfLiked(Users user, Post post) {
        if (user.getPostLikes().contains(post))
            throw new GeneralException(ErrorResponseEnum.ALREADY_LIKED_ERROR);
    }

    public void setPostComment(PostComment postComment, String comment, Post post, Users user) {
        postComment.setComment(comment);
        postComment.setPost(post);
        postComment.setUser(user);
    }
}