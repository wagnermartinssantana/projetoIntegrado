package br.com.projetointegrado.model.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = 5573314337011743657L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Servico servico;

    private int estrelas;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    private Date dataAvaliacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvalicao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

	public Avaliacao() {
	}
    
	public Avaliacao(Long id, Cliente cliente, Servico servico, int estrelas, String comentario, Date dataAvaliacao) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.servico = servico;
		this.estrelas = estrelas;
		this.comentario = comentario;
		this.dataAvaliacao = dataAvaliacao;
	}

}