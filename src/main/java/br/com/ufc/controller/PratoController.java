package br.com.ufc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.ufc.model.Prato;
import br.com.ufc.service.PratoService;


@Controller
@RequestMapping("/pratos")
public class PratoController {
	
	@Autowired
	private PratoService pratoService;
	
	
	@RequestMapping("/cadastrarPrato")
	public ModelAndView cadastrarPratos() {
		
		ModelAndView mv = new ModelAndView("addPrato");
		
		mv.addObject("prato", new Prato());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarPrato(@Validated Prato p, BindingResult result, @RequestParam(value="imagem") MultipartFile imagem) {
		
		
		
		ModelAndView mv = new ModelAndView("addPrato");
		
		if(result.hasErrors())
			return mv;
		
		pratoService.cadastrar(p, imagem);
		mv.addObject("mensagem","Prato cadastrada com sucesso");
		
		mv.addObject("prato", new Prato());
		return mv;
	}
}
