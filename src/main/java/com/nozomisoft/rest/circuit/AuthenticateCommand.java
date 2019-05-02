package com.nozomisoft.rest.circuit;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.nozomisoft.rest.model.User;
import com.nozomisoft.rest.model.UserAuth;
import com.nozomisoft.rest.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AuthenticateCommand extends HystrixCommand<User> {

  @Autowired
  private UserRepositoryImpl userRepositoryImpl;

  @Autowired
  private PasswordEncoder passwordEncoder;

  private UserAuth userAuth;

  public AuthenticateCommand(UserAuth userAuth) {
    super(HystrixCommandGroupKey.Factory.asKey("UserGroup"));
    this.userAuth = userAuth;
  }

  @Override
  protected User run() throws Exception {
    User user = userRepositoryImpl.authenticate(userAuth);
    if(!passwordEncoder.matches(userAuth.getPassword(), user.getPassword())){
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
    }

    return user;
  }
}
