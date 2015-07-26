package br.com.ajax.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "item_emprestimo")
public class ItemEmprestimo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 3)
	private Integer quantidade = 1;

	@ManyToOne
	@JoinColumn(name = "livro_id", nullable = false)
	private Livro livro;

	@ManyToOne
	@JoinColumn(name = "emprestimo_id", nullable = false)
	private Emprestimo emprestimo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
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
		ItemEmprestimo other = (ItemEmprestimo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	public boolean isLivroAssociado() {
		return this.getLivro() != null && this.getLivro().getId() != null;
	}

	@Transient
	public boolean isEstoqueSuficiente() {
		return this.getEmprestimo().isEmprestado()
				|| this.getLivro().getId() == null
				|| this.getLivro().getQuantidadeEstoque() >= getQuantidade();
	}

	@Transient
	public boolean isEstoqueInsuficiente() {
		return !this.isEstoqueSuficiente();
	}

}
