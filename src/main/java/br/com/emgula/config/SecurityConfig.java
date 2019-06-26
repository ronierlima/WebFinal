package br.com.emgula.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import br.com.emgula.security.UserDetailsServiceImplementacao;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImplementacao userDetaisServiceImple;

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		
		
		http.csrf().disable().authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/EmGula").permitAll()
		.antMatchers("/EmGula/").permitAll()
		.antMatchers("/EmGula/cadastrar").permitAll()
		.antMatchers("/EmGula/salvar").permitAll()
		.antMatchers("/EmGula/entrar").permitAll()
		.antMatchers("/EmGula/cardapio").permitAll()
		.antMatchers("/EmGula/destaques").permitAll()
		.antMatchers("/EmGula/quemSomos").permitAll()
		.antMatchers("/EmGula/faleConosco").permitAll()
		.antMatchers("/EmGula/pratos/cadastrarPratos").hasRole("ADM")
		.antMatchers("/EmGula/pratos/listar").hasRole("ADM")
		
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.loginPage("/EmGula/logar").defaultSuccessUrl("/EmGula")
		.permitAll()
		
		
		.and()
		.logout()
		.logoutSuccessUrl("/EmGula/cardapio")
		.permitAll();
		
	}
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetaisServiceImple).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/images/**");
	}

}