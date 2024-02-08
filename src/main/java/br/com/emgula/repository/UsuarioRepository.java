package br.com.emgula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.emgula.model.Usuario;

@Repository
public  interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findByEmail(String login);
	
}
