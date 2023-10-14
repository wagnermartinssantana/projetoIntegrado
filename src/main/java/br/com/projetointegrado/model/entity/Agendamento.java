package br.com.projetointegrado.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Agendamento implements Serializable {

	private static final long serialVersionUID = 5331002443296534261L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToMany
    private List<Funcionario> funcionarios;

    @ManyToMany
    private List<Servico> servicos;

    @Temporal(TemporalType.DATE)
    private Date data;

    private String horario;

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

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    public Agendamento() {
    }

	public Agendamento(Long id, Cliente cliente, List<Funcionario> funcionarios, List<Servico> servicos, Date data,
			String horario) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.funcionarios = funcionarios;
		this.servicos = servicos;
		this.data = data;
		this.horario = horario;
	}
    
}