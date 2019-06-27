package br.com.emgula.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.emgula.model.Prato;
import br.com.emgula.model.Role;
import br.com.emgula.model.Usuario;
import br.com.emgula.service.PratoService;
import br.com.emgula.service.UsuarioService;
import br.com.emgula.model.Pedido;
import br.com.emgula.service.PedidoService;

@Controller
public class UsuarioController {

	@Autowired
	private PratoService pratoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PedidoService pedidoService;

	@RequestMapping("")
	public ModelAndView homenull() {

		List<Prato> pratos = pratoService.listar();
		List<Prato> pratosHome = new ArrayList<>();
		int i = 0;

		for (int j = 0; j < pratos.size(); j++) {
			if (i == 3)
				break;
			pratosHome.add(pratos.get(j));
			i++;
		}

		ModelAndView mv = new ModelAndView("redirect:/EmGula");
		mv.addObject("listaDePratos", pratosHome);

		return mv;
	}

	@RequestMapping("/")
	public ModelAndView homebarra() {

		List<Prato> pratos = pratoService.listar();
		List<Prato> pratosHome = new ArrayList<>();
		int i = 0;

		for (int j = 0; j < pratos.size(); j++) {
			if (i == 3)
				break;
			pratosHome.add(pratos.get(j));
			i++;
		}

		ModelAndView mv = new ModelAndView("redirect:/EmGula");
		mv.addObject("listaDePratos", pratosHome);

		return mv;
	}

	@RequestMapping("/EmGula")
	public ModelAndView home() {

		List<Prato> pratos = pratoService.listar();
		List<Prato> pratosHome = new ArrayList<>();
		int i = 0;

		for (int j = 0; j < pratos.size(); j++) {
			if (i == 3)
				break;
			pratosHome.add(pratos.get(j));
			i++;
		}

		ModelAndView mv = new ModelAndView("index");
		mv.addObject("listaDePratos", pratosHome);

		return mv;
	}

	@RequestMapping("/EmGula/cardapio")
	public ModelAndView verCardapio() {

		List<Prato> pratos = pratoService.listar();

		ModelAndView mv = new ModelAndView("cardapio");
		mv.addObject("listaDePratos", pratos);

		return mv;
	}

	@RequestMapping("/EmGula/cadastrar")
	public ModelAndView cadastro() {

		ModelAndView mv = new ModelAndView("cadastro");

		mv.addObject("usuario", new Usuario());
		return mv;
	}

	@PostMapping("/EmGula/salvar")
	public ModelAndView salvarUsuario(@Validated Usuario u, BindingResult result) throws Exception {

		ModelAndView mv = new ModelAndView("cadastro");

		if (result.hasErrors())
			return mv;

		try {
			usuarioService.cadastrar(u);
			mv.addObject("mensagem", "Usuario cadastrada com sucesso!");
		} catch (Exception e) {
			mv.addObject("erro", "Caba, Este email já ta sendo usado, tenta outro valá !!!");
		}

		mv.addObject("usuario", new Usuario());
		return mv;
	}

	@RequestMapping("/EmGula/logar")
	public ModelAndView logar() {

		ModelAndView mv = new ModelAndView("login");

		return mv;
	}

	@RequestMapping("/EmGula/addCarrinho/{codigo}")
	public ModelAndView aadPrato(@PathVariable Long codigo) {
		System.out.println(codigo);
		Prato prato = pratoService.buscarPorId(codigo);
		System.out.println(prato);
		PedidoController.adicionarPratoAoPedido(prato);
		ModelAndView mv = new ModelAndView("redirect:/EmGula/pedido/sacola");
		return mv;

	}
	
	@RequestMapping("/EmGula/listarClientes")
	public ModelAndView listarClients() {
		
		List<Usuario> users = usuarioService.listarClientes();
		
		ModelAndView mv = new ModelAndView("listarClientes");
		mv.addObject("listaDeClientes", users);

		return mv;
	}

	@RequestMapping("/EmGula/historico")
	public ModelAndView listarPedidos() {
		ModelAndView mv = new ModelAndView("historicoPedidos");
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;
		Usuario cliente = usuarioService.buscarPorEmail(user.getUsername());
		List<Pedido> pedidos = pedidoService.listarPedidosPorId(cliente.getCodigo());
		mv.addObject("historicoPedidos", pedidos);
		return mv;
	}

	@RequestMapping("/painel/atualizar")
	public ModelAndView atualizarCliente() {
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;
		Usuario cliente = usuarioService.buscarPorEmail(user.getUsername());
		ModelAndView mv = new ModelAndView("cadastroCliente");
		mv.addObject("cliente", cliente);
		return mv;
	}
}
