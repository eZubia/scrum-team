package brothersideas.mx.scrumteam;

import android.content.Context;
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
import brothersideas.mx.scrumteam.models.Sprint;
import brothersideas.mx.scrumteam.utils.ReadProyectos;
import brothersideas.mx.scrumteam.utils.ReadSprint;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SprintFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SprintFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SprintFragment extends Fragment {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    public SprintFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sprints, container, false);
        // Inicializar Animes
        List items = getSprintsProyecto(MainActivity.idProyecto);

// Obtener el Recycler
        recycler = (RecyclerView) rootView.findViewById(R.id.recicladorSprint);
        recycler.setHasFixedSize(true);

// Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);

// Crear un nuevo adaptador
        adapter = new SprintAdapter(items);
        recycler.setAdapter(adapter);
        implementRecyclerViewClickListeners();

        return rootView;
    }

    private void implementRecyclerViewClickListeners() {
        recycler.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recycler, new RecyclerClick_Listener() {
            @Override
            public void onClick(View view, int position) {
//                Intent i = new Intent(getContext(), SprintsActivity.class);
//                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public ArrayList<Sprint> getSprintsProyecto(String idProyecto){
        ConnectServerSprints server = new ConnectServerSprints();
        server.execute(idProyecto);
        ArrayList<Sprint> sprints = new ArrayList<>();

        try {
            String json = server.get();
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Sprint>>(){}.getType();
            sprints = gson.fromJson(json, listType);
        } catch (Exception e){
            Log.e("Error", "No pude leer el JSON.");
        }

        return sprints;
    }

    public class ConnectServerSprints extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... parameters) {
            String json = ReadSprint.findSprintByProyecto(parameters[0]);
            System.out.println("json = " + json);
            return json;

        }
    }
}
