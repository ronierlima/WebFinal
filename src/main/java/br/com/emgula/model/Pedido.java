package br.com.emgula.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pedido {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long codigo;
	
	private Long codigoCliente;
	private Float valorTotal;
	private String endereco;
	private String pratosPedido;
	
	public String getPratosPedido() {
		return pratosPedido;
	}

	public void setPratosPedido(String pratosPedido) {
		this.pratosPedido = pratosPedido;
	}

	public Pedido() {}
	
	public String getEnderecoPedido() {
		return endereco;
	}


	public void setEnderecoPedido(String enderecoPedido) {
		this.endereco = enderecoPedido;
	}


	public Long getCodigoPedido() {
		return codigo;
	}


	public void setCodigoPedido(Long codigoPedido) {
		this.codigo = codigoPedido;
	}


	public Long getCodigoCliente() {
		return codigoCliente;
	}


	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public float getValorTotal() {
		return valorTotal;
	}


	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
}
