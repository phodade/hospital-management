package org.dnyanyog.service;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.Optional;
import org.dnyanyog.common.ResponseCodes;
import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectoryServiceServiceImpl {

  Logger logger = LoggerFactory.getLogger(DirectoryServiceServiceImpl.class);
  @Autowired UsersRepository userRepo;
  @Autowired AddUserResponse userResponse;
  @Autowired private List<Long> userIds;

  public Optional<AddUserResponse> addUpdateUser(AddUserRequest request) {

    Users usersTable;
    if (request.getUserId() != null && userRepo.existsById(request.getUserId())) {
        // Update existing user
        usersTable = userRepo.findById(request.getUserId()).orElse(new Users());
        usersTable.setUsername(request.getUsername())
                  .setPassword(request.getPassword())
                  .setEmail(request.getEmail())
                  .setConfirm(request.getConfirm())
                  .setMobileNumber(request.getMobilenumber())
                  .setRole(request.getRole());
        userResponse.setMessage(ResponseCodes.UPDATE_USER_SUCCESS.getStatus());
        userResponse.setStatus(ResponseCodes.UPDATE_USER_SUCCESS.getMessage());
    } else {
        // Add new user
        usersTable = Users.getInstance()
                  .setUsername(request.getUsername())
                  .setPassword(request.getPassword())
                  .setEmail(request.getEmail())
                  .setConfirm(request.getConfirm())
                  .setMobileNumber(request.getMobilenumber())
                  .setRole(request.getRole());
        userResponse.setMessage(ResponseCodes.ADD_USER_SUCCESS.getStatus());
        userResponse.setStatus(ResponseCodes.ADD_USER_SUCCESS.getMessage());
    }

    usersTable = userRepo.save(usersTable);
    
    userResponse.setUserId(usersTable.getUserId());
    userResponse.getUserData().setEmail(usersTable.getEmail());
    userResponse.getUserData().setUsername(usersTable.getUsername());
    userResponse.getUserData().setPassword(usersTable.getPassword());
    userResponse.getUserData().setConfirm(usersTable.getConfirm());
    userResponse.getUserData().setRole(usersTable.getRole());
    userResponse.getUserData().setMobilenumber(usersTable.getMobileNumber());

    return Optional.of(userResponse);
  }

  public AddUserResponse getSingleUser(Long userId) {
    Optional<Users> receivedData = userRepo.findById(userId);

    if (receivedData.isEmpty()) {
      userResponse.setStatus(ResponseCodes.USER_NOT_FOUND.getStatus());
      userResponse.setMessage(ResponseCodes.USER_NOT_FOUND.getMessage());
    } else {
      Users user = receivedData.get();
      userResponse.setStatus(ResponseCodes.ADD_USER_SUCCESS.getStatus());
      userResponse.setMessage(ResponseCodes.ADD_USER_SUCCESS.getMessage());
      userResponse.setUserId(user.getUserId());
      userResponse.getUserData().setEmail(user.getEmail());
      userResponse.getUserData().setUsername(user.getUsername());
      userResponse.getUserData().setPassword(user.getPassword());
      userResponse.getUserData().setConfirm(user.getConfirm());
      userResponse.getUserData().setMobilenumber(user.getMobileNumber());
    }
    return userResponse;
  }

  public List<Users> getAllUser() {
    return userRepo.findAll();
  }

  public List<Long> getAllUserIds() {
    List<Users> users = userRepo.findAll();
    for (Users user : users) {
      if (nonNull(user)) {
        userIds.add(user.getUserId());
      }
    }
    return userIds;
  }

  public List<Users> getFilteredUser(String email, String username) {
    if (email != null && username != null) {
      return userRepo.findByUsingEmailAndUserName(email, username);
    } else if (email != null) {
      return userRepo.findByEmail(email);
    } else if (username != null) {
      return userRepo.findByUsername(username);
    } else {
      return userRepo.findAll();
    }
  }

  public void deleteUser(Long userId) {
    if (userRepo.existsById(userId)) {
      userRepo.deleteById(userId);
      userResponse.setStatus(ResponseCodes.USER_DELETED_SUCCESS.getStatus());
      userResponse.setMessage(ResponseCodes.USER_DELETED_SUCCESS.getMessage());
    } else {
      userResponse.setStatus(ResponseCodes.USER_NOT_FOUND.getStatus());
      userResponse.setMessage(ResponseCodes.USER_NOT_FOUND.getMessage());
    }
  }
  
  public AddUserResponse searchUserById(Long userId) {
	    Optional<Users> userOptional = userRepo.findById(userId);
	    
	    if (userOptional.isPresent()) {
	        Users user = userOptional.get();
	        userResponse.setStatus(ResponseCodes.USER_FOUND.getStatus());
	        userResponse.setMessage(ResponseCodes.USER_FOUND.getMessage());
	        userResponse.setUserId(user.getUserId());
	        userResponse.getUserData().setEmail(user.getEmail());
	        userResponse.getUserData().setUsername(user.getUsername());
	        userResponse.getUserData().setPassword(user.getPassword());
	        userResponse.getUserData().setConfirm(user.getConfirm());
	        userResponse.getUserData().setRole(user.getRole());
	        userResponse.getUserData().setMobilenumber(user.getMobileNumber());
	    } else {
	        userResponse.setStatus(ResponseCodes.USER_NOT_FOUND.getStatus());
	        userResponse.setMessage(ResponseCodes.USER_NOT_FOUND.getMessage());
	    }
	    
	    return userResponse;
	}

}
