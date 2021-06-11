package com.socialapp.backend.authen;

import com.socialapp.backend.exception.user.UserIdNotFoundException;
import com.socialapp.backend.exception.user.UsernameNotFoundException;
import com.socialapp.backend.user.dao.UserRepository;
import com.socialapp.backend.user.dto.CustomUserDetails;
import com.socialapp.backend.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationUserService implements UserDetailsService {
    private final UserRepository userRepository;

    // ---- UserDetailsService ----
    @Override
    public UserDetails loadUserByUsername(String username) throws com.socialapp.backend.exception.user.UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " is not found"));
        return new CustomUserDetails(user);

    }

    public UserDetails loadUserById(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserIdNotFoundException("User id " + userId + " is not found"));
        return new CustomUserDetails(user);
    }
    // ---- UserDetailsService ----
}
