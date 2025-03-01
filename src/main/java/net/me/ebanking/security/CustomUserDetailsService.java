package net.me.ebanking.security;

import net.me.ebanking.entitie.Role;
import net.me.ebanking.entitie.UserEntity;
import net.me.ebanking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("username not found") );
        return new User(user.getUsername(), user.getPassword(), mapRoleToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRoleToAuthorities(List<Role> roles){
       return roles.stream().map(r-> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
