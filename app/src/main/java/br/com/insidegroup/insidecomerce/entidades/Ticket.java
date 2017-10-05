package br.com.insidegroup.insidecomerce.entidades;

import java.util.Date;
/*
Os status dos tickets
    1 - em aberto
    2 - em atendimento
    3 - cliente cancelou vistoria
    4 - cliente recusou contrato
    5 - cliente fechou contrato
    6 - Vistoria reagendada

 */
public class Ticket {



    private String nome;
    private String descricao;
    private String dataCriacao;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
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


}

