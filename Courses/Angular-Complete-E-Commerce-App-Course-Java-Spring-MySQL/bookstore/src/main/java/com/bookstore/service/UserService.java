package com.bookstore.service;

import com.bookstore.domain.User;
import com.bookstore.domain.UserBilling;
import com.bookstore.domain.UserPayment;
import com.bookstore.domain.UserShipping;
import com.bookstore.domain.security.UserRole;

import java.util.Set;

public interface UserService {

    User createUser(User user, Set<UserRole> userRoles);

    User findByUsername(String username);

    User findByEmail (String email);

    User save(User user);

    User findById(Long id);

    void updateUserPaymentInfo(UserBilling userBilling, UserPayment userPayment, User user);

    void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);

    void setUserDefaultPayment(Long userPaymentId, User user);

    void updateUserShipping(UserShipping userShipping, User user);

    void setUserDefaultShipping(Long userShippingId, User user);

}
