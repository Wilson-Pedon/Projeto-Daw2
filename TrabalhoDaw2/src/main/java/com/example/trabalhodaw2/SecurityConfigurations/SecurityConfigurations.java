package com.example.trabalhodaw2.SecurityConfigurations;

import com.example.trabalhodaw2.Dao.ClienteRepositorio;
import com.example.trabalhodaw2.Service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ClienteRepositorio repositorio;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //Configurações de autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Configuração de autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/api/leilao/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/leilao/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/leilao").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/cliente").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/cliente").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/cliente/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/cliente/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/cliente/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/lance/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/lance/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/auto").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, repositorio), UsernamePasswordAuthenticationFilter.class);

    }

    //configurações de recursos estaticos(js,css,imagens, etc)
    @Override
    public void configure(WebSecurity web) throws Exception {
    }
}
