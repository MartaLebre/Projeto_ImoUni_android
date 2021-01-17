package amsi.dei.estg.ipleiria.imouni.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.imouni.R;
import amsi.dei.estg.ipleiria.imouni.modelo.Anuncio;

public class ListaAnuncioAdaptador extends BaseAdapter{
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Anuncio> anuncios;

    public ListaAnuncioAdaptador(Context context, ArrayList<Anuncio> anuncios){
        this.context = context;
        this.anuncios = anuncios;
    }

    @Override
    public int getCount() {

        return anuncios.size();
    }

    @Override
    public Object getItem(int position) {
        return anuncios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return anuncios.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_lista_anuncio, null);
        }

        /** otimização **/
        ViewHolderLista viewHolder = (ViewHolderLista) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolderLista(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.update(anuncios.get(position));

        return convertView;
    }

    private class ViewHolderLista {
        TextView tvListaAnuncio_titulo;
        TextView tvListaAnuncio_preco;
        TextView tvListaAnuncio_despesas;
        TextView tvListaAnuncio_publicado;

        public ViewHolderLista(View view){
            tvListaAnuncio_titulo = view.findViewById(R.id.tvListaAnuncio_titulo);
            tvListaAnuncio_preco = view.findViewById(R.id.tvListaAnuncio_preco);
            tvListaAnuncio_publicado = view.findViewById(R.id.tvListaAnuncio_publicado);
            tvListaAnuncio_despesas = view.findViewById(R.id.tvListaAnuncio_despesas);
        }

        public void update(Anuncio anuncio){
            tvListaAnuncio_titulo.setText(anuncio.getTitulo());
            tvListaAnuncio_preco.setText(String.valueOf(anuncio.getPreco()));
            tvListaAnuncio_despesas.setText(String.valueOf(anuncio.getDespesas_inc()));
            tvListaAnuncio_publicado.setText(anuncio.getData_criacao());
        }
    }
}
