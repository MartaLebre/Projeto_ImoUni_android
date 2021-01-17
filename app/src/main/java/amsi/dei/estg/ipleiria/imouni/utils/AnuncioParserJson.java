package amsi.dei.estg.ipleiria.imouni.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import amsi.dei.estg.ipleiria.imouni.modelo.Anuncio;

public class AnuncioParserJson {

    public static ArrayList<Anuncio> parserJsonAnuncios(JSONArray response) {

        ArrayList<Anuncio> anuncios = new ArrayList<>();

        try {
            for (int index = 0; index < response.length(); index++) {
                JSONObject anuncio = (JSONObject) response.get(index);
                anuncios.add(new Anuncio(
                        anuncio.getInt("id"),
                        anuncio.getInt("id_proprietario"),
                        anuncio.getInt("id_casa"),
                        anuncio.getString("titulo"),
                        anuncio.getInt("preco"),
                        anuncio.getString("data_criacao"),
                        anuncio.getString("data_disponibilidade"),
                        anuncio.getInt("despesas_inc"),
                        anuncio.getString("descricao")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return anuncios;
    }

    public static Anuncio parserJsonAnuncio(String response) {
        Anuncio auxAnuncio = null;
        try {
            JSONObject anuncio = new JSONObject(response);
            auxAnuncio = new Anuncio(
                    anuncio.getInt("id"),
                    anuncio.getInt("id_proprietario"),
                    anuncio.getInt("id_casa"),
                    anuncio.getString("titulo"),
                    anuncio.getInt("preco"),
                    anuncio.getString("data_criacao"),
                    anuncio.getString("data_disponibilidade"),
                    anuncio.getInt("despesas_inc"),
                    anuncio.getString("descricao")
            );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return auxAnuncio;
    }


}
