package br.com.ufc.controller;

import java.util.ArrayList;
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

	@RequestMapping("/Cardapio")
	public ModelAndView verCardapio() {

		List<Prato> pratos = pratoService.listar();
		
		ModelAndView mv = new ModelAndView("cardapio");
		mv.addObject("listaDePratos", pratos);

		return mv;
	}
}
