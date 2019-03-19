package com.computacionmovil.computacionmovil.Modelos;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Usuario {
    private String nombre, apellido, telefono, email, direccion;
    private int id;
    private boolean isMale, isActive;

    public static void borrarUsuario(Context context , int selectedID) {
        try {
            JSONObject jsonObject = new JSONObject(DataModel.getData(context));
            JSONArray jsonObjectNew = new JSONArray();


            JSONArray dataArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < dataArray.length(); i++) {

                JSONObject dataobj = dataArray.getJSONObject(i);
                if(dataobj.getInt("id")!=selectedID){
                    jsonObjectNew.put(dataobj);
                }


            }
            JSONObject newAgendaData = new JSONObject();
            newAgendaData.put("data",jsonObjectNew);
            DataModel.saveData(context,newAgendaData.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono=telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email= email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public void saveNewUser(Context context) throws JSONException {
        this.setActive(true);
        JSONObject nuevoUsuarioJSON = userToJson(context);
        JSONArray agenda = getAgenda(context);
        agenda.put(nuevoUsuarioJSON);
        JSONObject newAgendaData = new JSONObject();
        newAgendaData.put("data",agenda);
        DataModel.saveData(context,newAgendaData.toString());
    }

    private JSONArray getAgenda(Context context) throws JSONException {
        String currentAgenda = DataModel.getData(context);
        JSONObject jobj=new JSONObject(currentAgenda);
        String c = jobj.getString("data");
        JSONArray jArray = new JSONArray(c);
    return jArray;
    }
    private JSONObject userToJson(Context context ){
        JSONObject jsonObject = new JSONObject();
        Usuario usr = this;
        try {
            jsonObject.put("nombre", usr.getNombre());
            jsonObject.put("Apellido", usr.getApellido());
            jsonObject.put("telefono", usr.getTelefono());
            jsonObject.put("email", usr.getEmail());
            jsonObject.put("direccion", usr.getDireccion());
            jsonObject.put("id",getNewID(context));
            jsonObject.put("isMale", usr.isMale());
            jsonObject.put("isActive", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    private int getNewID(Context context) throws JSONException {
        int val = 0;
        String currentAgenda = DataModel.getData(context);
        JSONObject jobj=new JSONObject(currentAgenda);
        String c = jobj.getString("data");
        JSONArray jArray = new JSONArray(c);

        for (int i = 0; i < jArray.length(); i++) {
            JSONObject object = jArray.getJSONObject(i);
            int temp = object.getInt("id");
            if (val<temp){
                val = temp;
            }
        }


        return val + 1;
    }

    public static Usuario getUsuarioByID(Context context, int id){
    Usuario usr = new Usuario();
        try {
            JSONObject jsonObject = new JSONObject(DataModel.getData(context));


            JSONArray dataArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < dataArray.length(); i++) {

                JSONObject dataobj = dataArray.getJSONObject(i);
                if(dataobj.getInt("id")==id){
                    usr.setNombre(dataobj.getString("nombre"));
                    usr.setApellido(dataobj.getString("Apellido"));
                    usr.setTelefono(dataobj.getString("telefono"));
                    usr.setEmail(dataobj.getString("email"));
                    usr.setDireccion(dataobj.getString("direccion"));
                    usr.setMale(dataobj.getBoolean("isMale"));
                    usr.setId(dataobj.getInt("id"));
                    usr.setActive(dataobj.getBoolean("isActive"));
                    break;
                }


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    return usr;
    }

}
