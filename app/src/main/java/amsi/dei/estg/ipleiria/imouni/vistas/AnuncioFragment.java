package amsi.dei.estg.ipleiria.imouni.vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.imouni.R;
import amsi.dei.estg.ipleiria.imouni.modelo.Anuncio;
import amsi.dei.estg.ipleiria.imouni.modelo.SingletonGestorImoUni;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnuncioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnuncioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AnuncioFragment() {
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
    public static AnuncioFragment newInstance(String param1, String param2) {
        AnuncioFragment fragment = new AnuncioFragment();
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

    private TextView tvTitulo, tvPreco, tvDataPublicado, tvDescricao;



    private void carregarAnuncios(){
        ArrayList<Anuncio> anuncios = SingletonGestorImoUni.getInstance(getContext()).getAnunciosDB();

        if(anuncios.size() > 0){
            Anuncio l = anuncios.get(0);
            tvTitulo.setText(l.getTitulo());
            tvPreco.setText(String.valueOf(l.getPreco()));
            tvDataPublicado.setText(l.getData_criacao());
            tvDescricao.setText(l.getDescricao());

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_anuncio, container, false);

        tvTitulo = view.findViewById(R.id.txtTitulo);
        tvPreco = view.findViewById(R.id.txtPreco);
        tvDataPublicado = view.findViewById(R.id.txtDataPublicacao);
        tvDescricao = view.findViewById(R.id.txxDescricao);

        carregarAnuncios();
        return view;
    }
}