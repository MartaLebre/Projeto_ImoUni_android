package amsi.dei.estg.ipleiria.imouni.vistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.imouni.R;
import amsi.dei.estg.ipleiria.imouni.adaptadores.ListaAnuncioAdaptador;
import amsi.dei.estg.ipleiria.imouni.listeners.AnunciosListener;
import amsi.dei.estg.ipleiria.imouni.modelo.Anuncio;
import amsi.dei.estg.ipleiria.imouni.modelo.SingletonGestorImoUni;


public class ListaAnuncioFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AnunciosListener {

    private ListView lvListaAnuncios;
    private ArrayList<Anuncio> listaAnuncios;
    private static final int EDITAR = 2;
    private static final int ADICIONAR = 3;
    private SwipeRefreshLayout swipeRefreshLayout;


    public ListaAnuncioFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_anuncios, container, false);
        setHasOptionsMenu(true);

        lvListaAnuncios = view.findViewById(R.id.lvListaLivros);
        /*
        lvListaAnuncios.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetalhesAnuncioActivity.class);
                intent.putExtra("id" , (int)id);
                //startActivity(intent);
                startActivityForResult(intent,EDITAR);
            }
        });
*/
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        SingletonGestorImoUni.getInstance(getContext()).getAllLivrosAPI(getContext());
        SingletonGestorImoUni.getInstance(getContext()).setAnunciosListener(this);


        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case ADICIONAR:
                    listaAnuncios = SingletonGestorImoUni.getInstance(getContext()).getAnunciosDB();
                    lvListaAnuncios.setAdapter(new ListaAnuncioAdaptador(getContext(), listaAnuncios));
                    Toast.makeText(getContext(), "Anuncio adicionado com sucesso", Toast.LENGTH_LONG).show();
                    break;
                case EDITAR:
                    listaAnuncios = SingletonGestorImoUni.getInstance(getContext()).getAnunciosDB();
                    lvListaAnuncios.setAdapter(new ListaAnuncioAdaptador(getContext(), listaAnuncios));
                    Toast.makeText(getContext(), "Anuncio Editado com sucesso", Toast.LENGTH_LONG).show();


                    break;
            }
        }
        //atualizar a lista
        //apresentar um toast
        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public void onResume() {
        super.onResume();
        SingletonGestorImoUni.getInstance(getContext()).setAnunciosListener(this);
    }

    @Override
    public void onRefresh() {
        SingletonGestorImoUni.getInstance(getContext()).getAllLivrosAPI(getContext());
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefreshListaAnuncios(ArrayList<Anuncio> listaAnuncios) {
        if(listaAnuncios != null) {
            lvListaAnuncios.setAdapter(new ListaAnuncioAdaptador(getContext(), listaAnuncios));
        }
    }

    @Override
    public void onRefreshDetalhes() {
        //empty

    }
}