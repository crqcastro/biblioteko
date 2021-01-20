package br.com.cesarcastro.biblioteko.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "livro")
@Entity
@Table(name="tbl_livro",uniqueConstraints={@UniqueConstraint(columnNames={"ISBN"})})
@DynamicUpdate
public class LivroModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String ISBN;
	private Integer edicao;
	private Integer anoPublicacao;
	@Column(columnDefinition = "text")
	private String resumo;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tbl_termosChave")
	@Fetch(FetchMode.SUBSELECT)
	private List<String> termosChave;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private EditoraModel editora;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name="tbl_livro_autores",
	   joinColumns={@JoinColumn(name="id_livro")},
	   inverseJoinColumns={@JoinColumn(name="id_autor")})
	private List<AutorModel> autores;
	private Integer status;
	
}
