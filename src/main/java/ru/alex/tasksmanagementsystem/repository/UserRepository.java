package ru.alex.tasksmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.tasksmanagementsystem.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
