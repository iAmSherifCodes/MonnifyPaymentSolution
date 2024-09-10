package com.wallet.monnify.security.services;

import com.wallet.monnify.security.model.SecureUser;
import com.wallet.monnify.user.data.model.User;
import com.wallet.monnify.user.services.IUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component @AllArgsConstructor
public class MonnifyUserDetailsService implements UserDetailsService {
    private final IUser userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(username);
        return new SecureUser(user);
    }
}
