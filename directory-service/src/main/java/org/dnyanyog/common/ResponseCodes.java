package org.dnyanyog.common;

public enum ResponseCodes {
  ADD_USER_SUCCESS("Success", "User added Successfully"),
  USER_NOT_FOUND("Fail", "User not present"),
  USER_LOGIN_SUCCESS("Success", "User Login Successfully"),
  USER_LOGIN_FAIL("fail", "User Not Login Successfully"),
  USER_DELETED_SUCCESS("Success", "User Delete Successfully"),
  UPDATE_USER_SUCCESS("Success", "User update Successfully"),
  USER_FOUND("Success", "User found Successfully");
	

  private final String message;
  private final String status;

  ResponseCodes(String message, String status) {

    this.message = message;
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public String getStatus() {
    return status;
  }
}
