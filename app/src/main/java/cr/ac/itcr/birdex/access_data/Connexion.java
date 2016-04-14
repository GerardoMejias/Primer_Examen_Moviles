package cr.ac.itcr.birdex.access_data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Gerardo on 30/3/2016.
 */
public class Connexion extends SQLiteOpenHelper {

    private static final int VERSION_BDD = 1;
    private static final String NAME_BDD = "birdex";

    public Connexion(Context context) {
        super(context, NAME_BDD, null, VERSION_BDD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            StringBuilder sql = new StringBuilder();
            //Create
            String sqlCreateTableUsers = "CREATE TABLE IF NOT EXISTS users" +
                    " (nombre char(50), cedula char(11) primary key, clave char(10))"; //id INTEGER PRIMARY KEY AUTOINCREMENT, name CHAR(100))";
            sql.append(sqlCreateTableUsers);
            db.execSQL(sql.toString());

            String sqlCreateTableBirds = "CREATE TABLE IF NOT EXISTS users" +
                    " (genero char(30), especie char(30), nombre char(30), desc char(100))";
            sql.append(sqlCreateTableBirds);
            db.execSQL(sql.toString());

        } catch (Exception error) {
            Log.d("error", error.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            StringBuilder sql = new StringBuilder();

            for(int indiceVersion = oldVersion; indiceVersion < newVersion; indiceVersion++){
                int nextVersion = indiceVersion + 1;
                switch (nextVersion){
                    case 1:
                        String sqlDropUsers = "DROP TABLE IF EXISTS users";
                        sql.append(sqlDropUsers);
                        String sqlDropBirds = "DROP TABLE IF EXISTS birds";
                        sql.append(sqlDropBirds);
                        break;
                    case 2:
                        String sqlCreateTableUsers = "CREATE TABLE IF NOT EXISTS users" +
                                " (nombre char(50), cedula char(11) primary key, clave char(10))";
                        sql.append(sqlCreateTableUsers);
                        String sqlCreateTableBirds = "CREATE TABLE IF NOT EXISTS users" +
                                " (genero char(30), especie char(30) primary key, nombre char(30), desc char(100))";
                        sql.append(sqlCreateTableBirds);
                        break;
                }
            }

            db.execSQL(sql.toString());

        } catch (Exception error){
            Log.d("error", error.getMessage());
        }
    }
}
