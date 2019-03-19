package com.computacionmovil.computacionmovil.Modelos;

import android.content.SharedPreferences;
import android.content.Context;
import android.preference.PreferenceManager;

public class DataModel {
    private static final String AGENDA_NAME = "AgendaApp";
    private static final String AGENDA_DEFAULT_VALUES ="{'data':[{'id':0, 'nombre':'Mariza','Apellido':'Villalobos','isMale':false}," +
            "{'id':1, 'nombre':'Enrique','Apellido':'Fernandez','isMale':true}]}";
    private static final String AGENDA_DEFAULT_EMPTY= "{'data':[]}";
    public static void saveData(Context mContext,   String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AGENDA_NAME, value).apply();
    }

    public static String getData(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(AGENDA_NAME, AGENDA_DEFAULT_EMPTY);
    }

}
