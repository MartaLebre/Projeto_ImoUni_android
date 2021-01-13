package amsi.dei.estg.ipleiria.imouni.modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Patterns;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import amsi.dei.estg.ipleiria.imouni.R;

public class Utilizador {
    private int id;
    private String username, email, password, primeiroNome, ultimoNome, numeroTelemovel, genero, dataNascimento;

    private Pattern pattern;
    private Matcher matcher;

    public Utilizador(String username, String email, String password, String primeiroNome, String ultimoNome, String numeroTelemovel, String genero, String dataNascimento) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.numeroTelemovel = numeroTelemovel;
        this.genero = genero;
        this.dataNascimento = dataNascimento;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getNumeroTelemovel() {
        return numeroTelemovel;
    }

    public void setNumeroTelemovel(String numeroTelemovel) {
        this.numeroTelemovel = numeroTelemovel;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }




    public static void saveUser(JSONObject response, SharedPreferences preferences){
        try {
            // Saves authkey and user information in the shared preferences
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("id", response.getString("id"));
            editor.putString("username", response.getString("username"));
            editor.putString("authkey", response.getString("auth_key"));
            editor.putString("email", response.getString("email"));
            editor.putString("primeiroNome", response.getString("primeiroNome"));
            editor.putString("ultimoNome", response.getString("ultimoNome"));
            editor.putString("numeroTelemovel", response.getString("numeroTelemovel"));
            editor.putString("genero", response.getString("genero"));
            editor.putString("dataNascimento", response.getString("dataNascimento"));
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void deleteUser(Context context){
        try {
            // Removes user data from shared preferences
            SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.app_preferences), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("id");
            editor.remove("username");
            editor.remove("authkey");
            editor.remove("email");
            editor.remove("primeiroNome");
            editor.remove("ultimoNome");
            editor.remove("numeroTelemovel");
            editor.remove("genero");
            editor.remove("dataNascimento");
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean checkUsername(String username){
        // Vê se o utilizador inseriu um username
        if (TextUtils.isEmpty(username)) {
            return false;
        } else if (username.length() > 255) {
            return false;
        } else if (username.length() < 2) {
            return false;
        }
        return true;
    }
    public static boolean checkEmail(String email){
        // Vê se o utilizador inseriu um e-amil e se é válido
        if (TextUtils.isEmpty(email)) {
            return false;
        } else if (!email.contains("@")) {
            return false;
        } else if (email.length() > 255) {
            return false;
        } else if (email.length() < 6) {
            return false;
        }
        return true;
    }

    public static boolean checkFirstName(String primeiroNome){
        // Vê se o utilizador inseriu um nome
        if (TextUtils.isEmpty(primeiroNome)) {
            return false;
        } else if (primeiroNome.length() > 50) {
            return false;
        } else if (primeiroNome.length() < 2) {
            return false;
        }
        return true;
    }

    public static boolean checkLastName(String ultimoNome){
        // Vê se o utilizador inseriu um apelido
        if (TextUtils.isEmpty(ultimoNome)) {
            return false;
        } else if (ultimoNome.length() > 50) {
            return false;
        } else if (ultimoNome.length() < 2) {
            return false;
        }
        return true;
    }

    public static boolean checkPhone(String numeroTelemovel){
        // Vê se o utilizador inseriu um telefone
        if (TextUtils.isEmpty(numeroTelemovel)) {
            return false;
        } else if (numeroTelemovel.length() != 9) {
            return false;
        }
        return true;
    }





}
