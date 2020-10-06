package com.example.user.histoire;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static java.lang.Integer.parseInt;

/**
 * Created by AmineYahouni on 16/05/2018.
 */

public class HistoireManager {


    private static final String TABLE_NAME = "histoire";
    public static final String KEY_ID_Histoire="id_histoire";
    public static final String KEY_TITRE_Histoire="titre_histoire";
    public static final String KEY_AUTEUR_Histoire="auteur_histoire";
    public static final String KEY_NBRPAGES_Histoire="nbrpages_histoire";
    public static final String KEY_IMAGE_Histoire="image_histoire";
    public static final String KEY_HISTORY_Histoire="history_histoire";
    public static final String KEY_BACKGROUND_Histoire="background_histoire";
    public static final String CREATE_TABLE_Histoire = "CREATE TABLE "+TABLE_NAME+
            " (" +
           // " "+KEY_ID_Histoire+" INTEGER PRIMARY KEY AUTOINCREMENT" +
            "  "+KEY_TITRE_Histoire+" TEXT PRIMARY KEY"+
            " , "+KEY_AUTEUR_Histoire+" TEXT"+
            " , " +KEY_NBRPAGES_Histoire+" TEXT"+
            " , "+KEY_IMAGE_Histoire+" TEXT"+
            " , "+KEY_HISTORY_Histoire+" TEXT"+
            " , "+KEY_BACKGROUND_Histoire+" TEXT"+
            ");";
    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public HistoireManager(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public void addHistoire(Histoire histoire) {
        // Ajout d'un enregistrement dans la table

        if(this.getHistoire(histoire.getTitre())==null){//vérifie l'unicité d'une seule et même histoire
        ContentValues values = new ContentValues(5);

        values.put(KEY_TITRE_Histoire, histoire.getTitre());
        values.put(KEY_AUTEUR_Histoire, histoire.getAuteur());
        values.put(KEY_NBRPAGES_Histoire, histoire.getNbrPages());
        values.put(KEY_IMAGE_Histoire, histoire.getPhoto());
        values.put(KEY_HISTORY_Histoire,histoire.getHistory());
        values.put(KEY_BACKGROUND_Histoire,histoire.getBackground());





        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        db.insert(TABLE_NAME,null,values);}

    }

    public int modHistoire(Histoire histoire) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_TITRE_Histoire, histoire.getTitre());
        values.put(KEY_AUTEUR_Histoire, histoire.getAuteur());
        values.put(KEY_HISTORY_Histoire, histoire.getHistory());
        values.put(KEY_NBRPAGES_Histoire, histoire.getNbrPages());


        //String where = KEY_ID_Histoire+" = ?";
        //String[] whereArgs = {histoire.getId()+""};
        String where = KEY_TITRE_Histoire+" = ?";
        String[] whereArgs = {histoire.getTitre()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supHistoire(Histoire histoire) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

       //String where = KEY_ID_Histoire+" = ?";
        String where = KEY_TITRE_Histoire+" = ?";

//        String[] whereArgs = {histoire.getId()+""};

        String[] whereArgs = {histoire.getTitre()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Histoire getHistoire(String titre) {
        // Retourne l'histoire dont le titre est passé en paramètre

        Histoire a=new Histoire();
        int K=0;

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_TITRE_Histoire+" = "+"'"+titre+"'", null);
        if (c.moveToFirst()) {
            a.setTitre(c.getString(c.getColumnIndex(KEY_TITRE_Histoire)));
            a.setAuteur(c.getString(c.getColumnIndex(KEY_AUTEUR_Histoire)));
            a.setNbrPages(c.getString(c.getColumnIndex(KEY_NBRPAGES_Histoire)));
            a.setPhoto(parseInt(c.getString(c.getColumnIndex(KEY_IMAGE_Histoire))));
            a.setHistory(c.getString(c.getColumnIndex(KEY_HISTORY_Histoire)));
            a.setBackground(parseInt(c.getString(c.getColumnIndex(KEY_BACKGROUND_Histoire))));
            K++;
            c.close();
        }
        if(K==0)a=null;
        return a;
    }

    public Cursor getHistoires() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

} // class Histoiremanager//
