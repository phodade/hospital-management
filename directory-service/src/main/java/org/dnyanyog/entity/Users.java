package org.dnyanyog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users {
  @GeneratedValue
  @Id
  @Column(name = "userCode", nullable = false, updatable = false, insertable = false)
  private long userCode;

  @Column(name = "userId", nullable = false, updatable = false)
  private long userId;

  @Column(name = "username", nullable = false, length = 50)
  private String username;

  @Column(name = "password", nullable = false, length = 10)
  private String password;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "Role", nullable = false)
  private String Role;

  @Column private String mobileNumber;

  @Column(name = "confirm", nullable = false, length = 10)
  private String confirm;

  public static Users getInstance() {
    return new Users();
  }

  public long getUserCode() {
    return userCode;
  }

  public Users setUserCode(long userCode) {
    this.userCode = userCode;
    return this;
  }

  public String getRole() {
    return Role;
  }

  public Users setRole(String role) {
    Role = role;
    return this;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public Users setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  public String getConfirm() {
    return confirm;
  }

  public Users setConfirm(String confirm) {
    this.confirm = confirm;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public Users setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public Users setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public Users setEmail(String email) {
    this.email = email;
    return this;
  }

  public long getUserId() {
    return userId;
  }

  public Users setUserId(long userId) {
    this.userId = userId;
    return this;
  }
}
