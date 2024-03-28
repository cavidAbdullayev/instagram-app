package org.example.instagramapp.repository;

import org.example.instagramapp.model.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FollowerRepository extends JpaRepository<Follower,Integer> {

    Follower findByUser_Id(Integer id);

}