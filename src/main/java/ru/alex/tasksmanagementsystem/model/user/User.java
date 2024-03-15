package ru.alex.tasksmanagementsystem.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alex.tasksmanagementsystem.model.BaseDateEntity;

import java.util.Set;

/**
 * @author Alexander
 * */

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user")
public class User extends BaseDateEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
     private String password;

    @Column(name = "numberPhone")
    private String numberPhone;

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;
}
