package net.me.ebanking.endpoint;

import net.me.ebanking.entitie.AppUser;
import net.me.ebanking.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class ProfileOperation {

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("profile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AppUser> getAppUser(@RequestParam String username) {
        Optional<AppUser> appUser = appUserRepository.findByUsername(username);
        return appUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
