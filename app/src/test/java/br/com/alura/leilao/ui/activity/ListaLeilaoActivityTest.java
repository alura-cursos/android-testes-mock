package br.com.alura.leilao.ui.activity;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ListaLeilaoActivityTest {

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoBuscarLeiloesDaApi() throws InterruptedException {
        ListaLeilaoActivity activity = new ListaLeilaoActivity();

        activity.configuraAdapter();
        activity.buscaLeiloes();
        Thread.sleep(2000);
        int quantidadeLeiloesDevolvida = activity.getAdapter().getItemCount();


        assertThat(quantidadeLeiloesDevolvida, is(3));
    }

}