package com.Ecommerce.dhruvzon.dto.user;

import com.Ecommerce.dhruvzon.enums.Role;
import com.Ecommerce.dhruvzon.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private Role role;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}
