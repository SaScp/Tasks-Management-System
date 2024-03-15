package ru.alex.tasksmanagementsystem.service.update;

import ru.alex.tasksmanagementsystem.dto.UserDto;
import ru.alex.tasksmanagementsystem.model.user.User;

public interface UpdateComponentStrategy {
    void execute(UserDto updateEntity, User entity);
}
