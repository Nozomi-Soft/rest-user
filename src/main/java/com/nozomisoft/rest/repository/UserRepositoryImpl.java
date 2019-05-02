package com.nozomisoft.rest.repository;

import com.nozomisoft.rest.model.User;
import com.nozomisoft.rest.model.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom{

  @Autowired
  private MongoTemplate mongoTemplate;

  public User authenticate(UserAuth user){
    final Query query = new Query();
    query.addCriteria(Criteria.where("email").is(user.getEmail()));

    return mongoTemplate.findOne(query, User.class);
  }
}
