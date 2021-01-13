package amsi.dei.estg.ipleiria.imouni.vistas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import amsi.dei.estg.ipleiria.imouni.R;
import amsi.dei.estg.ipleiria.imouni.modelo.SingletonGestorImoUni;
import amsi.dei.estg.ipleiria.imouni.modelo.Utilizador;

import static java.security.AccessController.getContext;

public class SignupActivity extends AppCompatActivity {

    Button btn_signUp;
    EditText username;
    EditText primeiroNome;
    EditText ultimoNome;
    EditText email;
    EditText numeroTelemovel;
    CheckBox generoMasculino, generoFeminino;
    DatePicker dataNascimento;
    EditText password;
    private Utilizador utilizador;


    private Pattern pattern;
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.etUsername);
        primeiroNome = findViewById(R.id.etprimeiroNome);
        ultimoNome = findViewById(R.id.etultimoNome);
        email = findViewById(R.id.etEmail);
        numeroTelemovel = findViewById(R.id.etTelemovel);
        generoFeminino = findViewById(R.id.cbGenero_fem);
        generoMasculino = findViewById(R.id.cbGenero_mas);
        dataNascimento = findViewById(R.id.dpNascimento);
        password = findViewById(R.id.etPassword);

        btn_signUp = findViewById(R.id.btnSignup);
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (attempRegisto() != null) {
                    signupUserAPI(attempRegisto(), getApplicationContext());
                    setResult(Activity.RESULT_OK);
                    finish();
                }
            }
        });
    }

    private Utilizador attempRegisto() {

        final String mUsername = username.getText().toString();
        final String mEmail = email.getText().toString();
        final String mPassword = password.getText().toString();
        final String mPrimeiroNome = primeiroNome.getText().toString();
        final String mUltimoNome = ultimoNome.getText().toString();
        final String mNumeroTelemovel = numeroTelemovel.getText().toString();
        final String mDataNascimento = String.valueOf((dataNascimento.getDayOfMonth() + (dataNascimento.getMonth() + 1) + dataNascimento.getYear()));
                    /*if (rbtnFeminino.isChecked() || rbtnMasculino.isChecked()) {
                        if (rbtnMasculino.isChecked()) {
                            String sexo = "Masculino";
                        } else if (rbtnFeminino.isChecked()) {
                            String sexo = "Feminino";
                        }
                    }
                    if (rbtnMasculino.isChecked()) {
                        String sexoRBtn = "Masculino";
                    }
*/


        boolean cancel = false;
        View focusView = null;


        if (!Utilizador.checkUsername(mUsername)) {
            username.setError("Username Incorreto");
            focusView = username;
            cancel = true;
        }

        if (!Utilizador.checkEmail(mEmail)) {
            email.setError("Email inválido");
            focusView = email;
            cancel = true;
        }

        if (!Utilizador.checkFirstName(mPrimeiroNome)) {
            primeiroNome.setError("Primeiro Nome inválido");
            focusView = primeiroNome;
            cancel = true;
        }

        if (!Utilizador.checkLastName(mUltimoNome)){
            ultimoNome.setError("Ultimo Nome Inválido");
            focusView = ultimoNome;
            cancel = true;
        }

        if (!Utilizador.checkPhone(mNumeroTelemovel)) {
            numeroTelemovel.setError("Número de Telemóvel Incorreto");
            focusView = numeroTelemovel;
            cancel = true;
        }



        if (cancel) {
            // If there's an form input with errors it will focus on that editText
            focusView.requestFocus();
            return null;
        } else {
            //String genero = "Masculino";
            return new Utilizador(username, email, password, primeiroNome, ultimoNome, numeroTelemovel, genero, dataNascimento);
            SingletonGestorImoUni.getInstance(getApplicationContext()).signupUserAPI(utilizador, getApplicationContext());
        }
    }
    

}


