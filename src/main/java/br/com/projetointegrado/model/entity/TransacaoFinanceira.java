package br.com.projetointegrado.model.entity;

import javax.persistence.*;

import br.com.projetointegrado.model.enums.TipoTransacaoEnum;

import java.util.Date;

@Entity
public class TransacaoFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTransacaoEnum tipo;

    private double valor;
    private Date data;

    public TransacaoFinanceira() {
    }

    public TransacaoFinanceira(TipoTransacaoEnum tipo, double valor, Date data) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
    }

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
}