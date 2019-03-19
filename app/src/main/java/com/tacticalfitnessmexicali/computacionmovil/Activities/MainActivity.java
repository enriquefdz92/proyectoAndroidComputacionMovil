package com.tacticalfitnessmexicali.computacionmovil.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.tacticalfitnessmexicali.computacionmovil.Adaptadores.ListAdapter;
import com.tacticalfitnessmexicali.computacionmovil.Adaptadores.RecyclerItemClickListener;
import com.tacticalfitnessmexicali.computacionmovil.Modelos.DataModel;
import com.tacticalfitnessmexicali.computacionmovil.Modelos.Usuario;
import com.tacticalfitnessmexicali.computacionmovil.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ArrayList<Usuario> usuarioArrayList;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, usuarioNuevo.class);
                startActivity(i);
            }
        });
        recyclerView = findViewById(R.id.recycler);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Usuario usr = usuarioArrayList.get(position);
                        Intent i = new Intent(MainActivity.this, activity_perfil_usuario.class);
                        i.putExtra("id",usr.getId());
                        startActivity(i);
                    }
                })
        );
       UpdateList();
    }

    @Override
    protected void onResume(){
        super.onResume();
        UpdateList();
    }

    private void UpdateList(){
    usuarioArrayList= getInfo();
      Collections.sort(usuarioArrayList, new Comparator<Usuario>() {
            public int compare(Usuario o1, Usuario o2) {
                String n1,n2;
                n1= o1.getNombre()+o1.getApellido();
                n2=o2.getNombre()+o2.getApellido();
                return n1.compareTo(n2);
            }
        });
    listAdapter = new ListAdapter(this,usuarioArrayList);
    recyclerView.setAdapter(listAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        TextView txt = (TextView)findViewById(R.id.lblnousers);
    if(recyclerView.getAdapter().getItemCount()==0){
        txt.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }else{
        txt.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}

    private String getJsonListaUsuarios() {
        return DataModel.getData(this);
    }



    public ArrayList<Usuario> getInfo() {
        ArrayList<Usuario> usuarioMArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(getJsonListaUsuarios());


                JSONArray dataArray = jsonObject.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {

                    Usuario usuario = new Usuario();
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    usuario.setNombre(dataobj.getString("nombre"));
                    usuario.setApellido(dataobj.getString("Apellido"));
                    usuario.setMale(dataobj.getBoolean("isMale"));
                    usuario.setId(dataobj.getInt("id"));
                    usuario.setActive(dataobj.getBoolean("isActive"));
                    usuarioMArrayList.add(usuario);

                }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return usuarioMArrayList;
    }

}
