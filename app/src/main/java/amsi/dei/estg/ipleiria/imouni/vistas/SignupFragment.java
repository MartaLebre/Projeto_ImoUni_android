package amsi.dei.estg.ipleiria.imouni.vistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import amsi.dei.estg.ipleiria.imouni.R;
import amsi.dei.estg.ipleiria.imouni.listeners.UserListener;
import amsi.dei.estg.ipleiria.imouni.modelo.SingletonGestorImoUni;
import amsi.dei.estg.ipleiria.imouni.modelo.Utilizador;

public class SignupFragment extends Fragment implements UserListener {

    Button btn_signUp;
    EditText username;
    EditText primeiroNome;
    EditText ultimoNome;
    EditText email;
    EditText numeroTelemovel;
    EditText genero;
    DatePicker dataNascimento;
    EditText password;
    //private Utilizador utilizador;
    public UserListener userListener;

    private static final String mUrlAPIRegistarUser = "http://192.168.1.77:8080/user/registo";

    private Utilizador utilizador;
    private Pattern pattern;
    private Matcher matcher;
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        fragmentManager = getFragmentManager();

        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        SingletonGestorImoUni.getInstance(getContext()).setUserListener(this);

        username = view.findViewById(R.id.etUsername);
        primeiroNome = view.findViewById(R.id.etprimeiroNome);
        ultimoNome = view.findViewById(R.id.etultimoNome);
        email = view.findViewById(R.id.etEmail);
        numeroTelemovel = view.findViewById(R.id.etTelemovel);
        genero = view.findViewById(R.id.etGenero);
        //dataNascimento = findViewById(R.id.dpNascimento);
        password = view.findViewById(R.id.etPassword);

        btn_signUp = view.findViewById(R.id.btnSignup);
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SingletonGestorImoUni.isConnectedInternet(getContext())) {

                     String mPrimeiroNome = primeiroNome.getText().toString();
                     String mUltimoNome = ultimoNome.getText().toString();
                     String mUsername = username.getText().toString();
                     String mEmail = email.getText().toString();
                    String mPassword = password.getText().toString();
                     String mNumeroTelemovel = numeroTelemovel.getText().toString();
                     String mGenero = genero.getText().toString();

                    utilizador = new Utilizador(mPrimeiroNome, mUltimoNome,  mUsername, mEmail, mPassword, mNumeroTelemovel, mGenero);
                    SingletonGestorImoUni.getInstance(getContext()).registarUserAPI(utilizador, getContext());

                }else{
                    Toast.makeText(getContext(), "Sem ligação à Internet!", Toast.LENGTH_LONG).show();

                }
            }

        });

        return view;
    }


    @Override
    public void onUserRegistado(String response) {
        Log.e("resposta", response);

        switch (response) {
            case "0":
                Log.e("eee", "1111");
                email.setError("Este email já se encontra registado!");
                break;
            case "1":
                username.setError("Este nome de utilizador já se encontra registado!");
                break;
            case "2":
                numeroTelemovel.setError("Este numero de telemovel já se encontra registado!");
                break;
            case "false":
                Fragment fragment = new LoginFragment();
                fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).addToBackStack(null).commit();
                Toast.makeText(getContext(), "Bem Vindo(a), a sua conta foi registada com sucesso!", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onValidateLogin(String username, String password) {

    }

}


