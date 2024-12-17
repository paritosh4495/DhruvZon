package com.Ecommerce.dhruvzon.controller;
import com.Ecommerce.dhruvzon.dto.user.UserRequestDTO;
import com.Ecommerce.dhruvzon.dto.user.UserResponseDTO;
import com.Ecommerce.dhruvzon.response.ApiResponse;
import com.Ecommerce.dhruvzon.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(
           @Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);
        ApiResponse<UserResponseDTO> response = new ApiResponse<>(createdUser, "User created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO updatedUser = userService.updateUser(id, userRequestDTO);
        ApiResponse<UserResponseDTO> response = new ApiResponse<>(updatedUser, "User updated successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        ApiResponse<UserResponseDTO> response = new ApiResponse<>(user, "User fetched successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/email")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserByEmail(@RequestParam String email) {
        UserResponseDTO user = userService.getUserByEmail(email);
        ApiResponse<UserResponseDTO> response = new ApiResponse<>(user, "User fetched successfully by email");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getAllActiveUsers() {
        List<UserResponseDTO> activeUsers = userService.getAllActiveUsers();
        ApiResponse<List<UserResponseDTO>> response = new ApiResponse<>(activeUsers, "All active users fetched successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getAllUsers() {
        List<UserResponseDTO> allUsers = userService.getAllUsers();
        ApiResponse<List<UserResponseDTO>> response = new ApiResponse<>(allUsers, "All users fetched successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUserById(@PathVariable Long id) {
        String message = userService.deleteUserById(id);
        ApiResponse<String> response = new ApiResponse<>(message, "User deleted successfully");
        return ResponseEntity.ok(response);
    }
}
