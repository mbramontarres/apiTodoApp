package com.exerciciApi.todoApi.repository;

import com.exerciciApi.todoApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
