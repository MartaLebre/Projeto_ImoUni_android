package amsi.dei.estg.ipleiria.imouni.vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.imouni.R;
import amsi.dei.estg.ipleiria.imouni.modelo.Anuncio;
import amsi.dei.estg.ipleiria.imouni.modelo.SingletonGestorImoUni;


public class PesquisaFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PesquisaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DinamicoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PesquisaFragment newInstance(String param1, String param2) {
        PesquisaFragment fragment = new PesquisaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView tvTitulo, tvPreco, tvDespesas, tvPublicado;

    private void carregarAnuncios(){
        ArrayList<Anuncio> anuncios = SingletonGestorImoUni.getInstance(getContext()).getAnunciosDB();

        if(anuncios.size() > 0){
            Anuncio l = anuncios.get(0);
            tvTitulo.setText(l.getTitulo());
            tvPreco.setText(String.valueOf(l.getPreco()));
            tvDespesas.setText(String.valueOf(l.getDespesas_inc()));
            tvPublicado.setText(l.getData_criacao() + "");
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_pesquisa, container, false);

        tvTitulo = view.findViewById(R.id.tvListaAnuncio_titulo);
        tvPreco = view.findViewById(R.id.tvListaAnuncio_preco);
        tvDespesas = view.findViewById(R.id.tvListaAnuncio_despesas);
        tvPublicado  = view.findViewById(R.id.tvListaAnuncio_publicado);

        carregarAnuncios();

        return view;
    }
}