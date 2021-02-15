package amsi.dei.estg.ipleiria.imouni.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class AnuncioDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="anunciosDB";
    private static final int DB_VERSION=1;

    private static final String TABLE_ANUNCIOS="Anuncios";
    private static final String ID_ANUNCIO="id";
    private static final String ID_PROPRIETARIO = "id_proprietario";
    private static final String ID_CASA = "id_casa";
    private static final String TITULO_ANUNCIO = "titulo";
    private static final String PRECO_ANUNCIO = "preco";
    private static final String CRIACAO_ANUNCIO = "data_criacao";
    private static final String DISPONIBILIDADE_ANUNCIO = "data_disponibilidade";
    private static final String DESPESAS_ANUNCIO = "despesas_inc";
    private static final String DESCRICAO_ANUNCIO = "descricao";
    private static final String NUMERO_TELEMOVEL = "numero_telemovel";

    private final SQLiteDatabase db;

    public AnuncioDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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
                DESCRICAO_ANUNCIO + " TEXT NOT NULL, " +
                NUMERO_TELEMOVEL + " INT  );";

        db.execSQL(sqlCreateTableAnuncio);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlDropTableLivro="DROP TABLE IF EXISTS "+ TABLE_ANUNCIOS;
        db.execSQL(sqlDropTableLivro);
        this.onCreate(db);
    }

    /**
     * INSERT
     * o método insert() -> return idlivro (long), se houver erro devolve -1
     * @param anuncio
     * @return
     */
    public void adicionarAnuncioBD(Anuncio anuncio) {
        ContentValues values = new ContentValues();
        values.put(ID_ANUNCIO, anuncio.getId());
        values.put(ID_PROPRIETARIO, anuncio.getId_proprietario());
        values.put(ID_CASA, anuncio.getIdcasa());
        values.put(TITULO_ANUNCIO, anuncio.getTitulo());
        values.put(PRECO_ANUNCIO, anuncio.getPreco());
        values.put(CRIACAO_ANUNCIO, anuncio.getData_criacao());
        values.put(DISPONIBILIDADE_ANUNCIO, anuncio.getData_disponibilidade());
        values.put(DESPESAS_ANUNCIO, anuncio.getDespesas_inc());
        values.put(DESCRICAO_ANUNCIO, anuncio.getDescricao());
        values.put(NUMERO_TELEMOVEL, anuncio.getNumero_telefone());

        this.db.insert(TABLE_ANUNCIOS, null, values);

    }
    /**
     * UPDATE
     * o método update() -> return o nº de linhas alteradas
     * @param anuncio
     * @return
     */
    public boolean editarAnuncioBD(Anuncio anuncio){
        ContentValues values = new ContentValues();
        values.put(TITULO_ANUNCIO, anuncio.getTitulo());
        values.put(PRECO_ANUNCIO, anuncio.getPreco());
        values.put(DESCRICAO_ANUNCIO, anuncio.getDescricao());



        return this.db.update(TABLE_ANUNCIOS, values, "id=?", new String[]{anuncio.getId() + ""}) > 0;
    }
    /**
     * DELETE
     * @param id
     * @return
     */
    public boolean removerAnuncioBD(int id){
        return this.db.delete(TABLE_ANUNCIOS, "id=?", new String[]{id + ""}) > 0;
    }

    public void removerAllAnunciosBD(){
         this.db.delete(TABLE_ANUNCIOS,  null, null);
    }

    /**
     * SELECT
     * this.db.rawQuery("codigo sql", null) -> suscetivel de SQLINJECTION
     * @return
     */
    public ArrayList<Anuncio> getAllAnunciosBD(){
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_ANUNCIOS, new String[]{ID_ANUNCIO, ID_PROPRIETARIO, ID_CASA, TITULO_ANUNCIO, PRECO_ANUNCIO, CRIACAO_ANUNCIO,DISPONIBILIDADE_ANUNCIO,DESPESAS_ANUNCIO,DESCRICAO_ANUNCIO,NUMERO_TELEMOVEL},
                null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Anuncio auxAuncio = new Anuncio(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getInt(7),
                        cursor.getString(8),
                        cursor.getInt(9));
                anuncios.add(auxAuncio);
            }while(cursor.moveToNext());
        }
        return anuncios;
    }
}