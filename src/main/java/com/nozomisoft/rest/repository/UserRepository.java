package com.nozomisoft.rest.repository;

import com.nozomisoft.rest.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  public User getUser(String username) {
    User user = new User();
    user.setId("0");
    user.setName("name");
    user.setUsername("nick");
    user.setLastName("lastname");
    user.setEmail("email@mail.com");

    return user;
  }

  public User createUser(User user) {
    return user;
  }
}
