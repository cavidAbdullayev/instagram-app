package org.example.instagramapp.model.request.resetPassword;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResetPasswordRequest {
    @NotEmpty(message = "New password is important!")
    @Pattern(regexp = "^[a-zA-z0-9]{8,}")
    String newPassword;
    @NotEmpty(message = "Repeat password is important!")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,}")

    String repeatPassword;
}