package br.com.alura.leilao.ui.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.leilao.R;
import br.com.alura.leilao.model.Usuario;

public class ListaUsuarioAdapter extends RecyclerView.Adapter<ListaUsuarioAdapter.ViewHolder> {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final Context context;

    public ListaUsuarioAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_usuario, parent, false);
        return new ViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.vincula(usuarios.get(position));
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public void adiciona(List<Usuario> usuarios) {
        for (Usuario usuario :
                usuarios) {
            adiciona(usuario);
        }
    }

    public void adiciona(Usuario usuario) {
        usuarios.add(usuario);
        notifyItemInserted(getItemCount() - 1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView idComUsuario;

        ViewHolder(View itemView) {
            super(itemView);
            idComUsuario = itemView.findViewById(R.id.item_usuario_id_com_nome);
        }

        void vincula(Usuario usuario) {
            idComUsuario.setText(usuario.toString());
        }

    }
}
