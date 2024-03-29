package br.com.emgula.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.emgula.model.Prato;
import br.com.emgula.service.PratoService;



@Controller
@RequestMapping("EmGula/pratos")
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
	public ModelAndView salvarPrato(@Validated Prato p, BindingResult result,
			@RequestParam(value = "imagem") MultipartFile imagem) {

		ModelAndView mv = new ModelAndView("addPrato");

		if (result.hasErrors())
			return mv;

		pratoService.cadastrar(p, imagem);
		mv.addObject("mensagem", "Prato cadastrada com sucesso!");

		mv.addObject("prato", new Prato());
		return mv;
	}

	@RequestMapping("/listar")
	public ModelAndView listarPratos() {

		List<Prato> pratos = pratoService.listar();

		ModelAndView mv = new ModelAndView("listarPratos");
		mv.addObject("listaDePratos", pratos);

		return mv;
	}

	@RequestMapping("/excluir/{codigo}")
	public ModelAndView excluir(@PathVariable Long codigo) {
		// trazer do banco
		pratoService.excluir(codigo);

		ModelAndView mv = new ModelAndView("redirect:/EmGula/cardapio");

		return mv;
	}

	@RequestMapping("/editarPrato/{codigo}")
	public ModelAndView editarPrato(@PathVariable Long codigo) {
		// trazer do banco
		Prato prato = pratoService.buscarPorId(codigo);

		ModelAndView mv = new ModelAndView("atualizarPrato");
		mv.addObject("prato", prato);

		return mv;
	}

	@PostMapping("/atualizar")
	public ModelAndView atualizar(@Validated Prato p, BindingResult result, @RequestParam(value="imagem") MultipartFile imagem) {
		
		ModelAndView mv = new ModelAndView("atualizarPrato");
		
		if (result.hasErrors())
			return mv;

		mv.addObject("mensagem", "Prato atualizado com sucesso!");
		pratoService.atualizar(p, imagem);
		
		return mv;
	}

}