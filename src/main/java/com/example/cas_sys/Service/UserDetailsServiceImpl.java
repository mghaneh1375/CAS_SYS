package com.example.cas_sys.Service;

import com.example.cas_sys.Model.User;
import com.example.cas_sys.Repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service(value = "userService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService,UserService {

    private final UserRepository userRepository;


    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Load user infos by credential
     *
     * @param login username or email
     * @return UserDetails object
     */
    @Override
    public UserDetails loadUserByUsername(String login) {

        System.out.println("loadUserByUsername");

        Optional<User> user = getUserByUsername(login);

        System.out.println("user find is " + user.isPresent());

        if(user.isPresent()) {

            User user1 = user.get();

            org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
                    user1.getUsername(), user1.getPassword(), user1.isEnabled(),
                    user1.isAccountNonExpired(), user1.isCredentialsNonExpired(), user1.isAccountNonLocked(),
                    user1.getAllAuthorities()
            );

            detailsChecker.check(userDetails);

            return userDetails;
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    @Override
    public Optional<User> getUserByUsername(String login) {

        System.out.println("getUserByUsername : " + login);

        // trim username value
        String username = StringUtils.trimToNull(login);

        if(StringUtils.isNotEmpty(username)) {

            Optional<User> user;

            if (username.contains("@")) {
                user = userRepository.findByEmail(username);
            } else {
                user = userRepository.findByUsername(username);
            }

            return user;
        }else {
            return Optional.empty();
        }

    }


}
