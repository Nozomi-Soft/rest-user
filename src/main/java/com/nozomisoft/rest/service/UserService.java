package com.nozomisoft.rest.service;

import com.nozomisoft.rest.circuit.CreateUserCommand;
import com.nozomisoft.rest.circuit.FindUserCommand;
import com.nozomisoft.rest.circuit.FindUsersCommand;
import com.nozomisoft.rest.model.User;
import java.util.List;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

@Service
public class UserService {

  @Autowired
  private ObjectProvider<FindUserCommand> findUserCommand;

  @Autowired
  private ObjectProvider<FindUsersCommand> findUsersCommand;

  @Autowired
  private ObjectProvider<CreateUserCommand> createUserCommand;

  public Observable<User> getUser(String username) {
    return findUserCommand.getObject(username).observe();
  }

  public Observable<List<User>> getUsers() {
    return findUsersCommand.getObject().observe();
  }

  public Observable<User> createUser(User user) {
    return createUserCommand.getObject(user).observe();
  }

}
