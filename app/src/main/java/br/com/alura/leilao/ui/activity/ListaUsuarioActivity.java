package br.com.alura.leilao.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.alura.leilao.R;
import br.com.alura.leilao.database.dao.UsuarioDAO;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.AtualizadorDeUsuario;
import br.com.alura.leilao.ui.dialog.NovoUsuarioDialog;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaUsuarioAdapter;

public class ListaUsuarioActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Usuários";
    private UsuarioDAO dao;
    private ListaUsuarioAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuario);
        getSupportActionBar().setTitle(TITULO_APPBAR);
        inicializaAtributos();
        configuraRecyclerView();
        configuraFab();
    }

    private void inicializaAtributos() {
        dao = new UsuarioDAO(this);
        adapter = new ListaUsuarioAdapter(this);
    }

    private void configuraFab() {
        FloatingActionButton fabAdicionaUsuario = findViewById(R.id.lista_usuario_fab_adiciona);
        fabAdicionaUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                mostraDialogAdicionaNovoUsuario();
            }
        });
    }

    private void configuraRecyclerView() {
        recyclerView = findViewById(R.id.lista_usuario_recyclerview);
        recyclerView.setAdapter(adapter);
        adapter.adiciona(dao.todos());
    }

    private void mostraDialogAdicionaNovoUsuario() {
        NovoUsuarioDialog dialog = new NovoUsuarioDialog(
                this,
                new NovoUsuarioDialog.UsuarioCriadoListener() {
                    @Override
                    public void criado(Usuario usuario) {
                        new AtualizadorDeUsuario(
                                dao,
                                adapter,
                                recyclerView)
                                .salva(usuario);
                    }
                });
        dialog.mostra();
    }

}
