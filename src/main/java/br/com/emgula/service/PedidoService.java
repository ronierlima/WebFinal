package br.com.emgula.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emgula.model.Pedido;
import br.com.emgula.repository.PedidoRepository;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepositorio;
	
	public void cadastrar(Pedido pedido) {
		pedidoRepositorio.save(pedido);
	}
	
	public List<Pedido> listarPedidosPorId(Long clienteId){
		List<Pedido> clientePedidos = new ArrayList<Pedido>();
		for (Pedido pedido : pedidoRepositorio.findAll()) {
			if (pedido.getCodigoCliente() == clienteId) {
				clientePedidos.add(pedido);
			}
		}
		return clientePedidos;
	}
	
	public void excluir(Long codigo) {
		pedidoRepositorio.deleteById(codigo);
	}
	
	public Pedido buscar(Long codigo) {
		return pedidoRepositorio.getOne(codigo);
	}
}
