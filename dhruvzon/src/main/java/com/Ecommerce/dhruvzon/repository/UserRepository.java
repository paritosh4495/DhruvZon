package com.Ecommerce.dhruvzon.repository;

import com.Ecommerce.dhruvzon.enums.Status;
import com.Ecommerce.dhruvzon.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(@NotNull @NotEmpty @Email String email);

    List<User> findAllByStatus(@NotNull Status status);
}
