package amsi.dei.estg.ipleiria.imouni.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.imouni.modelo.Utilizador;


public class UtilizadoresParserJson {
    public static ArrayList<Utilizador> parserJsonUtilizadores(JSONArray response) {

        ArrayList<Utilizador> utilizadores = new ArrayList<>();

        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject utilizador = (JSONObject) response.get(i);

                String PrimeiroNome = utilizador.getString("primeiro_nome");
                String UltimoNome = utilizador.getString("ultimo_nome");
                String Username = utilizador.getString("username");
                String Email = utilizador.getString("email");
                String Password = utilizador.getString("password");
                String NumeroTelemovel = utilizador.getString("numero_telemovel");
                String Genero = utilizador.getString("genero");


                Utilizador auxUtilizador = new Utilizador( PrimeiroNome, UltimoNome, Username,Email, Password, NumeroTelemovel,Genero);
                utilizadores.add(auxUtilizador);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return utilizadores;
    }

    public static Utilizador parserJsonUtilizador(String response) {
        Utilizador auxUtilizador = null;

        try {
            JSONObject utilizador = new JSONObject(response);
            String PrimeiroNome = utilizador.getString("primeiro_nome");
            String UltimoNome = utilizador.getString("ultimo_nome");
            String Username = utilizador.getString("username");
            String Email = utilizador.getString("email");
            String Password = utilizador.getString("password");
            String NumeroTelemovel = utilizador.getString("numero_telemovel");
            String Genero = utilizador.getString("genero");

            auxUtilizador = new Utilizador( PrimeiroNome, UltimoNome,null, null, null, NumeroTelemovel,Genero);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return auxUtilizador;
    }

    public static String parserJsonLogin(String response) {
        String token = null;
        try {
            JSONObject login = new JSONObject(response);
            token = login.getString("verification_token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return token;
    }
}

