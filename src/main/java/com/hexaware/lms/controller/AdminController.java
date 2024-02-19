package com.hexaware.lms.controller;

import com.hexaware.lms.dto.AddCategoryRequestBody;
import com.hexaware.lms.dto.CategoryDTO;
import com.hexaware.lms.dto.NotificationDTO;
import com.hexaware.lms.exception.ResourceNotFoundException;
import com.hexaware.lms.service.AdminService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin")
public class AdminController {

    private final AdminService adminService;

    @Operation(
            description = "Get endpoint for admin",
            summary = "This is a summary for admin get endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }

    )
    // needed to be corrected
    @GetMapping(path = "/getCategory")
    public ResponseEntity<List<CategoryDTO>> getCategory() throws FileNotFoundException {
        List<CategoryDTO> response = adminService.findAll();
//        throw new FileNotFoundException();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //needed to be corrected
    @PostMapping(path = "/addCategory")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody AddCategoryRequestBody request){
        CategoryDTO response = adminService.addCategory(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping(path = "/deleteCategory/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") long id ) throws ResourceNotFoundException {
        try {
            adminService.deleteCategory(id);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/returnRequest")
    public ResponseEntity<NotificationDTO> returnRequest(@RequestBody NotificationDTO notificationDTO) throws ResourceNotFoundException {
        try{
            NotificationDTO savedDto = adminService.sendReturnRequest(notificationDTO);
            return new ResponseEntity(savedDto,HttpStatus.OK);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}