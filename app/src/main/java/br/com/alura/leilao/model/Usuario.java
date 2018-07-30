package br.com.alura.leilao.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private final long id;
    private final String nome;

    public Usuario(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Usuario(String nome) {
        this.id = 0L;
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (id != usuario.id) return false;
        return nome != null ? nome.equals(usuario.nome) : usuario.nome == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "(" + id + ") " + nome;
    }

    public String getNome() {
        return nome;
    }
}
