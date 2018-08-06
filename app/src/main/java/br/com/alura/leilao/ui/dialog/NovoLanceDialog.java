package br.com.alura.leilao.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.List;

import br.com.alura.leilao.R;
import br.com.alura.leilao.database.dao.UsuarioDAO;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.activity.ListaUsuarioActivity;

public class NovoLanceDialog {

    private static final String TITULO = "Novo lance";
    private static final String DESCRICAO_BOTAO_POSITIVO = "Propor";
    private static final String DESCRICAO_BOTAO_NEGATIVO = "Cancelar";
    private static final String USUARIOS_NAO_ENCONTRADOS = "Usuários não encontrados";
    private static final String MENSAGEM_NAO_EXISTE_USUARIOS_CADASTRADOS = "Não existe usuários cadastrados! Cadastre um usuário para propor o lance.";
    private static final String CADASTRAR_USUARIO = "Cadastrar usuário";

    private final Context context;
    private final LanceCriadoListener listener;
    private final UsuarioDAO dao;

    public NovoLanceDialog(Context context,
                           LanceCriadoListener listener,
                           UsuarioDAO dao) {
        this.context = context;
        this.listener = listener;
        this.dao = dao;
    }

    public void mostra() {
        List<Usuario> usuarios = dao.todos();
        if (naoTemUsuariosCadastrados(usuarios)) return;
        configuraView(usuarios);
    }

    private boolean naoTemUsuariosCadastrados(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) {
            mostraDialogUsuarioNaoCadastrado();
            return true;
        }
        return false;
    }

    private void mostraDialogUsuarioNaoCadastrado() {
        new AlertDialog.Builder(context)
                .setTitle(USUARIOS_NAO_ENCONTRADOS)
                .setMessage(MENSAGEM_NAO_EXISTE_USUARIOS_CADASTRADOS)
                .setPositiveButton(CADASTRAR_USUARIO, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent vaiParaListaDeUsuarios = new Intent(context, ListaUsuarioActivity.class);
                        context.startActivity(vaiParaListaDeUsuarios);
                    }
                }).show();
    }

    private void configuraView(List<Usuario> usuarios) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.form_lance, null, false);

        Spinner campoUsuarios = viewCriada.findViewById(R.id.form_lance_usuario);
        TextInputLayout textInputValor = viewCriada.findViewById(R.id.form_lance_valor);

        configuraSpinnerUsuarios(usuarios, campoUsuarios);
        EditText campoValor = textInputValor.getEditText();
        mostraDialogPropoeLance(viewCriada, campoUsuarios, campoValor);
    }

    private void mostraDialogPropoeLance(View viewCriada, Spinner campoUsuarios, EditText campoValor) {
        new AlertDialog.Builder(context)
                .setTitle(TITULO)
                .setView(viewCriada)
                .setPositiveButton(DESCRICAO_BOTAO_POSITIVO,
                        criaNovoLanceListener(campoValor, campoUsuarios))
                .setNegativeButton(DESCRICAO_BOTAO_NEGATIVO, null)
                .show();
    }

    @NonNull
    private DialogInterface.OnClickListener criaNovoLanceListener(
            final EditText campoValor, final Spinner campoUsuarios) {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String valorEmTexto = campoValor.getText().toString();
                Usuario usuario = (Usuario) campoUsuarios.getSelectedItem();
                try {
                    double valor = Double.parseDouble(valorEmTexto);
                    Lance novoLance = new Lance(usuario, valor);
                    listener.lanceCriado(novoLance);
                } catch (NumberFormatException e) {
                    new AvisoDialogManager(context).mostraAvisoValorInvalido();
                }
            }
        };
    }

    private void configuraSpinnerUsuarios(List<Usuario> usuarios,
                                          Spinner usuariosDisponiveis) {
        SpinnerAdapter adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_dropdown_item,
                usuarios);
        usuariosDisponiveis.setAdapter(adapter);
    }

    public interface LanceCriadoListener {
        void lanceCriado(Lance lance);
    }


}