package amsi.dei.estg.ipleiria.imouni.vistas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


public class EditProfileFragment extends Fragment implements UserListener {

    EditText username;
    EditText primeiroNome;
    EditText ultimoNome;
    EditText email;
    EditText numeroTelemovel;
    EditText password;

    private RadioButton rbtnMasculino, rbtnFeminino;


    private FragmentManager fragmentManager;
    private Utilizador utilizador;

    private Pattern pattern;
    private Matcher matcher;

    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        fragmentManager = getFragmentManager();

        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        SingletonGestorImoUni.getInstance(getContext()).setUserListener(this);


        numeroTelemovel = view.findViewById(R.id.etTelemovel);
        password = view.findViewById(R.id.etPassword);

        SharedPreferences sharedPreferencesUser = getActivity().getSharedPreferences(MenuMainActivity.PREF_INFO_USER, Context.MODE_PRIVATE);
        String token = sharedPreferencesUser.getString(MenuMainActivity.USERNAME, null);

        //SingletonGestorImoUni.getInstance(getContext()).getUserAPI(getContext(), token);

        Button button = view.findViewById(R.id.btnAtualizar);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (SingletonGestorImoUni.isConnectedInternet(getContext())) {


                    String mPassword = password.getText().toString();
                    String mNumeroTelemovel = numeroTelemovel.getText().toString();


                    utilizador = new Utilizador(null, null,  null, null, mPassword, mNumeroTelemovel, null);
                    SharedPreferences sharedPreferencesUser = getActivity().getSharedPreferences(MenuMainActivity.PREF_INFO_USER, Context.MODE_PRIVATE);
                    String token = sharedPreferencesUser.getString(MenuMainActivity.USERNAME, null);
                    SingletonGestorImoUni.getInstance(getContext()).editarUtilizadorAPI(utilizador, getContext(), mPassword,token);
                }
            }
        });

        Button buttonApagar = view.findViewById(R.id.btnapagarConta);
        buttonApagar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (SingletonGestorImoUni.isConnectedInternet(getContext())) {


                    String mPassword = password.getText().toString();
                    String mNumeroTelemovel = numeroTelemovel.getText().toString();


                    utilizador = new Utilizador(null, null,  null, null, mPassword, mNumeroTelemovel, null);
                    SharedPreferences sharedPreferencesUser = getActivity().getSharedPreferences(MenuMainActivity.PREF_INFO_USER, Context.MODE_PRIVATE);
                    String token = sharedPreferencesUser.getString(MenuMainActivity.USERNAME, null);
                    SingletonGestorImoUni.getInstance(getContext()).apagarContaAPI(token, getContext());
                }
            }
        });

        return  view;
    }

    @Override
    public void onUserRegistado(String response) {

    }

    @Override
    public void onRefreshDetalhes(String resposta) {
        switch (resposta) {
            case "true":
                Fragment fragment = new MainFragment();
                fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).addToBackStack(null).commit();
                Toast.makeText(getContext(), "@string/EditProfileFragment_onRefreshDetalhes", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onApagarConta(String resposta) {

        switch (resposta) {
            case "null":
                Fragment fragment = new SignupFragment();
                fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).addToBackStack(null).commit();
                Toast.makeText(getContext(), "@string/EditProfileFragment_onApagarConta", Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    public void onValidateLogin(String token, String username) {

    }
}