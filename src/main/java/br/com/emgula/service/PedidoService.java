package br.com.emgula.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emgula.model.Pedido;
import br.com.emgula.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoR;

	public void cadastrar(Pedido pedido, boolean status) {

		if (status) {
			pedidoR.save(pedido);
		} else {
			Pedido p1 = (Pedido)pedidoR.findByIdCliente(pedido.getIdCliente());
			if (p1 == null) {
				pedidoR.save(pedido);
			} else {
				pedido.setId(p1.getId());
				pedidoR.save(pedido);
			}
		}

	}

	public List<Pedido> listar() {
		return pedidoR.findAll();
	}
}
