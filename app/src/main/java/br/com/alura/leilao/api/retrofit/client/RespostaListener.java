package br.com.alura.leilao.api.retrofit.client;

public interface RespostaListener<T> {
    void sucesso(T resposta);

    void falha(String mensagem);
}