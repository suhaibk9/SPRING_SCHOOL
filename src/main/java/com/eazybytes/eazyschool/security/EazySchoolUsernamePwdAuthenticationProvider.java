package com.eazybytes.eazyschool.security;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.model.Roles;
import com.eazybytes.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EazySchoolUsernamePwdAuthenticationProvider
        implements AuthenticationProvider {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Person person = personRepository.readByEmail(email);
        if (person != null && person.getEmail().equalsIgnoreCase("admin@admin.com") && pwd.equals(person.getPwd())) {
            return new UsernamePasswordAuthenticationToken(
                    email, null, getGrantedAuthorities(person.getRoles()));
        } else if (null != person && person.getPersonId() > 0 &&
                passwordEncoder.matches(pwd, person.getPwd())) {
            return new UsernamePasswordAuthenticationToken(
                    email, null, getGrantedAuthorities(person.getRoles()));
        } else {
            throw new BadCredentialsException("Invalid credentials!");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + roles.getRoleName()));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
//person_id,name,email,mobile_number,pwd,role_id,address_id,created_at,created_by,updated_at,updated_by
//1,Suhaib,s@s.com,1234567890,s@s.coms@s.com,2,NULL,2025-03-25 16:15:56,anonymousUser,NULL,NULL
//2,suhaib,s@s.com,1234567890,s@s.coms@s.com,2,NULL,2025-03-25 16:16:31,anonymousUser,NULL,NULL
//3,Joe,Joe@Joe.com,1234567890,Joe@Joe.com,2,NULL,2025-03-26 13:48:22,anonymousUser,NULL,NULL
//4,Admin,admin@admin.com,3443434343,54321,1,NULL,2025-03-26 00:00:00,DBA,NULL,NULL
//5,Suhaib,S@S.com,1234567890,S@S.comS@S.com,2,NULL,2025-03-26 17:54:46,anonymousUser,NULL,NULL
//6,abcd,abcd@abcd.com,0123456789,$2a$10$5OUNwTFRIkdaq5vzMIWdLePyR0t7a1v1LCBCAoWAhGskWAX8C501e,2,NULL,2025-03-26 21:34:37,anonymousUser,NULL,NULL
//7,Suhaib,A@A.com,1234567890,$2a$10$hw5WcxMQ0kREd.zbYZdmMeI42UkHfXsdubmbChzpV8Bu9ioeq2sHS,2,NULL,2025-03-26 21:35:02,anonymousUser,NULL,NULL
