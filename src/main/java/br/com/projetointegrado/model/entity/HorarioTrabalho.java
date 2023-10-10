package br.com.projetointegrado.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.io.Serializable;
import java.sql.Time;

@Entity
public class HorarioTrabalho implements Serializable {

	private static final long serialVersionUID = 5750704989312217872L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @ManyToOne
    private Funcionario funcionario;

    private String diaSemana;
    
    private Time horarioInicio;
    
    private Time horarioFim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Time getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(Time horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public Time getHorarioFim() {
		return horarioFim;
	}

	public void setHorarioFim(Time horarioFim) {
		this.horarioFim = horarioFim;
	}

    public HorarioTrabalho() {
  	}
    
    public HorarioTrabalho(Long id, Funcionario funcionario, String diaSemana, Time horarioInicio, Time horarioFim) {
		super();
		this.id = id;
		this.funcionario = funcionario;
		this.diaSemana = diaSemana;
		this.horarioInicio = horarioInicio;
		this.horarioFim = horarioFim;
	}
}