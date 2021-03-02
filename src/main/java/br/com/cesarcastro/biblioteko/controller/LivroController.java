package br.com.cesarcastro.biblioteko.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cesarcastro.biblioteko.exceptions.DaoException;
import br.com.cesarcastro.biblioteko.model.LivroModel;
import br.com.cesarcastro.biblioteko.service.LivroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@RestController
@RequestMapping(path = "/livro")
@Validated
public class LivroController {

	private LivroService livroService;

	@Autowired
	public LivroController(LivroService livroService) {
		this.livroService = livroService;
	}

	@ApiOperation(value = "Obtém detalhes de um livro por seu ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um livro por seu ID"),
			@ApiResponse(code = 404, message = "Livro não encontrado"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@GetMapping(path = "/{id}")
	public ResponseEntity<LivroModel> getLivroById(@PathVariable("id") Long id) {

		LivroModel livro = livroService.findById(id);
		if (livro == null || livro.getTitulo() == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(livro);
	}

	@ApiOperation(value = "Salva os dados de um livro")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Livro salvo"),
			@ApiResponse(code = 204, message = "Erro ao salvar um livro"),
			@ApiResponse(code = 400, message = "Requição não pode ser processada"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@PostMapping
	public ResponseEntity<String> salvarLivro(@NotNull @RequestBody LivroModel livro) throws URISyntaxException {
		if(!StringUtils.hasLength(livro.getTitulo())||livro.getAutores()==null)
			return ResponseEntity.badRequest().build();
		
		try {
			livroService.salvarLivro(livro);
		}catch(DaoException e) {
			return ResponseEntity.badRequest().body("Livro ja existente");
		}
		
		if (livro.getId() == null)
			return ResponseEntity.status(204).build();

		return ResponseEntity.created(new URI("/livro/" + livro.getId())).build();
	}

	@ApiOperation(value = "Obtém lista de livros pelo titulo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma lista de livros pelo título"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@GetMapping(path = "/titulo")
	public ResponseEntity<List<LivroModel>> getLivroByTitulo(@RequestBody LivroModel params) {

		if(StringUtils.hasLength(params.getTitulo()))
			return ResponseEntity.badRequest().build();
		
		List<LivroModel> livros = livroService.findByParams(params);

		return ResponseEntity.ok(livros);
	}
	
	@ApiOperation(value = "Obtém lista de livros pelo ISBN")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma lista de livros pelo ISBN"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@GetMapping(path = "/ISBN")
	public ResponseEntity<List<LivroModel>> getLivroByISBN(@RequestBody LivroModel params) {

		if(StringUtils.hasLength(params.getISBN()))
			return ResponseEntity.badRequest().build();
		
		List<LivroModel> livros = livroService.findByParams(params);

		return ResponseEntity.ok(livros);
	}

	@ApiOperation(value = "Obtém lista de livros")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma lista de livros"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@GetMapping
	public ResponseEntity<List<LivroModel>> getListaLivros(
			@RequestParam(name = "offset", defaultValue = "0") Integer offset,
			@RequestParam(name = "size", defaultValue = "10") Integer size) {

		List<LivroModel> livros = livroService.getListaLivros(offset, size);

		return ResponseEntity.ok(livros);
	}

	@ApiOperation(value = "Deleta um livro pelo Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna 200 quando conclui"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<LivroModel> deleteLivro(@PathVariable("id") Long id) {

		livroService.deleteLivro(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ApiOperation(value = "Altera o status de um livro pelo Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna 200 quando conclui"),
			@ApiResponse(code = 404, message = "ID inexistente"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@PutMapping(path = "/{operacao}/{id}")
	public ResponseEntity<LivroModel> updateStatusLivro(@PathVariable("id") Long id,
			@PathVariable("operacao") Integer status) {

		try {
			livroService.alterarStatusLivro(id, status);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ApiOperation(value = "Altera os dados de um livro ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna 200 quando conclui"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@PutMapping
	public ResponseEntity<LivroModel> updateLivro(@NotNull @RequestBody LivroModel livro) {

		livroService.alterarLivro(livro);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}