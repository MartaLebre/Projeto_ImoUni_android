package amsi.dei.estg.ipleiria.imouni.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import amsi.dei.estg.ipleiria.imouni.R;
import amsi.dei.estg.ipleiria.imouni.listeners.AnunciosListener;
import amsi.dei.estg.ipleiria.imouni.modelo.Anuncio;
import amsi.dei.estg.ipleiria.imouni.modelo.SingletonGestorImoUni;
import amsi.dei.estg.ipleiria.imouni.modelo.Utilizador;


public class DetalhesAnuncioActivity extends AppCompatActivity implements AnunciosListener {

    public static final String ID = "ID";
    private Anuncio anuncio;
    private TextView Titulo, Preco, Descricao, dataAnuncio, dataDisponibilidade;
    private ImageButton btnLigar;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_anuncio);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int id = getIntent().getIntExtra(ID, -1);
        anuncio = SingletonGestorImoUni.getInstance(getApplicationContext()).getAnuncio(id);

        Titulo = findViewById(R.id.tvAnuncioTitulo);
        Preco = findViewById(R.id.preco);
        dataAnuncio = findViewById(R.id.dataPublicacao);
        dataDisponibilidade = findViewById(R.id.dataDisponibilidade);
        Descricao = findViewById(R.id.descricao);

        btnLigar = findViewById(R.id.imageButtonTelefonar);

        SingletonGestorImoUni.getInstance(getApplicationContext()).setAnunciosListener(this);


        SharedPreferences sharedPrefUser = getSharedPreferences(MenuMainActivity.PREF_INFO_USER, Context.MODE_PRIVATE);
        token = sharedPrefUser.getString(MenuMainActivity.TOKEN, null);

        SingletonGestorImoUni.getInstance(getApplicationContext()).setAnunciosListener(this);

        if(anuncio != null) {
            setTitle("Detalhes " + anuncio.getTitulo());
            carregarDetalhesLivro();
        }

        btnLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.1.68/yii2_Projeto_ImoUni/frontend/web/"));
                //startActivity(browserIntent);
                if(token != null) {
                    Intent chamada = new Intent(Intent.ACTION_DIAL);
                    chamada.setData(Uri.parse("tel:" + anuncio.getNumero_telefone()));
                    startActivity(chamada);
                }else{
                    Toast.makeText(getApplicationContext(), "Impossível realizar a chamada faça login para conseguir realizar a chamada!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void carregarDetalhesLivro() {
        Titulo.setText(anuncio.getTitulo());
        Preco.setText(String.valueOf(anuncio.getPreco()) + "€");
        Descricao.setText(anuncio.getDescricao());
        dataAnuncio.setText(anuncio.getData_criacao());
        dataDisponibilidade.setText(anuncio.getData_disponibilidade());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(anuncio!=null) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_detalhes_livro,menu);

            return super.onCreateOptionsMenu(menu);
        }
        return false;
    }

    /**
     * efetuar operações quando um item do menu é clicado
     * @param item
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return true;
    }


    @Override
    public void onRefreshListaAnuncios(ArrayList<Anuncio> listaAnuncios) {
        //empty
    }

    @Override
    public void onRefreshDetalhes() {
        setResult(RESULT_OK);
        finish();
    }
}