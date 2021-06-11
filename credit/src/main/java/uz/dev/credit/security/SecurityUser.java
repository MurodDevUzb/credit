package uz.dev.credit.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.dev.credit.model.User;

import java.util.List;
@Data
public class SecurityUser implements UserDetails {
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserDetails fromUser(User user){
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                true,true,true,true,
                user.getRole().getAuthoruty()
        );
    }
}
