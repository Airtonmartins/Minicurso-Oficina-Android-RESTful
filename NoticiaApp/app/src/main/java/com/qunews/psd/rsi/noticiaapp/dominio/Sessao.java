package com.qunews.psd.rsi.noticiaapp.dominio;

/**
 * Created by airton on 06/08/17.
 */

public class Sessao {
    private static Sessao instancia = new Sessao();
    private Sessao(){}
    public static Sessao getInstancia() {
        return instancia;
    }

    private Usuario usuarioLogado;

    public Usuario getUsuarioLogado(){

        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

}
