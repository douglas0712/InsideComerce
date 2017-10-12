package br.com.insidegroup.insidecomerce.util;


public enum EnumMensagemErro {

    MENSAGEM_ERRO_GENERICO(1, "Ocorreu algum erro inesperado!"),
    MENSAGEM_ERRO_ACESSO_INTERNET(2, "Não existe conexão com a internet!"),
    MENSAGEM_ERRO_PERMISSAO_INTERNET(3, "Permissão de acesso a internet não concedida"),
    MENSAGEM_ERRO_PREENCHA_TODOS_OS_CAMPOS(18, "Preencha todos os campo"),
    MENSAGEM_ERRO_OBTER_LISTA_TICKET(19, "Erro ao pegar obter os Tickets");


    private int id;
    private String msg;

    private EnumMensagemErro(int id, String msg) {
        this.setId(id);
        this.setMsg(msg);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}