package amsi.dei.estg.ipleiria.imouni.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.imouni.R;
import amsi.dei.estg.ipleiria.imouni.modelo.Anuncio;


public class ListaAnuncioAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Anuncio> anuncios;

    public ListaAnuncioAdaptador(Context context, ArrayList<Anuncio> anuncios) {
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
        if(inflater == null)
        {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.item_lista_anuncio, null);
        }

        //otimização

        ViewHolderLista viewHolderLista = (ViewHolderLista)convertView.getTag();
        if(viewHolderLista == null){
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }
        viewHolderLista.update(anuncios.get(position));


        return convertView;
    }

    private class ViewHolderLista{
        private TextView tvTitulo, tvPreco, tvPublicado, tvDescricao;


        public ViewHolderLista(View view){
            tvTitulo = view.findViewById(R.id.txtTitulo);
            tvPreco = view.findViewById(R.id.txtDataPublicacao);
            tvPublicado = view.findViewById(R.id.txtPreco);

        }

        public void update(Anuncio anuncio){
            tvTitulo.setText(anuncio.getTitulo());
            tvPreco.setText(String.valueOf(anuncio.getPreco()) + "€");
            tvPublicado.setText(anuncio.getData_criacao());


        }
    }
}
