package com.Ecommerce.dhruvzon.service.user;

import com.Ecommerce.dhruvzon.dto.user.UserRequestDTO;
import com.Ecommerce.dhruvzon.dto.user.UserResponseDTO;


import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    UserResponseDTO getUserById(Long id);
    UserResponseDTO getUserByEmail(String email);
    List<UserResponseDTO> getAllActiveUsers();
    List<UserResponseDTO> getAllUsers();
    String deleteUserById(Long id);

}
