package br.com.alura.leilao.ui.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;

public class AvisoDialogManager {

    private static final String MENSAGEM_PADRAO_BOTAO_POSITIVO = "Ok";
    private static final String MENSAGEM_AVISO_JA_DEU_CINCO_LANCES = "Usuário não pode dar mais de cinco lances no mesmo leilão";
    private static final String MENSAGEM_AVISO_LANCE_SEGUIDO_MESMO_USUARIO = "O mesmo usuário do último lance não pode propror novos lances";
    private static final String MENSAGEM_AVISO_LANCE_MENOR_QUE_ULTIMO_LANCE = "Lance precisa ser maior que o último lance";
    private static final String MENSAGEM_AVISO_FALHA_NO_ENVIO_DO_LANCE = "Não foi possível enviar Lance";
    private static final String MENSAGEM_AVISO_VALOR_INVALIDO = "Valor inválido";

    public static void mostraToastFalhaNoEnvio(Context context) {
        mostraDialog(context, MENSAGEM_AVISO_FALHA_NO_ENVIO_DO_LANCE);
    }

    public void mostraAvisoUsuarioJaDeuCincoLances(Context context) {
        mostraDialog(context, MENSAGEM_AVISO_JA_DEU_CINCO_LANCES);
    }

    public static void mostraAvisoLanceSeguidoDoMesmoUsuario(Context context) {
        mostraDialog(context, MENSAGEM_AVISO_LANCE_SEGUIDO_MESMO_USUARIO);
    }

    public void mostraAvisoLanceMenorQueUltimoLance(Context context) {
        mostraDialog(context, MENSAGEM_AVISO_LANCE_MENOR_QUE_ULTIMO_LANCE);
    }

    public static void mostraAvisoValorInvalido(Context context) {
        mostraDialog(context, MENSAGEM_AVISO_VALOR_INVALIDO);
    }

    private static void mostraDialog(Context context, String mensagem) {
        new AlertDialog.Builder(context)
                .setMessage(mensagem)
                .setPositiveButton(MENSAGEM_PADRAO_BOTAO_POSITIVO, null)
                .show();
    }

}
