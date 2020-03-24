package com.example.proyectomenu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomenu.adapters.mensaje_adapter;
import com.example.proyectomenu.models.mensaje;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Foro extends AppCompatActivity {

    private EditText mEditTextMensaje;
    private Button mBotonCrear;
    private DatabaseReference mDatabase;
    private mensaje_adapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<mensaje> mMensajesList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listadatos);

        mEditTextMensaje = (EditText)findViewById(R.id.edtMensaje);
        mBotonCrear = (Button)findViewById(R.id.btnCrear);
        mRecyclerView = (RecyclerView)findViewById(R.id.RecyclerMensajes);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mBotonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Mensaje= mEditTextMensaje.getText().toString();
                mDatabase.child("Mensajes").push().child("texto").setValue(Mensaje);
            }
        });
        getMensajesFromFirebase();
    }

    private void getMensajesFromFirebase(){
        mDatabase.child("Mensajes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    mMensajesList.clear();
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        String texto= ds.child("texto").getValue().toString();
                        mMensajesList.add(new mensaje(texto));
                    }
                    mAdapter = new mensaje_adapter(mMensajesList, R.layout.mensaje_view);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
