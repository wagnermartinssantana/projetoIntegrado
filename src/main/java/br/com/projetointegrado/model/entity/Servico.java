package br.com.projetointegrado.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.io.Serializable;
import java.util.List;

@Entity
public class Servico implements Serializable {

	private static final long serialVersionUID = -4664978181479379914L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
    private Long id;

    private String nome;
    
    private String descricao;
    
    private int duracao;
    
    private double preco;

    @ManyToMany
    private List<Produto> produtos;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public Servico() {
    }

	public Servico(Long id, String nome, String descricao, int duracao, double preco, List<Produto> produtos) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.duracao = duracao;
		this.preco = preco;
		this.produtos = produtos;
	}
}