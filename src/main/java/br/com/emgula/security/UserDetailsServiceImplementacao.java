package br.com.emgula.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.emgula.model.Usuario;
import br.com.emgula.repository.UsuarioRepository;

@Repository
@Transactional
public class UserDetailsServiceImplementacao implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
			Usuario usuario = usuarioRepository.findByEmail(login);
			
			if(usuario == null) {
				throw new UsernameNotFoundException("Pessoa não encontrada");
			}
		
		return new User(usuario.getUsername(),usuario.getPassword(),true,true,true,true,usuario.getAuthorities());
	}

}
