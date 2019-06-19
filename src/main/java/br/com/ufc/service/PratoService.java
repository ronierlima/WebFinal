package br.com.ufc.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.ufc.model.Prato;
import br.com.ufc.repository.PratoRepository;
import br.com.ufc.util.RonierFileUtils;


@Service
public class PratoService {
	
	@Autowired
	private PratoRepository pr;
	

	public void cadastrar(Prato prato, MultipartFile imagem) {
	
		prato.setCod(UUID.randomUUID().toString());
		
		String caminho = "images/" + prato.getCod() + ".png";
		
		prato.setImagemCaminho(caminho);
		
		RonierFileUtils.salvarImagem(caminho, imagem);
		
		pr.save(prato); 
	}


	public List<Prato> listar() {
		
		return pr.findAll();
	}


	public void excluir(Long codigo) {
		pr.deleteById(codigo);
		
	}


	public Prato buscarPorId(Long codigo) {
		return pr.getOne(codigo);
	}
	
}
