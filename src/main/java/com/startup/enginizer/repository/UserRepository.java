package com.startup.enginizer.repository;

import com.startup.enginizer.model.entities.User;
import com.startup.enginizer.security.Authority;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Dragos on 9/13/2015.
 */
@Repository
@Transactional(readOnly = false)
public class UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(UserRepository.class);

    @Autowired
    private SessionFactory sessionFactory;

    public List<User> getAllAdmins(){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("role", Authority.ADMINISTRATOR));
        List<User> list = criteria.list();
        return list;
    }

    public User getUserForCredentials(final String mail, final String password) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("mail", mail));
        criteria.add(Restrictions.eq("password", password));

        User user =(User) criteria.uniqueResult();
        if (user == null) {
            return null;
        }

        Date date = new Date();
        user.setLastLogin(new Timestamp(date.getTime()));
        sessionFactory.getCurrentSession().update(user);

        return user;
    }
}