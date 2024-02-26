package ru.alex.tasksmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.tasksmanagementsystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
