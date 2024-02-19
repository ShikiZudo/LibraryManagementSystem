package com.hexaware.lms.entity;

import com.hexaware.lms.utils.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification")
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id_seq")
    private Long id;
    private String message;
    private boolean seen;

    @Enumerated(value = EnumType.STRING)
    private NotificationType type;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}