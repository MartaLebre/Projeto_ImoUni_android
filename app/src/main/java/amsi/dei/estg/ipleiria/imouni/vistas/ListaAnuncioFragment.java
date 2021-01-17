package amsi.dei.estg.ipleiria.imouni.vistas;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.imouni.R;
import amsi.dei.estg.ipleiria.imouni.adaptadores.ListaAnuncioAdaptador;
import amsi.dei.estg.ipleiria.imouni.listeners.AnunciosListener;
import amsi.dei.estg.ipleiria.imouni.modelo.Anuncio;
import amsi.dei.estg.ipleiria.imouni.modelo.SingletonGestorImoUni;


public class ListaAnuncioFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AnunciosListener {

    private ListView lvListAnuncio;
    //private ArrayList<Anuncio> listaAnuncios;
    private SwipeRefreshLayout swipeRefreshLayout;

    public ListaAnuncioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_anuncio, container, false);
        setHasOptionsMenu(true);

        lvListAnuncio = view.findViewById(R.id.lvListaAnuncios);

        lvListAnuncio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), AnuncioActivity.class);
                intent.putExtra("ID", (int) id);
                startActivity(intent);

            }
        });

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        SingletonGestorImoUni.getInstance(getContext()).getAllAnunciosAPI(getContext());
        SingletonGestorImoUni.getInstance(getContext()).setAnunciosListener(this);


        return view;
    }

    /*
    @Override
    public void onResume(){
        super.onResume();
        if(searchView != null)
            searchView.onActionViewCollapsed();
        SingletonGestorImoUni.getInstance(getContext()).setAnunciosListener(this);

        SingletonGestorImoUni.getInstance(getContext()).setAnuncios(this);
    }
    */

    @Override
    public void onResume() {
        super.onResume();
        SingletonGestorImoUni.getInstance(getContext()).setAnunciosListener(this);
    }

    @Override
    public void onRefresh() {
        SingletonGestorImoUni.getInstance(getContext()).getAllAnunciosAPI(getContext());
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefreshListaAnuncios(ArrayList<Anuncio> listaAnuncios) {
        if(listaAnuncios != null){
            lvListAnuncio.setAdapter(new ListaAnuncioAdaptador(getContext(),listaAnuncios));
        }
    }

    @Override
    public void onRefreshDetalhes() {
        //empty
    }
}
