package br.com.projetointegrado.util;

public enum CodigoRetornoApiEnum {

    SUCESSO("00", "SUCCESS"),
    ERRO("01", "ERROR");

    private String codigo;
    
    private String nomeCodigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNomeCodigo() {
		return nomeCodigo;
	}

	public void setNomeCodigo(String nomeCodigo) {
		this.nomeCodigo = nomeCodigo;
	}

	private CodigoRetornoApiEnum() {
	}
	
	private CodigoRetornoApiEnum(String codigo, String nomeCodigo) {
		this.codigo = codigo;
		this.nomeCodigo = nomeCodigo;
	}
}
