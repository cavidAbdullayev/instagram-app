package org.example.instagramapp.model.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetUserResponse {
    String name;
    String username;
    Integer totalPost;
    Integer totalFollowers;
    String activeType;
}