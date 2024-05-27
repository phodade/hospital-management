package org.dnyanyog.controller;

import java.util.List;
import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.entity.Users;
import org.dnyanyog.service.DirectoryServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class DirectoryServiceController {

  @Autowired private DirectoryServiceServiceImpl directoryService;

  @PostMapping(
      path = "/api/v1/auth/user",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public AddUserResponse addUser(@Validated @RequestBody AddUserRequest userRequest) {
    return directoryService.addUpdateUser(userRequest).orElse(new AddUserResponse());
  }

  @PutMapping(
      path = "/api/v1/auth/user",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public AddUserResponse updateUser(@RequestBody AddUserRequest userRequest) {
    return directoryService.addUpdateUser(userRequest).orElse(new AddUserResponse());
  }

  @DeleteMapping("/api/v1/auth/user/{userId}")
  public void deleteUser(@PathVariable Long userId) {
    directoryService.deleteUser(userId);
  }

  @GetMapping("/api/v1/auth/user/{userId}")
  public AddUserResponse getUserById(@PathVariable Long userId) {
    return directoryService.getSingleUser(userId);
  }

  @GetMapping
  public List<Users> getAllUsers() {
    return directoryService.getAllUser();
  }

  @GetMapping("/api/v1/auth/user/search")
  public List<Users> searchUsers(
      @RequestParam(required = false) String email,
      @RequestParam(required = false) String username) {
    return directoryService.getFilteredUser(email, username);
  }
}
