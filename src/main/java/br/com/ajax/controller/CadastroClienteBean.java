package br.com.ajax.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Cliente;
import br.com.ajax.model.Curso;
import br.com.ajax.model.Endereco;
import br.com.ajax.model.Sala;
import br.com.ajax.repository.CursoRepository;
import br.com.ajax.repository.SalaRepository;
import br.com.ajax.service.CadastroClienteService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CursoRepository cursoRepository;

	@Inject
	private SalaRepository salaRepository;

	@Inject
	private CadastroClienteService cadastroClienteService;

	private Cliente cliente;

	private List<Curso> cursos;
	private List<Sala> salas;

	private String nome;
	private String email;

	public CadastroClienteBean() {
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.cursos = this.cursoRepository.cursos();
			this.salas = this.salaRepository.salas();
			
		}
	}

	public void salvar() {
		this.cliente = cadastroClienteService.salvar(this.cliente);
		limpar();

		FacesUtil.addInfoMessage("Cliente cadastrado com sucesso.");
	}

	public void limpar() {
		cliente = new Cliente();
		cliente.setEndereco(new Endereco());
	}

	public CursoRepository getCursoRepository() {
		return cursoRepository;
	}

	public void setCursoRepository(CursoRepository cursoRepository) {
		this.cursoRepository = cursoRepository;
	}

	public SalaRepository getSalaRepository() {
		return salaRepository;
	}

	public void setSalaRepository(SalaRepository salaRepository) {
		this.salaRepository = salaRepository;
	}

	public CadastroClienteService getCadastroClienteService() {
		return cadastroClienteService;
	}

	public void setCadastroClienteService(
			CadastroClienteService cadastroClienteService) {
		this.cadastroClienteService = cadastroClienteService;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
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

	public boolean isEditando() {
		return this.cliente.getId() != null;
	}

}
