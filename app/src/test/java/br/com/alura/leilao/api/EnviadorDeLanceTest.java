package br.com.alura.leilao.api;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.dialog.AvisoDialogManager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EnviadorDeLanceTest {

    @Mock
    private LeilaoWebClient client;
    @Mock
    private EnviadorDeLance.LanceProcessadoListener listener;
    @Mock
    private AvisoDialogManager manager;
    @Mock
    private Leilao leilao;

    @Test
    public void deve_MostrarMensagemDeFalha_QuandoLanceForMenorQueUltimoLance(){
        EnviadorDeLance enviador = new EnviadorDeLance(
                client,
                listener,
                manager);
        doThrow(LanceMenorQueUltimoLanceException.class)
                .when(leilao).propoe(ArgumentMatchers.any(Lance.class));

        enviador.envia(leilao, new Lance(new Usuario("Fran"), 100));

        verify(manager).mostraAvisoLanceMenorQueUltimoLance();
    }

    @Test
    public void deve_MostrarMensagemDeFalha_QuandoUsuarioComCincoDerNovoLance(){
        EnviadorDeLance enviador = new EnviadorDeLance(
                client,
                listener,
                manager);
        doThrow(UsuarioJaDeuCincoLancesException.class)
                .when(leilao).propoe(ArgumentMatchers.any(Lance.class));

        enviador.envia(leilao, new Lance(new Usuario("Alex"), 200));

        verify(manager).mostraAvisoUsuarioJaDeuCincoLances();
    }
}