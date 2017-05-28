package brothersideas.mx.scrumteam;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import brothersideas.mx.scrumteam.models.Project;


public class ScrumFragment extends Fragment {
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    public ScrumFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_scrum, container, false);
        // Inicializar Animes
        List items = new ArrayList();

        items.add(new Project("ECO", "VIGENTE", "5"));
        items.add(new Project("ECO MOVIL", "VIGENTE", "1"));
        items.add(new Project("SDG", "VIGENTE", "20"));

// Obtener el Recycler
        recycler = (RecyclerView) rootView.findViewById(R.id.recicladorScrum);
        recycler.setHasFixedSize(true);

// Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);

// Crear un nuevo adaptador
        adapter = new ScrumAdapter(items);
        recycler.setAdapter(adapter);
        implementRecyclerViewClickListeners();

        return rootView;
    }

    private void implementRecyclerViewClickListeners() {
        recycler.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recycler, new RecyclerClick_Listener() {
            @Override
            public void onClick(View view, int position) {
               Intent i = new Intent(getContext(), SprintsActivity.class);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

}
