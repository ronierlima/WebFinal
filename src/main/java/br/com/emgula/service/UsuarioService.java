package br.com.emgula.service;


import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.emgula.model.Role;
import br.com.emgula.model.Usuario;
import br.com.emgula.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository ur;
	
	public void cadastrar(Usuario usuario) throws Exception{
		 usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		 
		 Usuario usuario2 =  ur.findByEmail(usuario.getEmail());
		 
		 if(usuario2 != null)
			 throw new Exception("Caba, Este email já ta sendo usado, tenta valá !!!");
			 
		 List<Role> roles = new ArrayList<>();
		 Role cliente = new Role();
		 cliente.setPapel("ROLE_CLIENTE");
		 roles.add(cliente);
		 usuario.setRoles(roles);
		 
		 ur.save(usuario);
	}
	
}
