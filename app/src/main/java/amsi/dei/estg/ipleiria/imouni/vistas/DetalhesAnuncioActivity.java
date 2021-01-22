package amsi.dei.estg.ipleiria.imouni.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;


import java.util.ArrayList;

import amsi.dei.estg.ipleiria.imouni.R;
import amsi.dei.estg.ipleiria.imouni.listeners.AnunciosListener;
import amsi.dei.estg.ipleiria.imouni.modelo.Anuncio;
import amsi.dei.estg.ipleiria.imouni.modelo.SingletonGestorImoUni;


public class DetalhesAnuncioActivity extends AppCompatActivity implements AnunciosListener {

    public static final String ID = "ID";
    private Anuncio anuncio;
    private EditText etTitulo, etPreco, etDescricao;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_anuncio);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int id = getIntent().getIntExtra(ID, -1);
        anuncio = SingletonGestorImoUni.getInstance(getApplicationContext()).getAnuncio(id);

        etTitulo = findViewById(R.id.etTitulo);
        etPreco = findViewById(R.id.etDescricao);
        etDescricao = findViewById(R.id.etPreco);


        //SharedPreferences sharedPrefUser = getSharedPreferences(MenuMainActivity.PREF_INFO_USER, Context.MODE_PRIVATE);
        //token = sharedPrefUser.getString(MenuMainActivity.TOKEN, null);

        SingletonGestorImoUni.getInstance(getApplicationContext()).setAnunciosListener(this);


    }

    private boolean validarAnuncio() {
        String titulo = etTitulo.getText().toString();
        String preco = etPreco.getText().toString();
        String descricao = etDescricao.getText().toString();


        if(titulo.length()<3){
            etTitulo.setError("Título inválido");
            return false;
        }

        if(preco.length()<3){
            etPreco.setError("Preço inválido");
            return false;
        }

        if(descricao.length()<10){
            etDescricao.setError("Descrição inválida");
            return false;
        }

        return true;
    }

    private void carregarDetalhesLivro() {
        etTitulo.setText(anuncio.getTitulo());
        etPreco.setText(String.valueOf(anuncio.getPreco()));
        etDescricao.setText(anuncio.getDescricao());
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