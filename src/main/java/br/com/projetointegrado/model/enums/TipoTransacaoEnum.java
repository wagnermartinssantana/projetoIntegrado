package br.com.projetointegrado.model.enums;

public enum TipoTransacaoEnum {
    RECEITA(1, "RECEITA"),
    DESPESA(2, "DESPESA");

    private final int codigo;
    private final String descricao;

    TipoTransacaoEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

}