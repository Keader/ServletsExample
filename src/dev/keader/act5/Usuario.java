package dev.keader.act5;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String usuario;
    private String senha;
    private int tempo;
    private int logado;

    public Usuario() { }

    public Usuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
        tempo = 0;
        logado = 0;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getLogado() {
        return logado;
    }

    public void setLogado(int logado) {
        this.logado = logado;
    }
}
