package brothersideas.mx.scrumteam;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import brothersideas.mx.scrumteam.models.Project;
import brothersideas.mx.scrumteam.utils.ReadProyectos;

public class DeveloperFragment extends Fragment {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private List<Project> items_proyects;

    public DeveloperFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_developer, container, false);
        // Inicializar Animes
        items_proyects =getProyectosDeveloper(MainActivity.idUsuario);

// Obtener el Recycler
        recycler = (RecyclerView) rootView.findViewById(R.id.recicladorDeveloper);
        recycler.setHasFixedSize(true);

// Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);

// Crear un nuevo adaptador
        adapter = new ScrumAdapter(items_proyects);
        recycler.setAdapter(adapter);
        implementRecyclerViewClickListeners();

        return rootView;
    }

    private void implementRecyclerViewClickListeners() {
        recycler.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recycler, new RecyclerClick_Listener() {
            @Override
            public void onClick(View view, int position) {
                Project project = items_proyects.get(position);
                MainActivity.idProyecto = project.get_id();
                Intent i = new Intent(getContext(), SprintsActivity.class);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public ArrayList<Project> getProyectosDeveloper(String idUsuario){
        ConnectServerDeveloper server = new ConnectServerDeveloper();
        server.execute(idUsuario);
        ArrayList<Project> proyectos = new ArrayList<>();

        try {
            String json = server.get();
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Project>>(){}.getType();
            proyectos = gson.fromJson(json, listType);
        } catch (Exception e){
            Log.e("Error", "No pude leer el JSON.");
        }

        return proyectos;
    }

    public class ConnectServerDeveloper extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... parameters) {
            String json = ReadProyectos.findProyectosDeveloper(parameters[0]);
            System.out.println("json = " + json);
            return json;

        }
    }

}
