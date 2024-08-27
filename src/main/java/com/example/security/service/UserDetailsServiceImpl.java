package com.example.security.service;

import com.example.entity.user.User;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //useri user detailse cevir
        User user = userRepository.findByUsernameEquals(username);

        //user olup olmadigini kontrol et
        if (user!=null){
            return new UserDetailsmpl(
                    user.getId(),
                    user.getUserName(),
                    user.getName(),
                    false,
                    user.getPassword(),
                    user.getUserRole().getRoleType().name,
                    user.getSsn()
            );
        }
        throw new UsernameNotFoundException("User : "+ username + " not found");

    }
}
