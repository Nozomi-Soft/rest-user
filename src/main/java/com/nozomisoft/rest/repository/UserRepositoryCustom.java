package com.nozomisoft.rest.repository;


import com.nozomisoft.rest.model.User;
import com.nozomisoft.rest.model.UserAuth;

public interface UserRepositoryCustom {

  User authenticate(UserAuth user);
}
