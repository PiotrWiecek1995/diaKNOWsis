package pl.coderslab.DiaKNOWsis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import pl.coderslab.DiaKNOWsis.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/register", "/users/login", "/error").permitAll() // bez logowania
                .antMatchers("/home", "/users/details").authenticated() // Strona główna wymagane logowanie
                .antMatchers("/pacjenci/**").authenticated()
                .antMatchers("/wyniki/**").authenticated()
                .antMatchers("/diagnosis/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/users/login")
                .successHandler((request, response, authentication) -> {
                    System.out.println("Logowanie udane dla użytkownika: " + authentication.getName());
                    response.sendRedirect("/home"); // jak ok to na główną
                })
                .failureHandler((request, response, exception) -> {
                    System.out.println("Logowanie nieudane: " + exception.getMessage());
                    response.sendRedirect("/users/login?error=true");
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/users/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .httpBasic();

        return http.build();
    }


    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowSemicolon(true);
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowUrlEncodedPercent(true);
        firewall.setAllowedHeaderValues(header -> true);
        return firewall;
    }
}
