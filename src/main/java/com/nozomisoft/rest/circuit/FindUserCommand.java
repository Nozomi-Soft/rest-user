package com.nozomisoft.rest.circuit;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.nozomisoft.rest.model.User;
import com.nozomisoft.rest.repository.UserRepository;
import java.util.Objects;
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
  private String email;

  public FindUserCommand(String username, String email) {
    super(HystrixCommandGroupKey.Factory.asKey("UserGroup"));
    this.email = email;
    this.username = username;
  }

  @Override
  protected User run() {
    return Objects.isNull(this.username) ? userRepository.findUserByEmail(email)
        : userRepository.findUserByUsername(username);
  }
}
