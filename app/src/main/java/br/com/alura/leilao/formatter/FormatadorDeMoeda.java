package br.com.alura.leilao.formatter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatadorDeMoeda {

    public String formata(double valor) {
        NumberFormat formatador = DecimalFormat
                .getCurrencyInstance(new Locale("pt", "br"));
        return formatador.format(valor);
    }
}
