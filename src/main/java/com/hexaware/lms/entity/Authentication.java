package com.hexaware.lms.entity;

import com.hexaware.lms.utils.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authentication")
@Builder
public class Authentication {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authentication_id_seq")
    private Long id;
    private String email;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @PreRemove
    private void removeAssociation() {
        this.user.setAuthentication(null);
    }
}
