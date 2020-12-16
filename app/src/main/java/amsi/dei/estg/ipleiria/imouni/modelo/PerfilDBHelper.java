package amsi.dei.estg.ipleiria.imouni.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PerfilDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="ImoUniBD";
    private static final int DB_VERSION=1;

    private final SQLiteDatabase db;

    public PerfilDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.db=getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
        String sqlCreateTablePerfil = "CREATE TABLE "+TABLE_PERFIL+" ("+
                ID_LIVRO+" INTEGER PRIMARY KEY, "+
                TITULO_LIVRO+" TEXT NOT NULL, "+
                SERIE_LIVRO+" TEXT NOT NULL, "+
                AUTOR_LIVRO+" TEXT NOT NULL, "+
                ANO_LIVRO+" INTEGER NOT NULL, "+
                CAPA_LIVRO +" TEXT );";
*/
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
