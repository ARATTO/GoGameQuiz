package los_eternos.gogamificationquiz.Controladores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import los_eternos.gogamificationquiz.Modelo.Medalla;
import los_eternos.gogamificationquiz.R;

/**
 * Created by rodrigo on 02-26-17.
 */

public class MedallaAdapter extends RecyclerView.Adapter<MedallaAdapter.MedallaViewHolder> {
    private List<Medalla> items;

    public static class MedallaViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView visitas;

        public MedallaViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            nombre = (TextView) v.findViewById(R.id.nombre);
            visitas = (TextView) v.findViewById(R.id.visitas);
        }
    }


    public MedallaAdapter(List<Medalla> items) {
        this.items = items;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public MedallaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.medallas_card, viewGroup, false);
        return new MedallaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MedallaViewHolder holder, int position) {
        holder.imagen.setImageResource(items.get(position).getImagen());
        holder.nombre.setText(items.get(position).getDescripcionMedalla());
        holder.visitas.setText("Puntaje:"+String.valueOf(items.get(position).getEsCuantitativa()));
    }


}
