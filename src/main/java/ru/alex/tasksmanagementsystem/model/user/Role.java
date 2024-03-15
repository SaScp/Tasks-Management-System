package ru.alex.tasksmanagementsystem.model.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "t_role")
@RequiredArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "role_id", nullable = false)
    private Long id;


    @JoinTable(name = "t_role_t_user",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns =@JoinColumn(name = "user_id") )
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users;
}
