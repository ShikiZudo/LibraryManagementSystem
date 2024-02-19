package com.hexaware.lms.dto;

import com.hexaware.lms.entity.User;
import com.hexaware.lms.utils.NotificationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDTO {
    private Long id;
    private String message;
    private boolean seen;

    @Enumerated(value = EnumType.STRING)
    private NotificationType type;

    private User user;
}
