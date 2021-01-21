package br.com.cesarcastro.biblioteko.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cesarcastro.biblioteko.model.AutorModel;
import br.com.cesarcastro.biblioteko.service.AutorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/autor")
@Validated
public class AutorController {

	private AutorService autorService;

	@Autowired
	public AutorController(AutorService autorService) {
		this.autorService = autorService;
	}

	@ApiOperation(value = "Salva os dados de um autor")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Autor salvo"),
			@ApiResponse(code = 204, message = "Erro ao salvar um Autor"),
			@ApiResponse(code = 400, message = "Requição não pode ser processada"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@PostMapping
	public ResponseEntity<AutorModel> salvarAuto(@NotNull @RequestBody AutorModel autor) throws URISyntaxException {
		autorService.salvar(autor);
		if (autor.getId() == null)
			return ResponseEntity.status(204).build();
		return ResponseEntity.created(new URI("/autor/" + autor.getId())).build();
	}
	
	@ApiOperation(value = "Lista autores")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista obtidacom sucesso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@GetMapping
	public ResponseEntity<List<AutorModel>> getAutorByNamePostNameShortName(@RequestBody AutorModel autor) throws URISyntaxException {
		return ResponseEntity.ok(this.autorService.findAutorByProps(autor));
	}
	
	@ApiOperation(value = "Obtem o autor pelo Id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Autor obtido com sucesso"),
			@ApiResponse(code = 404, message = "Autor não encontrado"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@GetMapping(path = "/{id}")
	public ResponseEntity<AutorModel> getAutorById(@PathVariable("id") Long id) throws URISyntaxException {
		AutorModel autor= this.autorService.findAutorById(id);
		
		if(autor == null || autor.getId()==null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(autor);
	}
}
