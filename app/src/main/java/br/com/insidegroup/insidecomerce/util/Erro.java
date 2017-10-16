package br.com.insidegroup.insidecomerce.util;

public class Erro extends Throwable{

    private int id;
    private String msg;


    public Erro(String msg){
        this.setMsg(msg);
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
