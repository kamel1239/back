/*
    2024
*/
package org.project.domain.user.model;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Setter
@AllArgsConstructor
@Getter
public final class UserModel implements UserDetails {

    @Serial
    private static final long serialVersionUID = 0L;
    private final String username;
    private String password;
    private final String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

}
