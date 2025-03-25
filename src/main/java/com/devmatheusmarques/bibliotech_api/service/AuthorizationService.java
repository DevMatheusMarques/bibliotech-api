package com.devmatheusmarques.bibliotech_api.service;

import com.devmatheusmarques.bibliotech_api.exception.InactiveUserException;
import com.devmatheusmarques.bibliotech_api.model.User;
import com.devmatheusmarques.bibliotech_api.repository.UserRepository;
import com.devmatheusmarques.bibliotech_api.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        if (!user.getStatus().equals(Status.ACTIVE)) {
            throw new InactiveUserException("Acesso negado: Usuário inativo");
        }

        return user;
    }
}
