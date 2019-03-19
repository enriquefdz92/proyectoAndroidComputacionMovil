package com.tacticalfitnessmexicali.computacionmovil.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tacticalfitnessmexicali.computacionmovil.Modelos.Usuario;
import com.tacticalfitnessmexicali.computacionmovil.R;

public class activity_perfil_usuario extends AppCompatActivity {
private Usuario selectedUser ;
private TextView nombre, apellido, telefono, email, direccion;
private ImageView imagen;
private int selectedID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        Intent i = getIntent();
        selectedID = i.getIntExtra("id", -1);
        selectedUser = Usuario.getUsuarioByID(this,selectedID);

        nombre =  (TextView)findViewById(R.id.tvsNombre);
        apellido =  (TextView)findViewById(R.id.tvsApellido);
        telefono =  (TextView)findViewById(R.id.tvsTelefono);
        email =  (TextView)findViewById(R.id.tvsEmail);
        direccion =  (TextView)findViewById(R.id.tvsDireccion);
        imagen=(ImageView) findViewById(R.id.iv);

        nombre.setText(selectedUser.getNombre());
        apellido.setText(selectedUser.getApellido());
        telefono.setText(selectedUser.getTelefono());
        email.setText(selectedUser.getEmail());
        direccion.setText(selectedUser.getDireccion());
        if(selectedUser.isMale()){
            imagen.setImageResource(R.mipmap.ic_male);
        }else
        {
            imagen.setImageResource(R.mipmap.ic_female);
        }
    }
    public void borrarUsuario(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.alertBorrarUsuarioMensaje)
                .setTitle(R.string.alertBorrarUsuarioTitulo)
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(activity_perfil_usuario.this, R.string.usuarioborrado, Toast.LENGTH_SHORT).show();
                Usuario.borrarUsuario(activity_perfil_usuario.this,selectedID);
                Intent i = new Intent(activity_perfil_usuario.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
