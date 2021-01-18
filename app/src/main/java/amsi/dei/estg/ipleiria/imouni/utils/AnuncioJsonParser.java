package amsi.dei.estg.ipleiria.imouni.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.imouni.modelo.Anuncio;


public class AnuncioJsonParser {

    public static ArrayList<Anuncio> parserJsonAnuncios(JSONArray response){
        ArrayList<Anuncio> anuncios = new ArrayList<>();

        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject anuncio = (JSONObject) response.get(i);
                anuncios.add(new Anuncio(anuncio.getInt("id"), anuncio.getInt("id_proprietario"), anuncio.getInt("id_casa"), anuncio.getString("titulo"), anuncio.getInt("preco"), anuncio.getString("data_criacao"),anuncio.getString("data_disponibilidade"), anuncio.getInt("despesas_inc"),anuncio.getString("descricao")));
            }
        } catch(JSONException e) {
            e.printStackTrace();
        }
            return anuncios;
    }

   public static Anuncio parserJsonAnuncio(String response){

       Anuncio auxLivro = null;
       try {
           JSONObject anuncio = new JSONObject(response);
           auxLivro = new Anuncio(anuncio.getInt("id"), anuncio.getInt("id_proprietario"), anuncio.getInt("id_casa"), anuncio.getString("titulo"), anuncio.getInt("preco"), anuncio.getString("data_criacao"),anuncio.getString("data_disponibilidade"), anuncio.getInt("despesas_inc"),anuncio.getString("descricao"));

       } catch (JSONException e) {
           e.printStackTrace();
       }

       return  auxLivro;
   }

    public static String parserJsonLogin(String response){
        String token = null;
        try {
            JSONObject login = new JSONObject(response);
           if(login.getBoolean("success"))
               token=login.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  token;

    }

    public  static  boolean isConnectionInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        return ni != null && ni.isConnected();

    }


}
