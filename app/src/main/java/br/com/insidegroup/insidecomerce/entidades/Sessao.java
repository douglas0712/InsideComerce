package br.com.insidegroup.insidecomerce.entidades;

/**
 * Created by Lucas on 09/10/2017.
 */
public class Sessao {
    // Variável estática que conterá a instancia da classe
    private static Sessao instance;
    private static String nomeUsuario = null;
    private static String codUsuario = null;
    private static Ticket ticket = null;

    // Construtor privado (suprime o construtor público padrão).
    private Sessao() {}

    // Método público estático de acesso único ao objeto!
    public static Sessao getInstance() {
        if (instance == null)
            instance = new Sessao();
        return instance;
    }

    public static String getNomeUsuario() {
        return nomeUsuario;
    }

    public static void setNomeUsuario(String nomeUsuario) {
        Sessao.nomeUsuario = nomeUsuario;
    }

    public static String getCodUsuario() {
        return codUsuario;
    }

    public static Ticket getTicket() {
        return ticket;
    }

    public static void setTicket(Ticket ticket) {
        Sessao.ticket = ticket;
    }

    public static void setCodUsuario(String codUsuario) {
        Sessao.codUsuario = codUsuario;
    }
}