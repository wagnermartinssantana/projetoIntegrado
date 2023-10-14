package br.com.projetointegrado.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Promocao implements Serializable {

	private static final long serialVersionUID = -8565008184133906797L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @ManyToOne
    private Servico servico;
    
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    
    private String descricao;
    
    private double desconto;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

    public Promocao() {
 	}  

    public Promocao(Long id, Servico servico, Date dataInicio, Date dataFim, String descricao, double desconto) {
		super();
		this.id = id;
		this.servico = servico;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.descricao = descricao;
		this.desconto = desconto;
	}

}