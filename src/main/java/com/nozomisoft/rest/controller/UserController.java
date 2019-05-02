package com.nozomisoft.rest.controller;

import com.nozomisoft.rest.model.ErrorResponse;
import com.nozomisoft.rest.model.User;
import com.nozomisoft.rest.model.UserAuth;
import com.nozomisoft.rest.service.UserService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

@RestController
@RequestMapping("/api/v1")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/user/", method = RequestMethod.GET)
  public DeferredResult<User> getUser(
      @PathParam("username") String username,
      @PathParam("email") String email
      ) {

    if(Objects.isNull(username) && Objects.isNull(email)){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "username and email are null");
    }

    DeferredResult<User> result = new DeferredResult();
    Observable<User> observable = userService.getUser(username, email);
    observable.subscribe(result::setResult, result::setErrorResult);

    return result;
  }

  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public DeferredResult<List<User>> getUsers() {
    DeferredResult<List<User>> result = new DeferredResult();
    Observable<List<User>> observable = userService.getUsers();
    observable.subscribe(result::setResult, result::setErrorResult);

    return result;
  }

  @RequestMapping(value = "/user", method = RequestMethod.POST)
  public DeferredResult<User> createUser(@RequestBody @Valid User user) {
    DeferredResult<User> result = new DeferredResult();
    Observable<User> observable = userService.createUser(user);
    observable.subscribe(result::setResult, result::setErrorResult);

    return result;
  }

  @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = "application/json")
  public DeferredResult<User> authenticate(@RequestBody @Valid UserAuth user) {
    DeferredResult<User> result = new DeferredResult();
    Observable<User> observable = userService.authenticateUser(user);
    observable.subscribe(result::setResult, result::setErrorResult);

    return result;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorResponse>> methodArgumentNotValidExceptionn(
      MethodArgumentNotValidException ex) {
    List<ErrorResponse> errors = ex.getBindingResult()
        .getFieldErrors().stream()
        .map(error -> new ErrorResponse(error.getField(), error.getDefaultMessage()))
        .collect(Collectors.toList());
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(HttpClientErrorException.class)
  public ResponseEntity<ErrorResponse> httpClientErrorException(HttpClientErrorException ex){
    ErrorResponse error = new ErrorResponse(null, ex.getMessage());

    return ResponseEntity.status(ex.getStatusCode()).body(error);
  }
}
