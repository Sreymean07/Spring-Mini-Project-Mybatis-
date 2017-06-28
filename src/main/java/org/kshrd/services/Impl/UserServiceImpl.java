package org.kshrd.services.Impl;

import org.kshrd.model.User;
import org.kshrd.repositories.UserRepository;
import org.kshrd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by sovannarith on 6/26/17.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User search(String userhash) {
        // TODO Auto-generated method stub
        return userRepository.search(userhash);
    }
    @Override
    public boolean save(User user) {
        // TODO: save user to database
//		String userHash = UUID.randomUUID().toString();
//		SignUpWith signUpWith = new SignUpWith(2,null);
//		user.setSignUpWith(signUpWith);
    	String userHash = UUID.randomUUID().toString();
        user.setUserHash(userHash);
        boolean status = userRepository.save(user);
        if (status) {
            System.out.println("USER ID : " + user.getId());
            System.out.println("USER SignUpWith : " + user.getSignUpWith().getId());
            System.out.println("userhash  "+user.getUserHash());
            System.out.println("User has been inserted!");
        } else {
            System.out.println("User has not been inserted!.");
        }
        return status;
    }

    @Override
    public boolean deleteByUserHash(String userHash) {
        // TODO: delete user from database by userHash
        boolean status = userRepository.delete(userHash);
        if (status) {
            System.out.println("User has been deleted!");
        } else {
            System.out.println("User has not been deleted!.");
        }
        return status;
    }

    @Override
    public boolean updateByUserHash(User user) {
        // TODO: update user from database by userHash
        boolean status = userRepository.update(user);
        if (status) {
            System.out.println("User has been updated!");
        } else {
            System.out.println("User has not been updated!");
        }
        return status;
    }
    @Override
    public boolean saveBatch(List<User> users) {
        return userRepository.saveBatch(users);
    }

    @Override
    public int countTotal() {
        return userRepository.countTotal();
    }
    @Override
    public int countMale() {
        return userRepository.countMale();
    }
    @Override
    public int countFemale() {
        return userRepository.countFemale();
    }


}