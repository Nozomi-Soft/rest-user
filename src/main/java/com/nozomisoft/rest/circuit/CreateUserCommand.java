package com.nozomisoft.rest.circuit;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.nozomisoft.rest.model.User;
import com.nozomisoft.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateUserCommand extends HystrixCommand<User> {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  private User user;

  public CreateUserCommand(User user) {
    super(HystrixCommandGroupKey.Factory.asKey("UserGroup"));
    this.user = user;
  }


  @Override
  protected User run() throws Exception {
    user.setCreated(LocalDateTime.now());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.saveMerge(user);
  }
}
