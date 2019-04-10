package com.nozomisoft.rest.repository;

import com.nozomisoft.rest.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  User user;

  public  UserRepository() {
    user = new User();
    user.setId("0");
    user.setName("name");
    user.setUsername("nick");
    user.setPassword("123");
    user.setLastName("lastname");
    user.setEmail("email@mail.com");
    user.setCountry("US");
    user.setLanguage("en");
    user.setRole("ADMIN");
    user.setStatus("ACTIVE");
  }

  public User getUser(String username) {
    return user;
  }

  public List<User> getUsers() {
    List<User> result = new ArrayList<>();
    result.add(user);
    result.add(user);
    result.add(user);
    return result;
  }

  public User createUser(User user) {
    return user;
  }
}
