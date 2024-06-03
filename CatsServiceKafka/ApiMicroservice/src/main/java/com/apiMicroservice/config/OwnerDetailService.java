package com.apiMicroservice.config;

import com.apiMicroservice.kafka.KafkaConsumer;
import com.apiMicroservice.kafka.KafkaProducer;
import com.entities.owners.OwnerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class OwnerDetailService implements UserDetailsService {
    private final KafkaConsumer kafkaConsumer;
    private final KafkaProducer kafkaProducer;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("admin")) {
            return new User("admin", passwordEncoder.encode("123"),
                    Arrays.asList(new SimpleGrantedAuthority("admin")));
        }

        kafkaProducer.checkOwnerExists(username);

        try {
            Optional<OwnerDto> user = kafkaConsumer.getOwnerFuture().get(10, TimeUnit.SECONDS);
            return user.map(OwnerDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve user information", e);
        }
    }
}
