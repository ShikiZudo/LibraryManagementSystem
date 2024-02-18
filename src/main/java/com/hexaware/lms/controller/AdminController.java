package com.hexaware.lms.controller;

import com.hexaware.lms.dto.*;
import com.hexaware.lms.exception.ResourceNotFoundException;
import com.hexaware.lms.service.AdminService;
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
    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String get() {
        return "GET:: admin controller";
    }


    // needed to be corrected
    @GetMapping(path = "/getCategory")
    public ResponseEntity<List<CategoryDTO>> getCategory() throws FileNotFoundException {
        List<CategoryDTO> response = adminService.findAll();
//        throw new FileNotFoundException();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //needed to be corrected
    @PostMapping(path = "/addCategory")
    public ResponseEntity<CategoryDTO> addCategory(
            @RequestBody AddCategoryRequestBody request
    ){
        CategoryDTO response = adminService.addCategory(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping(path = "/deleteCategory/{id}")
    public ResponseEntity deleteCategory(
            @PathVariable("id") long id
    ) throws ResourceNotFoundException {
        try {
            adminService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/returnRequest/{id}")
    public ResponseEntity<NotificationDTO> returnRequest(
            @RequestBody NotificationDTO notificationDTO,
            @PathVariable("id") long id
    ) throws ResourceNotFoundException {
        try{
            NotificationDTO savedDto = adminService.sendReturnRequest(notificationDTO, id);
            return new ResponseEntity(savedDto,HttpStatus.OK);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/bookLoanHistory/{bookId}")
    public ResponseEntity<List<BookLoanHistoryDTO>> bookLoanHistory(
            @PathVariable("bookId") long bookId
    ) throws ResourceNotFoundException {
        try{
            List<BookLoanHistoryDTO> bookLoanHistoryList = adminService.getBookLoanHistory(bookId);
            return new ResponseEntity<>(bookLoanHistoryList,HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/bookReservationHistory/{bookId}")
    public ResponseEntity<List<BookReservationHistoryDTO>> bookReservationHistory(
            @PathVariable("bookId") long bookId
    ) throws ResourceNotFoundException {
        try{
            List<BookReservationHistoryDTO> bookLoanHistoryList = adminService.getBookReservationHistory(bookId);
            return new ResponseEntity<>(bookLoanHistoryList,HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/userLoanHistory/{userId}")
    public ResponseEntity<List<UserLoanHistoryDTO>> userLoanHistory(
            @PathVariable("userId") long userId
    ) throws ResourceNotFoundException {
        try{
            List<UserLoanHistoryDTO> userLoanHistoryList = adminService.getUserLoanHistory(userId);
            return new ResponseEntity<>(userLoanHistoryList,HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/userFine/{userId}")
    public ResponseEntity<List<fineDTO>> userFine(
            @PathVariable("userId") long userId
    ) throws ResourceNotFoundException {
        try{
            List<fineDTO> userFineList = adminService.getUserFine(userId);
            return new ResponseEntity<>(userFineList,HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/totalFine")
    public ResponseEntity<List<fineDTO>> totalFine(
    ) throws ResourceNotFoundException {
        try{
            List<fineDTO> userFineList = adminService.getTotalFine();
            return new ResponseEntity<>(userFineList,HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}