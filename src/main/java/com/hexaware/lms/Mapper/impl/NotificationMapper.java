package com.hexaware.lms.Mapper.impl;

import com.hexaware.lms.Mapper.Mapper;
import com.hexaware.lms.dto.NotificationDTO;
import com.hexaware.lms.entity.Notification;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationMapper implements Mapper<Notification, NotificationDTO> {

    private final ModelMapper modelMapper;
    @Override
    public NotificationDTO mapTo(Notification notification) {
        return modelMapper.map(notification,NotificationDTO.class);
    }

    @Override
    public Notification mapFrom(NotificationDTO notificationDTO) {
        return modelMapper.map(notificationDTO,Notification.class);
    }
}
