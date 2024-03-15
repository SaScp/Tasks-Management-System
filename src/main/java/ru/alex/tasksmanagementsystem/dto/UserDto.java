package ru.alex.tasksmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.alex.tasksmanagementsystem.model.Create;
import ru.alex.tasksmanagementsystem.model.Find;
import ru.alex.tasksmanagementsystem.model.Update;

@Data
public class UserDto {

    private String id;

    @Email(groups = {Create.class, Update.class, Find.class})
    private String email;

    @NotNull(groups = Create.class)
    @Size(min = 4, max = 30, message = "first name must be minimum 4 characters and maximum 30 characters", groups = {Create.class, Update.class})
    private String firstName;

    @NotNull(groups = Create.class)
    @Size(min = 4, max = 30, message = "last name must be minimum 4 characters and maximum 30 characters", groups = {Create.class, Update.class})
    private String lastName;

    @NotNull(groups = {Create.class, Find.class})
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,20}$",
            message = "password must be constructed by pattern!",
            groups = {Create.class, Update.class, Find.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordConfirmation;

    @NotNull(groups = Create.class)
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$",
            message = "number phone must be constructed by pattern!", groups = {Create.class, Update.class})
    private String numberPhone;
}
