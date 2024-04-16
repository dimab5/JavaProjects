package app.owners;

import abstractions.repositories.IOwnerRepository;
import config.OwnerDetails;
import models.owners.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerDetailService implements UserDetailsService {
    @Autowired
    private IOwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Owner> user = Optional.ofNullable(ownerRepository.findOwnerByName(username));
        return user.map(OwnerDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}