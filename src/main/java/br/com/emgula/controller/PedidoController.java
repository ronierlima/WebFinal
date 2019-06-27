package br.com.emgula.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.emgula.model.Pedido;
import br.com.emgula.model.Prato;
import br.com.emgula.service.PedidoService;
import br.com.emgula.service.UsuarioService;


@Controller
@RequestMapping("/EmGula/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	private static List<Prato> listaPedido = new ArrayList<>();
	
	public static void adicionarPratoAoPedido(Prato prato) {
		listaPedido.add(prato);
	}
	
	public static void limparCarrinho() {
		listaPedido.clear();
	}
	
	private float getValorTotalCarrinho() {
		float total = 0;
		for (Prato prato : listaPedido) {
			total += prato.getPreco();
		}
		return total;
	}
		
	@RequestMapping("/sacola")
	public ModelAndView listarPedidos() {
		ModelAndView mv = new ModelAndView("sacola");
		mv.addObject("listaPratosPedido", listaPedido);
		mv.addObject("valorTotal", getValorTotalCarrinho());
		return mv;
	}
	
	@RequestMapping("/confirmar")
	public ModelAndView confirmarPedido(@RequestParam(value="enderecoPedido") String endereco) {
		Pedido pedido = new Pedido();
		pedido.setEnderecoPedido(endereco);
		pedido.setValorTotal(getValorTotalCarrinho());
		pedido.setCodigoCliente(usuarioService.getUserLogado().getCodigo());
		String pratoPedido = " ";
		for (Prato prato : listaPedido) {
			pratoPedido += prato.getNome() + " , " + "R$" + prato.getPreco() + "\n";
		}
		System.out.println(pratoPedido);
		
		pedido.setPratosPedido(pratoPedido);
		
		pedidoService.cadastrar(pedido);
		
		//          ENVIO DO EMAIL       //
		
	
		
		ModelAndView mv = new ModelAndView("redirect:/EmGula/cardapio");
		limparCarrinho();
		return mv;
	}
	
	@RequestMapping("/excluir/{codigo}")
	public ModelAndView excluirPratoDoPedido(@PathVariable Long codigo) {
		for (Prato prato : listaPedido) {
			if (prato.getId() == codigo) {
				listaPedido.remove(prato);
				break;
			}
		}
		ModelAndView mv = new ModelAndView("redirect:/EmGula/pedido/sacola");
		return mv;
	}
	
	@RequestMapping("/logout")
	public void logout() {
		limparCarrinho();
	}
}
