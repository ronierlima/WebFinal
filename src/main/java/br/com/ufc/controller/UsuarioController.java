package br.com.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ufc.model.Prato;
import br.com.ufc.service.PratoService;

@Controller
@RequestMapping("/EmGula")
public class UsuarioController {

	@Autowired
	private PratoService pratoService;
	
	@RequestMapping("")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("index");

		return mv;
	}
	

	@RequestMapping("/Cardapio")
	public ModelAndView verCardapio() {

		List<Prato> pratos = pratoService.listar();

		ModelAndView mv = new ModelAndView("cardapio");
		mv.addObject("listaDePratos", pratos);

		return mv;
	}
}
