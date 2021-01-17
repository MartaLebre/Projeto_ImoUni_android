package amsi.dei.estg.ipleiria.imouni.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AnuncioDBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "anunciosDB";
    private static final int DB_VERSION = 1;

    private static final String TABLE_ANUNCIOS = "Anuncios";
    private static final String ID_ANUNCIO = "id";
    private static final String ID_PROPRIETARIO = "id_proprietario";
    private static final String ID_CASA = "id_casa";
    private static final String TITULO_ANUNCIO = "titulo";
    private static final String PRECO_ANUNCIO = "preco";
    private static final String CRIACAO_ANUNCIO = "data_criacao";
    private static final String DISPONIBILIDADE_ANUNCIO = "data_disponibilidade";
    private static final String DESPESAS_ANUNCIO = "despesas_inc";
    private static final String DESCRICAO_ANUNCIO = "descricao";

    private final SQLiteDatabase db;

    public AnuncioDBHelper(@Nullable Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sqlCreateTableAnuncio = "CREATE TABLE " +
                TABLE_ANUNCIOS + " (" +
                ID_ANUNCIO + " INT PRIMARY KEY, " +
                ID_PROPRIETARIO + " INT NOT NULL, " +
                ID_CASA + " INT NOT NULL, " +
                TITULO_ANUNCIO + " VARCHAR(45) NOT NULL, " +
                PRECO_ANUNCIO + " INT NOT NULL, " +
                CRIACAO_ANUNCIO + " TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                DISPONIBILIDADE_ANUNCIO + " TEXT NOT NULL, " +
                DESPESAS_ANUNCIO + " TINYINT NOT NULL, " +
                DESCRICAO_ANUNCIO + " TEXT NOT NULL );";

        db.execSQL(sqlCreateTableAnuncio);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlDropTableAnuncio = "DROP TABLE IF EXISTS " + TABLE_ANUNCIOS;
        db.execSQL(sqlDropTableAnuncio);
        this.onCreate(db);
    }

    /**
     * SELECT
     */
    public ArrayList<Anuncio> getAllAnunciosDB(){
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_ANUNCIOS, new String[]{ID_ANUNCIO, ID_PROPRIETARIO, ID_CASA, TITULO_ANUNCIO,PRECO_ANUNCIO, CRIACAO_ANUNCIO, DISPONIBILIDADE_ANUNCIO, DESPESAS_ANUNCIO, DESCRICAO_ANUNCIO},
                null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Anuncio auxAnuncio = new Anuncio(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getInt(7),
                        cursor.getString(8)

                );

                anuncios.add(auxAnuncio);
            }while(cursor.moveToNext());
        }
        return anuncios;
    }

    /**
     * INSERT
     */
    public void adicionarAnuncioDB(Anuncio anuncio) {
        ContentValues values = new ContentValues();
        values.put(ID_ANUNCIO, anuncio.getId());
        values.put(ID_PROPRIETARIO, anuncio.getId_proprietario());
        values.put(ID_CASA, anuncio.getId_casa());
        values.put(TITULO_ANUNCIO, anuncio.getTitulo());
        values.put(PRECO_ANUNCIO, anuncio.getPreco());
        values.put(CRIACAO_ANUNCIO, anuncio.getData_criacao().toString());
        values.put(DISPONIBILIDADE_ANUNCIO, anuncio.getData_disponibilidade().toString());
        values.put(DESPESAS_ANUNCIO, anuncio.getDespesas_inc());
        values.put(DESCRICAO_ANUNCIO, anuncio.getDescricao());

        this.db.insert(TABLE_ANUNCIOS, null, values);
    }

    /**
     * DELETE
     */
    public boolean removerAnuncioDB(int id){
        return this.db.delete(TABLE_ANUNCIOS, "id= ?", new String[]{id + ""}) > 0;
    }

    public void removerAllAnunciosDB(){
        this.db.delete(TABLE_ANUNCIOS,  null, null);
    }
}
