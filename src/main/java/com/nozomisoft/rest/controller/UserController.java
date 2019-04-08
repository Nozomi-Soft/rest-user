package com.nozomisoft.rest.controller;

import com.nozomisoft.rest.model.User;
import com.nozomisoft.rest.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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

}
