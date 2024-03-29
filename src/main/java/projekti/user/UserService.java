package projekti.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import projekti.exception.NotFoundException;
import projekti.exception.UnauthorizedException;
import projekti.photo.Photo;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Page<User> listByName(String name, Pageable pageable) {
        return userRepository.findByNameContainsIgnoreCase(name, pageable);
    }

    public User store(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public User findByHandle(String handle) {
        User user = userRepository.findByHandle(handle);
        if (user == null)
            throw new NotFoundException();
        return user;
    }

    public User findBySlug(String slug) {
        User user = userRepository.findBySlug(slug);
        if (user == null)
            throw new NotFoundException();
        return user;
    }

    public User login(User loginUser) {
        User user = findByHandle(loginUser.getHandle());
        if (!passwordEncoder.matches(loginUser.getPassword(), user.getPassword()))
            throw new UnauthorizedException();
        this.setAuthentication(user);
        return user;
    }

    public User signup(User user) {
        this.store(user);
        this.setAuthentication(user);
        return user;
    }

    public Page<User> listFriends(User user, Pageable pageable) {
        return userRepository.findFriends(user, pageable);
    }

    public Page<User> listPendingFriends(User user, Pageable pageable) {
        return userRepository.findPendingFriends(user, pageable);
    }

    public void setAvatar(User user, Photo avatar) {
        user.setAvatar(avatar);
        userRepository.save(user);
    }

    private void setAuthentication(User user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getHandle());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
