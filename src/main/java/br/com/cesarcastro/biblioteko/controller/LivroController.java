package br.com.cesarcastro.biblioteko.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

import br.com.cesarcastro.biblioteko.model.LivroModel;
import br.com.cesarcastro.biblioteko.service.LivroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/livro")
@Validated
public class LivroController {

	private LivroService livroService;

	@Autowired
	public LivroController(LivroService livroService) {
		this.livroService = livroService;
	}

	@ApiOperation(value = "Obtém detalhes de um livro por seu ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um livro por seu ID"),
			@ApiResponse(code = 404, message = "Livro não encontrado"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@GetMapping(path = "/{id}")
	public ResponseEntity<LivroModel> getLivroById(@PathVariable("id") Long id){
		
		LivroModel livro = livroService.findById(id);
		if(livro == null || livro.getTitulo() == null)
			return ResponseEntity.notFound().build();
		System.out.println(livro.getAutores().size());
		return ResponseEntity.ok(livro);
	}
	
	@ApiOperation(value = "Salva os dados de um livro")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Livro salvo"),
			@ApiResponse(code = 204, message = "Erro ao salvar um livro"),
			@ApiResponse(code = 400, message = "Requição não pode ser processada"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@PostMapping
	public ResponseEntity<LivroModel> salvarLivro(@NotNull @RequestBody LivroModel livro) throws URISyntaxException{
		livroService.salvarLivro(livro);
		if(livro.getId()==null)
			return ResponseEntity.status(204).build();
		
		return ResponseEntity.created(new URI("/livro/"+livro.getId())).build();
	}
	
	@ApiOperation(value = "Obtém lista de livros pelo titulo")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna uma lista de livros pelo título"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@GetMapping
	public ResponseEntity<List<LivroModel>> getLivroByTitulo(@RequestBody LivroModel params){
		
		List<LivroModel> livros = livroService.findByParams(params);
		
		return ResponseEntity.ok(livros);
	}
	
}