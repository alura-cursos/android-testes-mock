package br.com.alura.leilao.ui.activity;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.api.retrofit.client.RespostaListener;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ListaLeilaoActivityTest {

    @Mock
    private Context context;
    @Spy
    private ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(context);
    @Mock
    private LeilaoWebClient client;

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoBuscarLeiloesDaApi() {
        ListaLeilaoActivity activity = new ListaLeilaoActivity();
        Mockito.doNothing().when(adapter).atualizaLista();
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
                argument.sucesso(new ArrayList<>(Arrays.asList(
                        new Leilao("Computador"),
                        new Leilao("Carro")
                )));
                return null;
            }
        }).when(client).todos(ArgumentMatchers.any(RespostaListener.class));

        activity.buscaLeiloes(adapter, client);
        int quantidadeLeiloesDevolvida = adapter.getItemCount();

        assertThat(quantidadeLeiloesDevolvida, is(2));
    }

}