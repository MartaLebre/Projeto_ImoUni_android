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


public class ListaAnuncioFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView lvListAnuncio;
    private SwipeRefreshLayout swipeRefreshLayout;


    public ListaAnuncioFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_anuncio, container, false);
        setHasOptionsMenu(true);


        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(this);


        lvListAnuncio.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), AnuncioActivity.class);

            }
        });
        return view;
    }

    @Override
    public void onRefresh() {
        //listaLivros = SingletonGestorLivros.getInstance(getContext()).getLivrosBD();
        //lvListaLivros.setAdapter(new ListaLivroAdaptador(getContext(), listaLivros));
        System.out.println("ON refresh");
        swipeRefreshLayout.setRefreshing(false);
    }
}
