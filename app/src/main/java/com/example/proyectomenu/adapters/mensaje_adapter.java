package com.example.proyectomenu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomenu.R;
import com.example.proyectomenu.models.mensaje;

import java.util.ArrayList;

public class mensaje_adapter extends RecyclerView.Adapter<mensaje_adapter.ViewHolder> {

    private int resource;
    private ArrayList<mensaje> mensajesList;

    public mensaje_adapter(ArrayList<mensaje> mensajesList,int resource) {
        this.resource = resource;
        this.mensajesList = mensajesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int index) {

        mensaje mmensaje= mensajesList.get(index);
        viewHolder.mTextViewMensaje.setText(mmensaje.getTexto());

    }

    @Override
    public int getItemCount() {
        return mensajesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewMensaje;
        public View view;

        public ViewHolder (View view){
            super(view);
            this.view = view;
            this.mTextViewMensaje=(TextView)view.findViewById(R.id.txtViewMensaje);
        }

    }

}
