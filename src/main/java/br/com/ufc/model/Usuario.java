package br.com.ufc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//@NotNull(message = "Preencha sua data de nascimento seu marreco")
	private String nome;
	
	//@NotNull(message = "Preencha sua data de nascimento seu marreco")
	private String cpf;
	
	@FutureOrPresent(message = "Não tem como tu nascer depois de Hoje")
	//@NotNull(message = "Preencha sua data de nascimento seu marreco")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "Data_Nascimento")
	private Date dataNasc;
	
	@NotNull(message = "Preencha sua data de nascimento seu marreco")
	@Column(name = "endereco")
	private String end;
	
	@NotNull(message = "Preencha sua data de nascimento seu marreco")
	@Email(message = "Email should be valid")
	private String email;
	
	@NotNull(message = "Preencha sua data de nascimento seu marreco")
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {	
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
