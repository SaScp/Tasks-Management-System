package ru.alex.tasksmanagementsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "id", nullable = false)
    private String id;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name", nullable = false)
    @Size(min = 4, max = 30, message = "first name must be minimum 4 characters and maximum 30 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(min = 4, max = 30, message = "last name must be minimum 4 characters and maximum 30 characters")
    private String lastName;

    @Column(name = "password", nullable = false)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,20}$", message = "password must be constructed by pattern!")
    private String password;

    @Column(name = "password", nullable = false)
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$", message = "number phone must be constructed by pattern!")
    private String numberPhone;
}
