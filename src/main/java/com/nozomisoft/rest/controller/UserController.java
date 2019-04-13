package com.nozomisoft.rest.controller;

import com.nozomisoft.rest.model.ErrorResponse;
import com.nozomisoft.rest.model.User;
import com.nozomisoft.rest.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

@RestController
@RequestMapping("/api/v1")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
  public DeferredResult<User> getUser(@PathVariable String username) {
    DeferredResult<User> result = new DeferredResult();
    Observable<User> observable = userService.getUser(username);
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

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorResponse>> methodArgumentNotValidExceptionn(MethodArgumentNotValidException ex) {
    List<ErrorResponse> errors = ex.getBindingResult()
            .getFieldErrors().stream()
            .map(error -> new ErrorResponse(error.getField(), error.getDefaultMessage()))
            .collect(Collectors.toList());
    return ResponseEntity.badRequest().body(errors);
  }

}
