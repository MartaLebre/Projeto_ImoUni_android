package amsi.dei.estg.ipleiria.imouni.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import amsi.dei.estg.ipleiria.imouni.R;
import amsi.dei.estg.ipleiria.imouni.modelo.Anuncio;
import amsi.dei.estg.ipleiria.imouni.modelo.SingletonGestorImoUni;

public class AnuncioActivity extends AppCompatActivity {
    public static final String ID = "ID";
    private Anuncio anuncio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncio);

        int id = getIntent().getIntExtra(ID, -1);
        anuncio = SingletonGestorImoUni.getInstance(this).getAnuncio(id);

        /*
        tv_NomePT = findViewById(R.id.tv_NomePT);
        tv_tipoMonumento = findViewById(R.id.tv_tipoMonumento);

        tvEC = findViewById(R.id.tvEC);
        tv_estiloConstrucao = findViewById(R.id.tv_estiloConstrucao);

        tvAC = findViewById(R.id.tvAC);
        tv_anoConstrucao = findViewById(R.id.tv_anoConstrucao);

        tv_localidade = findViewById(R.id.tv_localidade);
        tv_rating = findViewById(R.id.tv_rating);
        tv_descricao = findViewById(R.id.tv_descricao);
        ivImgPT = findViewById(R.id.ivImgPT);
         */

        saveAnuncio(anuncio);
    }

    private void saveAnuncio(Anuncio anuncio){
        /*
        tv_NomePT.setText(pontoTuristico.getNome());
        tv_tipoMonumento.setText(pontoTuristico.getTipoMonumento());

        if(pontoTuristico.getEstiloConstrucao() != null){
            tv_estiloConstrucao.setText(pontoTuristico.getEstiloConstrucao());
        }else{
            tvEC.setVisibility(View.GONE);
            tv_estiloConstrucao.setVisibility(View.GONE);
        }

        if(pontoTuristico.getAnoConstrucao() != null){
            tv_anoConstrucao.setText(pontoTuristico.getAnoConstrucao()+"");
        }else{
            tvAC.setVisibility(View.GONE);
            tv_anoConstrucao.setVisibility(View.GONE);
        }
        tv_localidade.setText(pontoTuristico.getLocalidade());
        //tv_rating.setText(pontoTuristico.getRanking());
        tv_descricao.setText(pontoTuristico.getDescricao());
        Glide.with(this)
                .load(pontoTuristico.getFoto())
                .placeholder(R.drawable.castelo_de_leiria)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivImgPT);
        */
    }
}