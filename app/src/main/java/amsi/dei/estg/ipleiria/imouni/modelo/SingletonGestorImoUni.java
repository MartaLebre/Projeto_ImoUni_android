package amsi.dei.estg.ipleiria.imouni.modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import amsi.dei.estg.ipleiria.imouni.R;
import amsi.dei.estg.ipleiria.imouni.listeners.UserListener;
import amsi.dei.estg.ipleiria.imouni.utils.UtilizadoresParserJson;
import amsi.dei.estg.ipleiria.imouni.listeners.AnunciosListener;
import amsi.dei.estg.ipleiria.imouni.utils.AnuncioParserJson;

public class SingletonGestorImoUni{

    private static final String mUrlAPIRegistarUser = "http://192.168.1.68:8080/user/registo";
    private static final String mUrlAPIUserLogin = "http://192.168.1.68:8080/user/login/";
    private static final String mUrlAPIAnuncios = "http://192.168.1.68:8080/anuncios/";

    private ArrayList<Utilizador> utilizadores;
    public UserListener userListener;
    private ArrayList<Anuncio> anuncios;
    private AnuncioDBHelper anunciosDB = null;
    public AnunciosListener anunciosListener;

    private static SingletonGestorImoUni instance = null;
    private static Context sContext;
    private static RequestQueue volleyQueue = null;


    public static synchronized SingletonGestorImoUni getInstance(Context context){
        if (instance == null){
            instance = new SingletonGestorImoUni(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return instance;
    }

    public SingletonGestorImoUni(Context context){
        anuncios = new ArrayList<>();
        anunciosDB = new AnuncioDBHelper(context);
    }

    public void setUserListener(UserListener userListener){
        this.userListener = userListener;
    }

    public void setAnunciosListener(AnunciosListener anunciosListener){
        this.anunciosListener = anunciosListener;
    }

    public Anuncio getAnuncio(int id){
        for(Anuncio anuncio : anuncios){
            if(anuncio.getId() == id)
                return anuncio;
        }
        return null;
    }

    public static boolean isConnectedInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    /*********** Metodos para aceder a DB local ***********/

    public ArrayList<Anuncio> getAnunciosDB(){
        anuncios = anunciosDB.getAllAnunciosDB();

        return anuncios;
        // return new ArrayList<>(anuncios);
    }

    public void adicionarAnuncioDB(Anuncio anuncio){
        anunciosDB.adicionarAnuncioDB(anuncio);
    }

    public void adicionarAnunciosDB(ArrayList<Anuncio> anuncios) {
        anunciosDB.removerAllAnunciosDB();
        for(Anuncio anuncio : anuncios){
            adicionarAnuncioDB(anuncio);
        }
    }
    public void removerAnuncioDB(int id){
        Anuncio anuncio = getAnuncio(id);
        if(anuncio!=null)

            anunciosDB.removerAnuncioDB(id);
    }



    /*********** Métodos de acesso à API - Utilizador ***********/

    public void registarUserAPI(final Utilizador utilizador, final Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, mUrlAPIRegistarUser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (userListener != null) {
                            userListener.onUserRegistado(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("primeiro_nome", utilizador.getPrimeiroNome());
                params.put("ultimo_nome", utilizador.getUltimoNome());
                params.put("username", utilizador.getUsername());
                params.put("email", utilizador.getEmail());
                params.put("password", utilizador.getPassword());
                params.put("numero_telemovel", utilizador.getNumeroTelemovel());
                params.put("genero", utilizador.getGenero());

                return params;
            }
        };
        volleyQueue.add(stringRequest);
    }

    public void loginUserAPI(final String username, final String password, final Context context) {
        StringRequest req = new StringRequest(Request.Method.POST,  mUrlAPIUserLogin, new Response.Listener<String>() {

            public void onResponse(String response) {
                if(userListener != null){
                    userListener.onValidateLogin(UtilizadoresParserJson.parserJsonLogin(response), username);
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        volleyQueue.add(req);
    }

    /*********** Métodos de acesso à API - Anúncios ***********/

    public void getAllAnunciosAPI(final Context context) {
        if(!isConnectedInternet(context)){
            Toast.makeText(context, "Não tem ligação à Internet", Toast.LENGTH_SHORT).show();

            if(anunciosListener != null){
                anunciosListener.onRefreshListaAnuncios(anunciosDB.getAllAnunciosDB());
            }
        }
        else{
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mUrlAPIAnuncios, null, new Response.Listener<JSONArray>(){

                @Override
                public void onResponse(JSONArray response){
                    anuncios = AnuncioParserJson.parserJsonAnuncios(response);
                    adicionarAnunciosDB(anuncios);

                    if(anunciosListener != null) {
                        anunciosListener.onRefreshListaAnuncios(anuncios);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            volleyQueue.add(request);
        }
    }
}



