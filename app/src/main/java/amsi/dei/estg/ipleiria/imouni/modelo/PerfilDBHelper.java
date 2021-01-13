package amsi.dei.estg.ipleiria.imouni.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PerfilDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="ImoUniBD";
    private static final int DB_VERSION=1;

    private static final String TABLE_NAME = "perfil";
    private static final String ID_USER = "id_user";
    private static final String TIPO = "tipo";
    private static final String DATA_NASCIMENTO = "data_nascimento";
    private static final String NUMERO_TELEMOVEL = "numero_telemovel";
    private static final String PRIMEIRO_NOME = "primeiro_nome";
    private static final String ULTIMO_NOME = "ultimo_nome";
    private static final String GENERO = "genero";

    private final SQLiteDatabase db;

    public PerfilDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.db=getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTablePerfil = "CREATE TABLE "+ TABLE_NAME +" ("+
                ID_USER +" INTEGER PRIMARY KEY, "+
                TIPO +" INTEGER NOT NULL, "+
                DATA_NASCIMENTO +" DATE NOT NULL, "+
                NUMERO_TELEMOVEL +" INTEGER NOT NULL, "+
                PRIMEIRO_NOME +" TEXT NOT NULL, "+
                ULTIMO_NOME +" TEXT NOT NULL, "+
                GENERO +" TEXT CHECK( GENERO IN ('Masculino','Feminino') )   NOT NULL DEFAULT '' );";
        db.execSQL(createTablePerfil);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTablePerfil = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(deleteTablePerfil);
        this.onCreate(db);
    }


    public void adicionarUtilizadorBD(Utilizador utilizador){

        ContentValues values = new ContentValues();


        values.put(ID_USER, utilizador.getId());
        values.put(TIPO, utilizador.getTipo());
        values.put(DATA_NASCIMENTO, utilizador.getDataNascimento());
        values.put(NUMERO_TELEMOVEL, utilizador.getNumeroTelemovel());
        values.put(PRIMEIRO_NOME, utilizador.getPrimeiroNome());
        values.put(ULTIMO_NOME, utilizador.getUltimoNome());
        values.put(GENERO, utilizador.getGenero());


    }


}
