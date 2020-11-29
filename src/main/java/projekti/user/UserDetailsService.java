package projekti.user;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByHandle(username);
        if (user == null)
            throw new UsernameNotFoundException("User " + username + " not found.");
        return new org.springframework.security.core.userdetails.User(user.getHandle(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
