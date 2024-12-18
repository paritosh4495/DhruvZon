package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.user.UserRequestDTO;
import com.Ecommerce.dhruvzon.dto.user.UserResponseDTO;
import com.Ecommerce.dhruvzon.enums.Status;
import com.Ecommerce.dhruvzon.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    // Map UserRequestDTO to User
    public User userRequestDTOToUser(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) {
            return null;
        }

        User user = new User();
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());  // Password should be encoded before saving
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        user.setAddress(userRequestDTO.getAddress());
        user.setRole(userRequestDTO.getRole());
        user.setStatus(Status.ACTIVE);  // Default status

        return user;
    }

    // Map User to UserResponseDTO
    public UserResponseDTO userToUserResponseDTO(User user) {
        if (user == null) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setPhoneNumber(user.getPhoneNumber());
        userResponseDTO.setAddress(user.getAddress());
        userResponseDTO.setRole(user.getRole());
        userResponseDTO.setStatus(user.getStatus());
        userResponseDTO.setCreatedDate(user.getCreatedDate());
        userResponseDTO.setModifiedDate(user.getModifiedDate());

        return userResponseDTO;
    }

}
