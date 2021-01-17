package amsi.dei.estg.ipleiria.imouni.vistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import amsi.dei.estg.ipleiria.imouni.R;
import amsi.dei.estg.ipleiria.imouni.listeners.UserListener;
import amsi.dei.estg.ipleiria.imouni.modelo.SingletonGestorImoUni;
import amsi.dei.estg.ipleiria.imouni.modelo.Utilizador;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment implements UserListener {

    Button mLoginButton;
    EditText mUsername;
    EditText mPassword;
    private static final String mUrlAPIUserLogin = "http://10.0.2.2:8080/api/user/login/";
    private FragmentManager fragmentManager;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        SingletonGestorImoUni.getInstance(getContext()).setUserListener(this);

        fragmentManager = getFragmentManager();
        mLoginButton = view.findViewById(R.id.btnLogin);
        mUsername = view.findViewById(R.id.etUsername);
        mPassword = view.findViewById(R.id.etPassword);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SingletonGestorImoUni.isConnectedInternet(getContext())) {
                    String username = mUsername.getText().toString();
                    String password = mPassword.getText().toString();

        SingletonGestorImoUni.getInstance(getContext()).loginUserAPI(username, password, getContext());
    }

}
});
        Button btnRegistar = view.findViewById(R.id.btnSignup);
        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SignupFragment();
                fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).addToBackStack(null).commit();
            }
        });
return view;
}

    @Override
    public void onUserRegistado(String response) {

    }

    @Override
    public void onValidateLogin(String token, String username) {
        if (token != null) {
            guardarInfoSharedPref(token, username);
            Fragment fragment = new MainFragment();
            fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).addToBackStack(null).commit();
            Toast.makeText(getContext(), "Bem Vindo!", Toast.LENGTH_LONG).show();
        } else {
            mPassword.setError("Utilizador ou Palavra-Passe Incorretos!");
        }
    }

    private void guardarInfoSharedPref(String token, String username) {
        SharedPreferences sharedPreferencesUser = getActivity().getSharedPreferences(MenuMainActivity.PREF_INFO_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesUser.edit();

        editor.putString(MenuMainActivity.USERNAME, username);
        editor.putString(MenuMainActivity.TOKEN, token);

        editor.apply();
    }
}