package br.com.tiagolivrera.concessionariaTOP.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_CARRO")
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carro_seq")
	@SequenceGenerator(name = "carro_seq", sequenceName = "sq_matricula", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "NOME_CARRO", length = 15, nullable = false)
	private String nome_carro;

	@Column(name = "COR", length = 15, nullable = false)
	private String cor;

	@Column(name = "CHASSI", length = 15, nullable = false, unique = true)
	private String chassi;

	@OneToMany(mappedBy = "carro")
	private List<Acessorio> acessorios;

	@ManyToOne
	@JoinColumn(name = "marca_id")
	private Marca marca;

	@Column(name = "VALOR", nullable = false)
	private BigDecimal valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(List<Acessorio> acessorios) {
		this.acessorios = acessorios;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome_carro;
	}

	public void setNome(String nome) {
		this.nome_carro = nome;
	}

	@Override
	public String toString() {
		return "Carro [id=" + id + ", nome_carro=" + nome_carro + ", cor=" + cor + ", chassi=" + chassi
				+ ", valor=" + valor + "]";
	}

}
