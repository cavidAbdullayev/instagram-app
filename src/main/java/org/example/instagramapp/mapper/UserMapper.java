package org.example.instagramapp.mapper;

import org.example.instagramapp.model.entity.Users;
import org.example.instagramapp.model.request.user.CreateUserRequest;
import org.example.instagramapp.model.response.user.GetUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    Users mapToUser(CreateUserRequest req);

    GetUserResponse mapToResponse(Users user);
}