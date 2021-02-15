package amsi.dei.estg.ipleiria.imouni.modelo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import amsi.dei.estg.ipleiria.imouni.listeners.AnunciosListener;
import amsi.dei.estg.ipleiria.imouni.listeners.UserListener;
import amsi.dei.estg.ipleiria.imouni.utils.AnuncioJsonParser;
import amsi.dei.estg.ipleiria.imouni.utils.UtilizadoresParserJson;


public class SingletonGestorImoUni {

    private static SingletonGestorImoUni instance = null;
    private ArrayList<Anuncio> anuncios;
    private AnuncioDBHelper anunciosBD = null;
    private static RequestQueue volleyQueue;
    private static final String mUrlAPIRegistarUser = "http://192.168.1.68:8080/user/registo";
    private static final String mUrlAPIUserLogin = "http://192.168.1.68:8080/user/login/";
    private static final String mUrlAPIAnuncios = "http://192.168.1.68:8080/anuncios";
    private static final String mUrlAPIEditarRegistoUser = "http://192.168.1.68:8080/user/editar";
    private static final String mUrlAPIApagarUser = "http://192.168.1.68:8080/user/apagaruser";

    public UserListener userListener;

    private static final int ADICIONAR_BD = 1;
    private static  final int  EDITAR_BD = 2;

    private AnunciosListener anunciosListener;


    public void setUserListener(UserListener userListener){
        this.userListener = userListener;
    }


    public static synchronized SingletonGestorImoUni getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonGestorImoUni(context);
            volleyQueue = Volley.newRequestQueue(context);
        }

        return instance;
    }

    private SingletonGestorImoUni(Context context) {
        anuncios = new ArrayList<>();
        anunciosBD = new AnuncioDBHelper(context);
    }


    public void setAnunciosListener(AnunciosListener anunciosListener) {
        this.anunciosListener = anunciosListener;
    }




    public Anuncio getAnuncio(int id) {
        for (Anuncio l : anuncios) {
            if (l.getId() == id)
                return l;
        }
        return null;
    }


    public static boolean isConnectedInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    /*********** Metodos para aceder a BD local ************/

    public ArrayList<Anuncio> getAnunciosDB() {
        anuncios = anunciosBD.getAllAnunciosBD();

        return anuncios;

    }

    public void adicionarAnuncioBD(Anuncio anuncio) {
        anunciosBD.adicionarAnuncioBD(anuncio);

    }

    public void adicionarAnunciosBD(ArrayList<Anuncio> anuncios) {
        anunciosBD.removerAllAnunciosBD();
        for (Anuncio l : anuncios)
            adicionarAnuncioBD(l);
    }

    public void editarAnuncioBD(Anuncio livro){
        Anuncio livroAux = getAnuncio(livro.getId());
        if(livroAux != null)
            anunciosBD.editarAnuncioBD(livroAux);

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
        }) {
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
                params.put("tipo", String.valueOf(utilizador.getTipo()));

                return params;
            }
        };
        volleyQueue.add(stringRequest);
    }

    public void loginUserAPI(final String username, final String password, final Context context) {
        StringRequest req = new StringRequest(Request.Method.POST, mUrlAPIUserLogin, new Response.Listener<String>() {

            public void onResponse(String response) {
                if (userListener != null) {
                    userListener.onValidateLogin(UtilizadoresParserJson.parserJsonLogin(response), username);
                }
            }
        }, new Response.ErrorListener() {
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

    public void editarUtilizadorAPI(final Utilizador utilizador, final Context context, final String password, final String username) {
        StringRequest req = new StringRequest(Request.Method.PUT, mUrlAPIEditarRegistoUser + "/" + username, new Response.Listener<String>() {

            public void onResponse(String response) {
                if (userListener != null) {
                    userListener.onRefreshDetalhes(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("password", utilizador.getPassword());

                params.put("numero_telemovel", utilizador.getNumeroTelemovel());

                //params.put("tipo", String.valueOf(utilizador.getTipo()));


                return params;
            }
        };
        volleyQueue.add(req);
    }
    public void apagarContaAPI(String username, final Context context) {
        StringRequest req = new StringRequest(Request.Method.PATCH, mUrlAPIApagarUser + "/" +  username, new Response.Listener<String>() {

            public void onResponse(String response) {
                if (userListener != null) {
                    userListener.onApagarConta(response);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        volleyQueue.add(req);

    }


    /*********** Métodos de acesso à API - Anúncios ***********/
    public void adicionarAnuncioAPI(final Anuncio anuncio, final Context context) {
        if(!AnuncioJsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, "Não tem ligação à Internet", Toast.LENGTH_LONG).show();
        }
        else{
                StringRequest request = new StringRequest(Request.Method.POST, mUrlAPIAnuncios, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Anuncio temp = AnuncioJsonParser.parserJsonAnuncio(response);
                        onUpdateListaAnunciosBD(temp, ADICIONAR_BD);

                        if (anunciosListener != null)
                            anunciosListener.onRefreshDetalhes();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        params.put("titulo", anuncio.getTitulo());
                        params.put("preco", anuncio.getPreco() + " ");
                        params.put("data_criacao", anuncio.getData_criacao());
                        params.put("descricao", anuncio.getDescricao());

                        return params;
                    }
                };

                volleyQueue.add(request);
            }
        }


    public void getAllAnunciosAPI(final Context context) {
        if (!AnuncioJsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, "Não tem ligação à Internet", Toast.LENGTH_LONG).show();

            if (anunciosListener != null) {
                anunciosListener.onRefreshListaAnuncios(anunciosBD.getAllAnunciosBD());
            }
        } else {
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mUrlAPIAnuncios, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    anuncios = AnuncioJsonParser.parserJsonAnuncios(response);
                    adicionarAnunciosBD(anuncios);

                    if (anunciosListener != null) {
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
    private void onUpdateListaAnunciosBD(Anuncio anuncio, int operacao) {
        switch (operacao) {
            case ADICIONAR_BD:
                adicionarAnuncioBD(anuncio);
                break;
            case EDITAR_BD:
                editarAnuncioBD(anuncio);
                break;
        }
    }
}



