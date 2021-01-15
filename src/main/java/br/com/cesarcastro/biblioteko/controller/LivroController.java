package br.com.cesarcastro.biblioteko.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cesarcastro.biblioteko.model.Autor;
import br.com.cesarcastro.biblioteko.model.Editora;
import br.com.cesarcastro.biblioteko.model.LivroModel;
import br.com.cesarcastro.biblioteko.service.LivroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/livro")
public class LivroController {

	private LivroService livroService;

	@Autowired
	public LivroController(LivroService livroService) {
		this.livroService = livroService;
	}

	@ApiOperation(value = "Obtem uma lista de livros")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna uma lista de livros que atendem aos parâetros informados"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@GetMapping
	public ResponseEntity<List<LivroModel>> getLisvros(
			@RequestParam(name = "offset", defaultValue = "0") Long offset,
			@RequestParam(name = "size", defaultValue = "30") Long size, 
			@RequestParam(name = "titulo") String titulo,
			@RequestParam(name = "editora") String editora, 
			@RequestParam(name = "chaves") String chaves,
			@RequestParam(name = "autores") String autores) {

		LivroModel params = new LivroModel();
		params.setTitulo(titulo);
		
		List<Autor> listaAutor = new ArrayList<>();
		Arrays.asList(autores.split(" ")).forEach(s -> {
			Autor autor = new Autor(s);
			listaAutor.add(autor);
		});
		params.setAutores(listaAutor);
		params.setEditora(new Editora(editora));
		params.setTermosChave(chaves==null?null:Arrays.asList(chaves.split(" ")));
		
		
		return ResponseEntity.ok(this.livroService.buscarLivros(offset, size, params));
	}
}
