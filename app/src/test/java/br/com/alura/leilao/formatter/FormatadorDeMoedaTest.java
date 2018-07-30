package br.com.alura.leilao.formatter;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FormatadorDeMoedaTest {

    @Test
    public void deve_formatarParaMoeda_QuandoRecebeValorDouble(){
        FormatadorDeMoeda formatador = new FormatadorDeMoeda();

        String moedaFormatada = formatador.formata(200.0);

        assertThat(moedaFormatada, is(equalTo("R$ 200,00")));
    }

}