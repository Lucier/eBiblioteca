package br.com.ajax.model;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

import br.com.ajax.validation.Telefone;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 80, nullable = false)
	private String nome;

	@Email
	@Column(length = 100, nullable = false)
	private String email;

	@Telefone
	@Column(length = 15, nullable = false)
	private String telefone;

	private String tipoDeCliente;

	private String disciplina;

	private String funcao;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "curso_id", nullable = false)
	private Curso curso;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sala_id", nullable = false)
	private Sala sala;

	@Nonnull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id", nullable = false)
	private Endereco endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public String getTipoDeCliente() {
		return tipoDeCliente;
	}

	public void setTipoDeCliente(String tipoDeCliente) {
		this.tipoDeCliente = tipoDeCliente;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
