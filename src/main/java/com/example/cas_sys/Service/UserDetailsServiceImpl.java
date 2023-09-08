package com.example.cas_sys.Service;

import com.example.cas_sys.Repository.UserRepository;
import my.common.commonkoochita.Model.User;
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

        System.out.println(login);

        Optional<User> user = getUserByUsername(login);

        if(user.isPresent()) {

            detailsChecker.check((UserDetails) user.get());

            return (UserDetails) user.get();
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    @Override
    public Optional<User> getUserByUsername(String login) {

        System.out.println(login);

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
