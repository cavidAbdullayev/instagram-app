package org.example.instagramapp.repository;

import org.example.instagramapp.model.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment,Integer> {

    List<PostComment> findAllByPost_Id(Integer id);
}