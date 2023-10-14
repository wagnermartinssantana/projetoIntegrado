package br.com.projetointegrado.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.projetointegrado.util.CodigoRetornoApiEnum;

public class DefaultResponse implements Serializable {
	
	private static final long serialVersionUID = -6200421683942778474L;
	
	private Integer status;
	
    private CodigoRetornoApiEnum codigo;
    
    private LocalDateTime dataHora;

    private String mensagem;

    private Integer totalItens;
    
    private Object data;

    public void setData(Object o) {
        this.data = o;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public CodigoRetornoApiEnum getCodigo() {
        return codigo;
    }

    public void setCodigo(CodigoRetornoApiEnum codigoRetornoApiEnum) {
        this.codigo = codigoRetornoApiEnum;
    }
    
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public int getTotalItens() {
    	return totalItens;
    }
    
    public void setTotalItens(Integer totalItens) {
    	this.totalItens = totalItens;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String message) {
        this.mensagem = message;
    }
    
    public DefaultResponse() {
    	this.setCodigo(CodigoRetornoApiEnum.SUCESSO);
        this.setStatus(200);
        this.setDataHora(LocalDateTime.now() );
        this.setMensagem("");
        this.setTotalItens(0);
        this.setData(null);
    }
}