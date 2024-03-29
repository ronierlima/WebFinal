package br.com.emgula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.emgula.model.Prato;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long>{

}
