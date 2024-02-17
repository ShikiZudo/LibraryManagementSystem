package com.hexaware.lms.service.impl;

import com.hexaware.lms.Mapper.impl.CategoryMapper;
import com.hexaware.lms.Mapper.impl.NotificationMapper;
import com.hexaware.lms.dto.AddCategoryRequestBody;
import com.hexaware.lms.dto.CategoryDTO;
import com.hexaware.lms.dto.NotificationDTO;
import com.hexaware.lms.entity.Category;
import com.hexaware.lms.entity.Notification;
import com.hexaware.lms.entity.User;
import com.hexaware.lms.exception.ResourceNotFoundException;
import com.hexaware.lms.repository.BookRepository;
import com.hexaware.lms.repository.CategoryRepository;
import com.hexaware.lms.repository.NotificationRepository;
import com.hexaware.lms.repository.UserRepository;
import com.hexaware.lms.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final NotificationRepository notificationRepository;
    private final CategoryMapper categoryMapper;
    private final NotificationMapper notificationMapper;
    @Override
    public List<CategoryDTO> findAll() {
        List<Category> resultEntity = categoryRepository.findAll();
        List<CategoryDTO> resultDto = resultEntity.stream().map(it-> categoryMapper.mapTo(it)).collect(Collectors.toList());
        return resultDto;
    }

    @Override
    public CategoryDTO addCategory(AddCategoryRequestBody request) {
        Category newCategory = new Category(request.getCategory());
        Category savedCategory = categoryRepository.save(newCategory);
        return categoryMapper.mapTo(savedCategory);
    }

    @Override
    public void deleteCategory(long id) throws ResourceNotFoundException {
        if(!categoryRepository.existsById(id)) throw new ResourceNotFoundException("bookId","id",id);
        categoryRepository.deleteById(id);
    }

//    @Override
//    public void sendReturnRequest(long userId, long bookId) throws ResourceNotFoundException {
//        if(!bookRepository.existsById(bookId)) throw new ResourceNotFoundException("bookId","id",bookId);
//        if(!userRepository.existsById(userId)) throw new ResourceNotFoundException("userId","id",userId);
//
//        Optional<User> user = userRepository.findById(userId);
//        Notification notification = new Notification(1L,"Dear user, you have been late in submitting this book. please kindly submit this book along with the fine involved.",false, NotificationType.ALERT,user.get());
////        Notification notify = new Notification(b)
//        notificationRepository.save();
//    }

    @Override
    public NotificationDTO sendReturnRequest(NotificationDTO notificationDTO) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(notificationDTO.getUser().getId());
        if(user.isEmpty()) throw new ResourceNotFoundException("userId","id",notificationDTO.getUser().getId());

        Notification notificationEntity = notificationMapper.mapFrom(notificationDTO);

        Notification savedEntity = notificationRepository.save(notificationEntity);
        return notificationMapper.mapTo(savedEntity);
    }
}

