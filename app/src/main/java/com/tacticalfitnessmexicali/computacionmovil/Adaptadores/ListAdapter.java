package com.tacticalfitnessmexicali.computacionmovil.Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacticalfitnessmexicali.computacionmovil.Modelos.Usuario;
import com.tacticalfitnessmexicali.computacionmovil.R;

import java.util.ArrayList;
import java.util.Collection;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Usuario> usuarioMArrayList;

    public ListAdapter(Context ctx, ArrayList<Usuario> usuarioMArrayList){

        inflater = LayoutInflater.from(ctx);
        this.usuarioMArrayList = usuarioMArrayList;
        ArrayList<Usuario> ListFiltered = new ArrayList<>();
        for(Usuario usr : usuarioMArrayList) {
            //code here
            if(usr.isActive()){
                ListFiltered.add(usr);
            }
        }
        this.usuarioMArrayList = ListFiltered;
    }

    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ListAdapter.MyViewHolder holder, int position) {

        //Picasso.get().load(usuarioMArrayList.get(position).getImgURL()).into(holder.iv);
        if(usuarioMArrayList.get(position).isMale() == true){
            holder.iv.setImageResource(R.mipmap.ic_male);
        }else{
            holder.iv.setImageResource(R.mipmap.ic_female);
        }

        holder.name.setText(usuarioMArrayList.get(position).getNombre() + " " + usuarioMArrayList.get(position).getApellido());

    }

    @Override
    public int getItemCount() {
        return usuarioMArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView  name ;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);


            name = (TextView) itemView.findViewById(R.id.name);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }

    }
}