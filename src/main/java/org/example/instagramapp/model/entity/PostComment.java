package org.example.instagramapp.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Lob
    String comment;
    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;
    @CreationTimestamp
    LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    Users user;
}