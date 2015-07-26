package br.com.ajax.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.ajax.model.Cliente;
import br.com.ajax.model.Emprestimo;
import br.com.ajax.model.ItemEmprestimo;
import br.com.ajax.model.Livro;
import br.com.ajax.model.Usuario;
import br.com.ajax.repository.ClienteRepository;
import br.com.ajax.repository.LivroRepository;
import br.com.ajax.repository.UsuarioRepository;
import br.com.ajax.service.CadastroEmprestimoService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEmprestimoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	private ClienteRepository clienteRepository;

	@Inject
	private LivroRepository livroRepository;

	@Inject
	private CadastroEmprestimoService cadastroEmprestimoService;

	@Produces
	@EmprestimoEdicao
	private Emprestimo emprestimo;

	private List<Usuario> bibliotecarios;

	private Livro livroLinhaEditavel;

	private String isbn;
	
	private Livro livro;

	public CadastroEmprestimoBean() {
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.bibliotecarios = this.usuarioRepository.bibliotecarios();

			this.emprestimo.adicionarItemVazio();
		}
	}

	public void emprestimoAlterado(@Observes EmprestimoAlteradoEvent event) {
		this.emprestimo = event.getEmprestimo();
	}

	public void carregarLivroPorIsbn() {
		if (StringUtils.isNotEmpty(this.isbn)) {
			this.livroLinhaEditavel = this.livroRepository.porISBN(this.isbn);
			this.carregarLivroLinhaEditavel();
		}
	}

	public void carregarLivroLinhaEditavel() {
		ItemEmprestimo item = this.emprestimo.getItens().get(0);

		if (this.livroLinhaEditavel != null) {
			if (this.existeItemComLivro(this.livroLinhaEditavel)) {
				FacesUtil.addErrorMessage("já existe o livro informado.");
			} else {
				item.setLivro(this.livroLinhaEditavel);

				this.emprestimo.adicionarItemVazio();
				this.livroLinhaEditavel = null;
				this.isbn = null;
			}
		}
	}

	public boolean existeItemComLivro(Livro livro) {
		boolean existeItem = false;

		for (ItemEmprestimo item : this.getEmprestimo().getItens()) {
			if (livro.equals(item.getLivro())) {
				existeItem = true;
				break;
			}
		}

		return existeItem;
	}

	public void atualizarQauntidade(ItemEmprestimo item, int linha) {
		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getEmprestimo().getItens().remove(linha);
			}
		}
	}

	public void salvar() {
		this.emprestimo.removerItemVazio();

		try {
			this.emprestimo = cadastroEmprestimoService.salvar(emprestimo);

			FacesUtil.addInfoMessage("Emprestimo salvo com sucesso.");
		} finally {
			this.emprestimo.adicionarItemVazio();
		}
	}

	private void limpar() {
		emprestimo = new Emprestimo();
	}

	public List<Cliente> completarCliente(String nome) {
		return this.clienteRepository.porNome(nome);
	}

	public List<Livro> completarLivro(String titulo) {
		return this.livroRepository.listaPorTitulo(titulo);
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public List<Usuario> getBibliotecarios() {
		return bibliotecarios;
	}

	public Livro getLivroLinhaEditavel() {
		return livroLinhaEditavel;
	}

	public void setLivroLinhaEditavel(Livro livroLinhaEditavel) {
		this.livroLinhaEditavel = livroLinhaEditavel;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public boolean isEditando() {
		return this.emprestimo.getId() != null;
	}

	public Livro getLivro() {
		return livro;
	}
	
	

}
