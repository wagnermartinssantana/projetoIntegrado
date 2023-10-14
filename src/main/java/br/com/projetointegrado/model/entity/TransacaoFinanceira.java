package br.com.projetointegrado.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.projetointegrado.model.enums.TipoTransacaoEnum;

import java.io.Serializable;
import java.util.Date;

@Entity
public class TransacaoFinanceira implements Serializable {

	private static final long serialVersionUID = -5674968147982753835L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTransacaoEnum tipo;

    private double valor;
    
    private Date data;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoTransacaoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacaoEnum tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public TransacaoFinanceira() {
    }

    public TransacaoFinanceira(TipoTransacaoEnum tipo, double valor, Date data) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
    }
    
}