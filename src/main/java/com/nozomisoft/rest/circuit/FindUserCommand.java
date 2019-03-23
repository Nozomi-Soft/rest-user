package com.nozomisoft.rest.circuit;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.nozomisoft.rest.model.User;
import com.nozomisoft.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FindUserCommand extends HystrixCommand<User> {

  @Autowired
  private UserRepository userRepository;

  private String username;

  public FindUserCommand(String username) {
    super(HystrixCommandGroupKey.Factory.asKey("UserGroup"));
    this.username = username;
  }

  @Override
  protected User run() {
    return userRepository.getUser(username);
  }
}
