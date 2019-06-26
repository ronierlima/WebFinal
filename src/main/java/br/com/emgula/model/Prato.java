package br.com.emgula.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "pratos")
public class Prato{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String cod;
	
	
	@NotBlank(message = "Bota um nome nesse prato, vailá.")
	private String nome;
	
	@PositiveOrZero(message = "Preços abaixo de zero tu não tem lucro né.")
	@NotNull(message = "Vai vender como? Bota um preço.")
	private Float preco;

	private String imagemCaminho;
	
	
	
	//Getters
	
	public String getImagemCaminho() {
		return imagemCaminho;
	}

	public void setImagemCaminho(String imagemCaminho) {
		this.imagemCaminho = imagemCaminho;
	}

	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Float getPreco() {
		return preco;
	}
	
	
	public String getCod() {
		return cod;
	}

	//Setters
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	
	
	public void setCod(String cod) {
		this.cod = cod;
	}
	
}
