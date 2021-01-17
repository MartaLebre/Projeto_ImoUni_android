package amsi.dei.estg.ipleiria.imouni.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.imouni.modelo.Anuncio;

public interface AnunciosListener {
    void onRefreshListaAnuncios(ArrayList<Anuncio> listaAnuncios);

    void onRefreshDetalhes();
}