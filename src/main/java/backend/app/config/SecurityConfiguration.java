package backend.app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF as JWTs are used (stateless)

                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        // Public endpoints that do not require authentication
                        .requestMatchers("/api/v1/auth/**").permitAll()

                        // Endpoints that are only accessible by users with the ROLE_ADMIN authority
                        .requestMatchers("/api/v1/demo/admin/**").hasAuthority("ROLE_ADMIN")

                        // Endpoints that are only accessible by users with the ROLE_USER authority
                        .requestMatchers("/api/v1/demo/user/**").hasAuthority("ROLE_USER")

                        // Any other request must be authenticated
                        .anyRequest().authenticated()
                )

                .sessionManagement(sessionManagement -> sessionManagement
                        // Configure session management to be stateless
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Set the custom authentication provider
                .authenticationProvider(authenticationProvider)

                // Add the custom JWT authentication filter before the default UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build(); // Build and return the SecurityFilterChain
    }
}
