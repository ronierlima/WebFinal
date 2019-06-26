package br.com.emgula.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.emgula.model.Prato;
import br.com.emgula.model.Usuario;
import br.com.emgula.service.PratoService;
import br.com.emgula.service.UsuarioService;

@Controller
@RequestMapping("/EmGula")
public class UsuarioController {

	@Autowired
	private PratoService pratoService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping("")
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

	@RequestMapping("/cardapio")
	public ModelAndView verCardapio() {

		List<Prato> pratos = pratoService.listar();

		ModelAndView mv = new ModelAndView("cardapio");
		mv.addObject("listaDePratos", pratos);

		return mv;
	}

	@RequestMapping("/cadastrar")
	public ModelAndView cadastro() {

		ModelAndView mv = new ModelAndView("cadastro");

		mv.addObject("usuario", new Usuario());
		return mv;
	}

	@PostMapping("/salvar")
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
	
	@RequestMapping("/logar")
	public ModelAndView logar() {
		
		ModelAndView mv = new ModelAndView("login");
		
		return mv;
	}
}
