package com.moka.auth.persnldetails;

import com.moka.auth.utils.CustomAuthorityUtils;
import com.moka.exception.BusinessLogicException;
import com.moka.persnl.entity.MokaPersnl;
import com.moka.persnl.repository.MokaPersnlRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

public class PersnlDetailsService implements UserDetailsService {

    private final MokaPersnlRepository mokaPersnlRepository;
    private final CustomAuthorityUtils authorityUtils;

    public PersnlDetailsService(MokaPersnlRepository mokaPersnlRepository, CustomAuthorityUtils authorityUtils) {
        this.mokaPersnlRepository = mokaPersnlRepository;
        this.authorityUtils = authorityUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MokaPersnl> optionalMember = mokaPersnlRepository.findByPersnlId(username);
        MokaPersnl findMember = optionalMember.orElseThrow();

        return new PersnlDetails(findMember);
    }

    private final class PersnlDetails extends MokaPersnl implements UserDetails {
        PersnlDetails ( MokaPersnl mokaPersnl){
            setPersnlSerialNum(mokaPersnl.getPersnlSerialNum());
            setPersnlId(mokaPersnl.getPersnlId());
            setPersnlPw(mokaPersnl.getPersnlPw());
            setRoles(mokaPersnl.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getRoles());
            //        return null;
        }

        @Override
        public String getPassword() {
            return getPersnlPw();
        }

        // 이게 userName일까?
        @Override
        public String getUsername() {
            return getPersnlId();
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
    }
}
