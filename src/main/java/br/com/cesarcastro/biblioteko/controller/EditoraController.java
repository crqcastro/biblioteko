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

import br.com.cesarcastro.biblioteko.model.EditoraModel;
import br.com.cesarcastro.biblioteko.service.EditoraService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/editora")
@Validated
public class EditoraController {

	private EditoraService editoraService;

	@Autowired
	public EditoraController(EditoraService editoraService) {
		this.editoraService = editoraService;
	}

	@ApiOperation(value = "Salva os dados de uma editora")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Editora salvo"),
			@ApiResponse(code = 204, message = "Erro ao salvar uma Editora"),
			@ApiResponse(code = 400, message = "Requição não pode ser processada"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@PostMapping
	public ResponseEntity<EditoraModel> salvarEditora(@NotNull @RequestBody EditoraModel editora) throws URISyntaxException {
		this.editoraService.salvar(editora);
		if (editora.getId() == null)
			return ResponseEntity.status(204).build();
		return ResponseEntity.created(new URI("/editora/" + editora.getId())).build();
	}
	

	@ApiOperation(value = "Obtem lista de editoras pelo nome")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista de editoras obtida com sucesso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@GetMapping
	public ResponseEntity<List<EditoraModel>> listaEditoraPeloNome(@RequestBody EditoraModel editora){
		return ResponseEntity.ok(this.editoraService.obterEditorasPeloNome(editora));
	}
	
	@ApiOperation(value = "Obtem editora pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Editora obtida com sucesso"),
			@ApiResponse(code = 404, message = "Editora não encontrada"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@GetMapping(path = "/{id}")
	public ResponseEntity<EditoraModel> getEditoraById(@PathVariable("id") Long id){
		return ResponseEntity.ok(this.editoraService.obterEditoraById(id));
	}
}
