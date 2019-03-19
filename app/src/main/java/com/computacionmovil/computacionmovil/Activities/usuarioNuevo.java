package com.tacticalfitnessmexicali.computacionmovil.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tacticalfitnessmexicali.computacionmovil.Activities.MainActivity;
import com.tacticalfitnessmexicali.computacionmovil.Modelos.Usuario;
import com.tacticalfitnessmexicali.computacionmovil.R;

import org.json.JSONException;

public class usuarioNuevo extends AppCompatActivity {
    private TextView tvNombre, tvApellido, tvTelefono, tvemail, tvDireccion;
    private RadioButton rbMale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_nuevo);

      //  getSupportActionBar().setDisplayShowHomeEnabled(true);
      //  getSupportActionBar().setIcon(R.drawable.ic_launcher_foreground);


        tvNombre = (TextView)findViewById(R.id.etNombre);
        tvApellido = (TextView)findViewById(R.id.etApellido);
        tvTelefono = (TextView)findViewById(R.id.etTelefono);
        tvemail = (TextView)findViewById(R.id.etEmail);
        tvDireccion = (TextView)findViewById(R.id.etDireccion);
        rbMale = (RadioButton)findViewById(R.id.rbHombre);
    }
    public void saveBTNClick (View view){
        Usuario newUser = new Usuario();
        newUser.setNombre(tvNombre.getText().toString());
        newUser.setApellido(tvApellido.getText().toString());
        newUser.setTelefono(tvTelefono.getText().toString());
        newUser.setEmail(tvemail.getText().toString());
        newUser.setDireccion(tvDireccion.getText().toString());
        newUser.setMale(rbMale.isChecked());
        try {
            newUser.saveNewUser(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            Toast.makeText(this, R.string.usuarioGuardado, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}
