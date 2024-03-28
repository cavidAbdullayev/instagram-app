package org.example.instagramapp.model.request.email;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EmailRequest {
    String to;
    String subject;
    String text;
}