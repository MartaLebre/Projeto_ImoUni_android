package amsi.dei.estg.ipleiria.imouni.vistas;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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

    private RadioButton rbtnMasculino, rbtnFeminino;
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
        rbtnMasculino = view.findViewById(R.id.rbtnMasculino);
        rbtnFeminino = view.findViewById(R.id.rbtnFeminino);
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
                     String genero = "";

                    if (rbtnMasculino.isChecked()) {
                        genero = "masculino";
                    } else if (rbtnFeminino.isChecked()) {
                        genero = "feminino";
                    }

                    if (!isPrimeiroNomeValido(mPrimeiroNome)) {
                        primeiroNome.setError("Primeiro nome inválido");
                        return;
                    }

                    if (!isUltimoNomeValido(mUltimoNome)) {
                        ultimoNome.setError("Ultimo nome inválido");
                        return;
                    }

                    if (!isUsernameValido(mUsername)) {
                        username.setError("Nome de Utilizador Inválido");
                        return;
                    }

                    if (!isEmailValido(mEmail)) {
                        email.setError("Email inválido");
                        return;
                    }

                    if (!isPasswordValida(mPassword)) {
                        password.setError("Palavra Passe Inválida, é necessário ter mais de 8 caractéres");
                        return;
                    }
                    if (!isNumeroTelemovelValido(mNumeroTelemovel)) {
                        numeroTelemovel.setError("Número de telemóvel inválido");
                        return;
                    }


                    int tipo = 1;
                    utilizador = new Utilizador( mUsername, mEmail, mPassword,mPrimeiroNome, mUltimoNome, mNumeroTelemovel, genero, tipo);
                    SingletonGestorImoUni.getInstance(getContext()).registarUserAPI(utilizador, getContext());

                }else{
                    Toast.makeText(getContext(), "Sem ligação à Internet!", Toast.LENGTH_LONG).show();

                }
            }

        });

        return view;
    }
    private boolean isPrimeiroNomeValido(String primeiroNome) {
        if (primeiroNome == null) {
            return false;
        }
        return primeiroNome.length() > 0;

    }

    private boolean isUltimoNomeValido(String ultimoNome) {
        if (ultimoNome == null) {
            return false;
        }
        return ultimoNome.length() > 0;

    }

    private boolean isUsernameValido(String username) {
        if (username == null) {
            return true;
        }
        return username.length() > 0;

    }

    private boolean isEmailValido(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValida(String password) {
        if (password == null) {
            return true;
        }
        return password.length() > 8;

    }

    public  boolean isNumeroTelemovelValido(String numeroTelemovel){
        if (numeroTelemovel == null) {
            return false;
        } else if (numeroTelemovel.length() != 9) {
            return false;
        }
        return true;
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
    public void onRefreshDetalhes(String response) {

    }

    @Override
    public void onApagarConta(String response) {

    }


    @Override
    public void onValidateLogin(String username, String password) {

    }

}


