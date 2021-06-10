package com.socialapp.backend.user.service.impl;

import com.socialapp.backend.exception.user.UserCanNotFollowException;
import com.socialapp.backend.exception.user.UserIdNotFoundException;
import com.socialapp.backend.exception.user.UsernameNotFoundException;
import com.socialapp.backend.user.entity.UserRepository;
import com.socialapp.backend.user.entity.CustomUserDetails;
import com.socialapp.backend.user.entity.User;
import com.socialapp.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    // ---- UserDetailsService ----
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            log.error("Username " + username + " is not found");
            throw new UsernameNotFoundException("Username " + username + " is not found");
        }
        return new CustomUserDetails(user);

    }

    public UserDetails loadUserById(Long userId) {
        User user = this.userRepository.findById(userId).get();
        if (user == null) {
            log.error("User id is not found");
            throw new UserIdNotFoundException("User id " + userId + " is not found");
        }
        return new CustomUserDetails(user);
    }
    // ---- UserDetailsService ----

    // ---- UserService ----
    @Override
    public User insertUser(User user) {
        log.info("Inside insertUser of UserServiceImpl");
        try {
            return this.userRepository.save(user);
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    @Override
    public User updateUser(User user) {
        log.info("Inside updateUser of UserServiceImpl");
        try {
            return this.userRepository.save(user);
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        log.info("Inside deleteUser of UserServiceImpl");
        if (this.userRepository.existsById(id)) {
            this.userRepository.deleteById(id);
            return true;
        } else {
            log.error("User id " + id + " not found");
            return false;
        }
    }

    @Override
    public User findUserById(Long id) {
        log.info("Inside findUserById of UserServiceImpl");

        Optional<User> optionalUser = this.userRepository.findById(id);
        User user = optionalUser.get();
        if (user != null) {
            return User.builder()
                    .username(user.getUsername())
                    .city(user.getCity())
                    .profilePicture(user.getProfilePicture())
                    .coverPicture(user.getCoverPicture())
                    .desc(user.getDesc())
                    .from(user.getFrom())
                    .email(user.getEmail())
                    .id(user.getId())
                    .relationship(user.getRelationship())
                    .build();
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public boolean followUser(Long id, Long userId) {
        if(id.equals(userId)){
            throw new UserCanNotFollowException("Can not follow yourself");
        }

        User currentUser = this.userRepository.findById(id).get();
        User followedUser = this.userRepository.findById(userId).get();
        if (currentUser == null || followedUser == null) {
            throw new UserIdNotFoundException("Invalid user id");
        }

        Set<User> followings = currentUser.getFollowings();
        if (followings.contains(followedUser)) {
            throw new UserCanNotFollowException("User is already followed");
        }

        followings.add(followedUser);
        currentUser.setFollowings(followings);
        this.userRepository.save(currentUser);

        return true;
    }

    @Override
    public boolean unfollowUser(Long id, Long userId) {
        if(id.equals(userId)){
            throw new UserCanNotFollowException("Can not unfollow yourself");
        }

        User currentUser = this.userRepository.findById(id).get();
        User unfollowedUser = this.userRepository.findById(userId).get();
        if (currentUser == null || unfollowedUser == null) {
            throw new UserIdNotFoundException("Invalid user id");
        }

        Set<User> followings = currentUser.getFollowings();
        if (!followings.contains(unfollowedUser)) {
            throw new UserCanNotFollowException("User is already unfollowed");
        }

        followings.remove(unfollowedUser);
        currentUser.setFollowings(followings);
        this.userRepository.save(currentUser);

        return true;
    }


}