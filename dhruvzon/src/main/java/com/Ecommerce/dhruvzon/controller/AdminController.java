package com.Ecommerce.dhruvzon.controller;

import com.Ecommerce.dhruvzon.dto.user.UserResponseDTO;
import com.Ecommerce.dhruvzon.response.ApiResponse;
import com.Ecommerce.dhruvzon.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getAllUsers() {
        List<UserResponseDTO> allUsers = userService.getAllUsers();
        ApiResponse<List<UserResponseDTO>> response = new ApiResponse<>(allUsers, "All users fetched successfully");
        return ResponseEntity.ok(response);
    }

}
