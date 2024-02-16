package com.hexaware.lms.controller;

import com.hexaware.lms.dto.AddCategoryRequestBody;
import com.hexaware.lms.dto.CategoryDTO;
import com.hexaware.lms.service.AdminService;
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
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String get() {
        return "GET:: admin controller";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")

    public String post() {
        return "POST:: admin controller";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")

    public String put() {
        return "PUT:: admin controller";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    public String delete() {
        return "DELETE:: admin controller";
    }

    @GetMapping(path = "/getCategory")
    public ResponseEntity<List<CategoryDTO>> getCategory() throws FileNotFoundException {
        List<CategoryDTO> response = adminService.findAll();
//        throw new FileNotFoundException();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/addCategory")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody AddCategoryRequestBody request){
        CategoryDTO response = adminService.addCategory(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}