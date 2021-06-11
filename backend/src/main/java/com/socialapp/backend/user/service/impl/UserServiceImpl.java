package com.socialapp.backend.user.service.impl;

import com.socialapp.backend.exception.user.UserCanNotFollowException;
import com.socialapp.backend.exception.user.UserIdNotFoundException;
import com.socialapp.backend.exception.user.UsernameNotFoundException;
import com.socialapp.backend.user.dao.UserRepository;
import com.socialapp.backend.user.dto.CustomUserDetails;
import com.socialapp.backend.user.dto.User;
import com.socialapp.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ---- UserService ----
    @Override
    public User insertUser(User user) {
        log.info("Inside insertUser of UserServiceImpl");
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        return this.userRepository.insertUser(user);
    }

//    @Override
//    public User updateUser(Long id, User user) {
//        log.info("Inside updateUser of UserServiceImpl");
//        this.userRepository.findById(id)
//                .orElseThrow(() -> new UserIdNotFoundException("Invalid user id"));
//
//        User res = this.userRepository.updateUser(user);
//        res.setPassword(null);
//        return res;
//    }
//
//    @Override
//    public void deleteUser(Long id) {
//        log.info("Inside deleteUser of UserServiceImpl");
//        if (this.userRepository.existsById(id)) {
//            this.userRepository.deleteById(id);
//        } else {
//            log.error("User id " + id + " not found");
//            throw new UserIdNotFoundException("Invalid user id");
//        }
//    }

    @Override
    public User findUserById(Long id) {
        log.info("Inside findUserById of UserServiceImpl");

        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException("Invalid user id"));
    }

//    @Override
//    @Transactional
//    public void followUser(Long id, Long userId) {
//        if (id.equals(userId)) {
//            throw new UserCanNotFollowException("Can not follow yourself");
//        }
//
//        User currentUser = this.userRepository.findById(id)
//                .orElseThrow(() -> new UserIdNotFoundException("Invalid user id"));
//        User followedUser = this.userRepository.findById(userId)
//                .orElseThrow(() -> new UserIdNotFoundException("Invalid user id"));
//
//        Set<User> followings = currentUser.getFollowings();
//        if (followings.contains(followedUser)) {
//            throw new UserCanNotFollowException("User is already followed");
//        }
//
//        followings.add(followedUser);
//        currentUser.setFollowings(followings);
//        this.userRepository.update(currentUser);
//
//    }
//
//    @Override
//    public void unfollowUser(Long id, Long userId) {
//        if (id.equals(userId)) {
//            throw new UserCanNotFollowException("Can not unfollow yourself");
//        }
//
//        User currentUser = this.userRepository.findById(id)
//                .orElseThrow(() -> new UserIdNotFoundException("Invalid user id"));
//        User unfollowedUser = this.userRepository.findById(userId)
//                .orElseThrow(() -> new UserIdNotFoundException("Invalid user id"));
//
//        Set<User> followings = currentUser.getFollowings();
//        if (!followings.contains(unfollowedUser)) {
//            throw new UserCanNotFollowException("User is already unfollowed");
//        }
//
//        followings.remove(unfollowedUser);
//        currentUser.setFollowings(followings);
//        this.userRepository.update(currentUser);
//
//    }


}