package org.example.instagramapp.model.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CreateUserRequest {
    @NotBlank
    String name;
    @NotBlank
    String username;
    @NotBlank
    String password;
    @NotBlank
    String phoneNumber;
    @NotBlank
    String email;
    @NotNull
    Integer activeTypeId;
}