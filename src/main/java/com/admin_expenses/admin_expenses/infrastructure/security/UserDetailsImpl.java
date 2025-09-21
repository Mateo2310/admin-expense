package com.admin_expenses.admin_expenses.infrastructure.security;

import com.admin_expenses.admin_expenses.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final UserModel userModel;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userModel.getRoleModel().getName());
        return  List.of(grantedAuthority);
    }

    @Override
    public String getPassword() {
        return this.userModel.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userModel.getUsername();
    }

    public UserModel getUserModel() {
        return this.userModel;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
