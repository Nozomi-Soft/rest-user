package com.nozomisoft.rest.repository;

import com.nozomisoft.rest.model.User;
import com.nozomisoft.rest.model.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom{

  @Autowired
  private EntityManager em;

  public User authenticate(UserAuth user){
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<User> cq = cb.createQuery(User.class);
    Root<User> from = cq.from(User.class);

    cq.select(from);
    cq.where(cb.equal(from.get("email"), user.getEmail()));
    TypedQuery<User> q = em.createQuery(cq);

    return q.getSingleResult();
  }

  @Transactional
  public User saveMerge(User user){
    return em.merge(user);
  }
}
