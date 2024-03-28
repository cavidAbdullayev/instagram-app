package org.example.instagramapp.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    @Column(unique = true)
    String username;
    String password;
    String phoneNumber;
    String activeType;
    @Builder.Default
    Integer followerCount = 0;
    @Builder.Default
    Integer followingCount = 0;
    @Column(unique = true)
    String email;
    @CreationTimestamp
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Boolean isEnable;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    List<Token> tokens;
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    List<Follower> followers;
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    List<Post> posts;
    @ManyToMany(mappedBy = "postLikes")
    List<Post> postLikes;
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    List<Post> postComments;
}