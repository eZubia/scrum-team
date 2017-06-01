package brothersideas.mx.scrumteam;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import brothersideas.mx.scrumteam.models.Project;
import brothersideas.mx.scrumteam.models.Sprint;

public class SprintAdapter extends RecyclerView.Adapter<SprintAdapter.ScrumViewHolder> {
    private List<Sprint> items;


    public static class ScrumViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView status;
        public TextView sprints;

        public ScrumViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre);
            status = (TextView) v.findViewById(R.id.status);
            sprints = (TextView) v.findViewById(R.id.sprints);
        }
    }

    public SprintAdapter(List<Sprint> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ScrumViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.scrum_card, viewGroup, false);
        return new ScrumViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ScrumViewHolder viewHolder, int i) {
        viewHolder.nombre.setText(items.get(i).getNombreSprint());
        viewHolder.status.setText(items.get(i).getFinalizo() ? "Finalizado" : "Abierto");
        viewHolder.sprints.setText("12");
    }
}