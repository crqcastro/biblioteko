package br.com.cesarcastro.biblioteko.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "livro")
@Entity
@Table(name="livro")
public class LivroModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String ISBN;
	private String ISBN10;
	private String ISBN13;
	private Integer edicao;
	private Integer anoPublicacao;
	private String resumo;
	@ManyToMany
	private List<String> termosChave;
	@ManyToOne
	private EditoraModel editora;
	@ManyToMany
	private List<AutorModel> autores;
	
}
