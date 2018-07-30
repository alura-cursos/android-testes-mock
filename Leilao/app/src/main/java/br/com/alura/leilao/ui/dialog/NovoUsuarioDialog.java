package br.com.alura.leilao.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import br.com.alura.leilao.R;
import br.com.alura.leilao.model.Usuario;

public class NovoUsuarioDialog {

    private static final String TITULO = "Novo usuário";
    private static final String DESCRICAO_BOTAO_POSITIVO = "Adicionar";
    private final Context context;
    private final UsuarioCriadoListener listener;

    public NovoUsuarioDialog(Context context,
                             UsuarioCriadoListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void mostra() {
        final View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.form_usuario, null, false);
        TextInputLayout textInputNome = viewCriada.findViewById(R.id.form_usuario_nome);

        EditText campoNome = textInputNome.getEditText();

        new AlertDialog.Builder(context)
                .setTitle(TITULO)
                .setView(viewCriada)
                .setPositiveButton(DESCRICAO_BOTAO_POSITIVO,
                        criaNovoUsuarioListener(campoNome))
                .show();
    }

    @NonNull
    private DialogInterface.OnClickListener criaNovoUsuarioListener(final EditText campoNome) {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String nome = campoNome.getText().toString();
                Usuario novoUsuario = new Usuario(nome);
                listener.criado(novoUsuario);
            }
        };
    }

    public interface UsuarioCriadoListener {
        void criado(Usuario usuario);
    }

}
