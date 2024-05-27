package org.dnyanyog.service;

import java.util.List;
import org.dnyanyog.common.ResponseCodes;
import org.dnyanyog.dto.LoginRequest;
import org.dnyanyog.dto.LoginResponse;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

  @Autowired UsersRepository userRepo;

  public LoginResponse validateUser(LoginRequest loginRequest) {

    LoginResponse response = new LoginResponse();

    List<Users> liUser =
        userRepo.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());

    if (liUser.size() == 1) {
      response.setStatus(ResponseCodes.USER_LOGIN_SUCCESS.getStatus());
      response.setMessage(ResponseCodes.USER_LOGIN_SUCCESS.getMessage());
    } else {
      response.setStatus(ResponseCodes.USER_LOGIN_FAIL.getStatus());
      response.setMessage(ResponseCodes.USER_LOGIN_FAIL.getMessage());
    }
    return response;
  }
}
