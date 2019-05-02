package com.nozomisoft.rest.circuit;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.nozomisoft.rest.model.User;
import com.nozomisoft.rest.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FindUsersCommand extends HystrixCommand<List<User>> {
  @Autowired
  private UserRepository userRepository;

  public FindUsersCommand() {
    super(HystrixCommandGroupKey.Factory.asKey("UserGroup"));
  }

  @Override
  protected List<User> run() {
    return userRepository.findAll();
  }
}
