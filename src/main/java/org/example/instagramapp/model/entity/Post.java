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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String postType;
    String postName;
    @Builder.Default
    Long totalLikes = 0L;
    @CreationTimestamp
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    @Builder.Default
    Long totalComments = 0L;
    @ManyToOne
    @JoinColumn(name = "user_id")
    Users user;
    @ManyToMany
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<Users> postLikes;
    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    List<PostComment> comments;
}