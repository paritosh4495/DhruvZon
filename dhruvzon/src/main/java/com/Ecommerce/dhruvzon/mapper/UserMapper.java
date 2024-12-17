package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.user.UserRequestDTO;
import com.Ecommerce.dhruvzon.dto.user.UserResponseDTO;
import com.Ecommerce.dhruvzon.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userRequestDTOToUser(UserRequestDTO userRequestDTO);

    UserResponseDTO userToUserResponseDTO(User user);
}
