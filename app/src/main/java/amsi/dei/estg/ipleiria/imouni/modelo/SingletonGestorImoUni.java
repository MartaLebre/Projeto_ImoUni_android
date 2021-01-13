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

public class SingletonGestorImoUni {

    private static SingletonGestorImoUni instance = null;
    private static RequestQueue volleyQueue = null;

    private static final String mUrlAPIUserLogin = "http://10.0.2.2:8080/userprofile/login/"; //API pontos turisticos
    private static final String mUrlAPIRegistarUser = "http://10.0.2.2:8080/userprofile/registo"; //API pontos turisticos

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

    public void signupUserAPI(final Utilizador user, final Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, mUrlAPIRegistarUser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("isSuccess");

                            if (success.equals("201")) {
                                Toast.makeText(context, R.string.signup_success, Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(context, R.string.signup_error, Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Register Error: " + error.toString(), Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", user.getUsername());
                params.put("email", user.getEmail());
                params.put("password", user.getPassword());
                params.put("firstName", user.getPrimeiroNome());
                params.put("lastName", user.getUltimoNome());
                params.put("phone", user.getNumeroTelemovel());
                return params;
            }
        };
        volleyQueue.add(stringRequest);
    }
}



