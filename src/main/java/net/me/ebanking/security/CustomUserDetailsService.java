package net.me.ebanking.security;

import lombok.AllArgsConstructor;
import net.me.ebanking.entitie.UserEntity;
import net.me.ebanking.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("username not found") );

        return new User(user.getUsername(), user.getPassword(),user.getAuthorities());
    }

}
