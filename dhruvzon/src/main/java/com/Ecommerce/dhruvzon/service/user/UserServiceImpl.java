package com.Ecommerce.dhruvzon.service.user;

import com.Ecommerce.dhruvzon.dto.user.UserRequestDTO;
import com.Ecommerce.dhruvzon.dto.user.UserResponseDTO;
import com.Ecommerce.dhruvzon.enums.Role;
import com.Ecommerce.dhruvzon.enums.Status;
import com.Ecommerce.dhruvzon.exception.EmailAlreadyExistsException;
import com.Ecommerce.dhruvzon.exception.UserNotFoundException;
import com.Ecommerce.dhruvzon.mapper.UserMapper;
import com.Ecommerce.dhruvzon.model.User;
import com.Ecommerce.dhruvzon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) {
            throw new IllegalArgumentException("User data cannot be null");
        }
        if(userRepository.existsByEmail(userRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        if (userRequestDTO.getPassword() == null || userRequestDTO.getPassword().isEmpty() || userRequestDTO.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        System.out.println("----------------------+++++________________________++++++++++++");
        System.out.println(userRequestDTO.getPassword());


        User user = userMapper.userRequestDTOToUser(userRequestDTO);

        System.out.println("()))))))))))))))))))))))))))))()(");
        System.out.println(user.getPassword()+" BASTARD PARI");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role defaultRole = Role.USER;
        user.setRole(defaultRole);
        User savedUser = userRepository.save(user);
        return userMapper.userToUserResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("User with id " + id + " not found"));
        existingUser.setFirstName(userRequestDTO.getFirstName());
        existingUser.setLastName(userRequestDTO.getLastName());
        existingUser.setAddress(userRequestDTO.getAddress());
        existingUser.setPhoneNumber(userRequestDTO.getPhoneNumber());

        User updatedUser = userRepository.save(existingUser);
        return userMapper.userToUserResponseDTO(updatedUser);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("User with id " + id + " not found"));
        return userMapper.userToUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User with email " + email + " not found"));
        return userMapper.userToUserResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllActiveUsers() {
        List<User> users = userRepository.findAllByStatus(Status.ACTIVE);
        return users.stream().map(userMapper::userToUserResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::userToUserResponseDTO).collect(Collectors.toList());
    }

    @Override
    public String deleteUserById(Long id) {
        userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User Not found with id: " + id));
        userRepository.deleteById(id);
        return "User deleted";
    }
}
