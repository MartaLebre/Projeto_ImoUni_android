package amsi.dei.estg.ipleiria.imouni.modelo;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import amsi.dei.estg.ipleiria.imouni.R;
import amsi.dei.estg.ipleiria.imouni.listeners.UserListener;

public class SingletonGestorImoUni {

    private static SingletonGestorImoUni instance = null;
    private static RequestQueue volleyQueue = null;

    private static final String mUrlAPIUserLogin = "http://10.0.2.2:8080/api/user/login/"; //API pontos turisticos
    private static final String mUrlAPIRegistarUser = "http://10.0.2.2:8080/api/user/registo"; //API pontos turisticos

    public UserListener userListener;

    public static synchronized SingletonGestorImoUni getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonGestorImoUni(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return instance;
    }

    public SingletonGestorImoUni(Context context) {
    }


    /********* Métodos de acesso à API - Utilizador****/
    /**
     * Registar User API
     */


    public void loginUserAPI(final String username, final String password, final Context context) {
        StringRequest req = new StringRequest(Request.Method.POST,  mUrlAPIUserLogin, new Response.Listener<String>() {

            public void onResponse(String response) {
                if(userListener != null){
                    userListener.onValidateLogin(UtilizadoresParserJson.parserJsonLogin(response), email);
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
}



