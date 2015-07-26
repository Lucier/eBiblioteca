package br.com.ajax.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "emprestimo")
public class Emprestimo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_emprestimo", nullable = false)
	private Date dataEmprestimo;

	@Column(columnDefinition = "text")
	private String observacao;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_devolucao", nullable = false)
	private Date dataDevolucao;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "bibliotecario_id", nullable = false)
	private Usuario bibliotecario;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ItemEmprestimo> itens = new ArrayList<>();

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 15)
	private StatusLivro status = StatusLivro.RESERVA;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Usuario getBibliotecario() {
		return bibliotecario;
	}

	public void setBibliotecario(Usuario bibliotecario) {
		this.bibliotecario = bibliotecario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemEmprestimo> getItens() {
		return itens;
	}

	public void setItens(List<ItemEmprestimo> itens) {
		this.itens = itens;
	}

	public StatusLivro getStatus() {
		return status;
	}

	public void setStatus(StatusLivro status) {
		this.status = status;
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
		Emprestimo other = (Emprestimo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	public boolean isNovo() {
		return getId() == null;
	}

	@Transient
	public boolean isExistente() {
		return !isNovo();
	}

	public void adicionarItemVazio() {
		if (this.isReserva()) {
			Livro livro = new Livro();

			ItemEmprestimo item = new ItemEmprestimo();
			item.setLivro(livro);
			item.setEmprestimo(this);

			this.getItens().add(0, item);
		}
	}

	public void removerItemVazio() {
		ItemEmprestimo primeiroItem = this.getItens().get(0);

		if (primeiroItem != null && primeiroItem.getLivro().getId() == null) {
			this.getItens().remove(0);
		}
	}

	@Transient
	public boolean isReserva() {
		return StatusLivro.RESERVA.equals(this.getStatus());
	}

	@Transient
	public boolean isEmprestado() {
		return StatusLivro.EMPRESTADO.equals(this.getStatus());
	}

	@Transient
	public boolean isEmprestavel() {
		return this.isExistente() && this.isReserva();
	}

	@Transient
	public boolean isNaoEmprestavel() {
		return !this.isEmprestavel();
	}

	@Transient
	public boolean isDevolvido() {
		return StatusLivro.DEVOLVIDO.equals(this.getStatus());
	}

	@Transient
	public boolean isDevolvivel() {
		return this.isExistente() && !this.isDevolvido();
	}

	@Transient
	public boolean isNaoDevolvivel() {
		return !this.isDevolvivel();
	}

	@Transient
	public boolean isAlteravel() {
		return this.isReserva();
	}

	@Transient
	public boolean isNaoAlteravel() {
		return !this.isAlteravel();
	}

}
