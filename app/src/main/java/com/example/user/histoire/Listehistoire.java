package com.example.user.histoire;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;



public class Listehistoire extends ListActivity {
    ListView Listvi;
    HistoireManager tmp;
    Cursor C;
    HistoireAdapter adapter;
    Histoire H1,H2,H3,H4;
    private HistoireAdapter histoireAdapter;
    public String KEY_ID_Histoire = "id_histoire";
    public String KEY_TITRE_Histoire = "titre_histoire";
    public String KEY_AUTEUR_Histoire = "auteur_histoire";
    public String KEY_NBRPAGES_Histoire = "nbrpages_histoire";
    public String KEY_IMAGE_Histoire = "image_histoire";
    public String KEY_HISTORY_Histoire="history_histoire";
    public String KEY_BACKGROUND_Histoire="background_histoire";


    //Creation d'un Menu Contextuel(en plus)
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option,menu);
    }
    //Creation d'un Menu d'option (en plus)
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.option, menu);
        return true;
    }
    //interception du long click (en plus je n'ai definie aucune action a suivre)
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.itemsave:
            case R.id.itemhelp:
            case R.id.itemremove:
            default: return super.onOptionsItemSelected(item);


        }
    }
    //Interception de click sur une histoire
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Histoire histoire = (Histoire) adapter.getItem(position);
        Intent A=new Intent(Listehistoire.this,affichage.class);
        A.putExtra("Titre",histoire.getTitre());
        startActivity(A);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listehistoire);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Listvi = (ListView) findViewById(android.R.id.list);

        List<Histoire> histoir = new ArrayList<Histoire>();
        tmp = new HistoireManager(this);
        tmp.open();
        H1=new Histoire("cocorito","Bruno Heitz","3",R.drawable.cocorico,"Cocorico !\n , Impossible de fermer l'œil…\nCocorico !\n , …depuis que j'ai avalé ce maudit coq !\n , Cocorico !\n , Toc Toc\n , Tiens, qu'est-ce qu'il y a encore ?\n , - Ah ! C'est toi voisin.\n , - Dis donc, ça t'embêterait de faire taire ton coq ? Je ne peux pas dormir , - Je n'y arrive pas !\n" +
                "- Tu devrais manger une poule.\n" +
                ",Ça occupera ton coq, il fera moins de bruit.\n" +
                ",- Bonne idée. Je vais faire ça dès demain matin. »\n" +
                ",Cot\n" +
                ",Poink !\n" +
                ",« Ah ! Tu tombes bien. Je commençais à m'ennuyer dans ce ventre !\n" +
                ",Le seul moyen de nous sortir de là…\n" +
                ",Cot ?\n" +
                ",C'est de faire le plus de bruit possible ! »\n" +
                ",Cot, cot, cot\n" +
                ",Cocorico ! Cocorico !\n" +
                ",Cot, cot, cot\n" +
                ",Cocorico",R.drawable.back_cocorico);
        H2=new Histoire("Le Corbeau et le Renard","Jean de La Fontaine","4",R.drawable.lecorbeau,"Maître Corbeau, sur un arbre perché\n" +
                ",Tenait en son bec un fromage.\n" +
                ", Maître Renard, par l'odeur alléché\n" +
                ", Lui tint à peu près ce langage :\n" +
                ", Et bonjour, Monsieur du Corbeau.\n" +
                ",Que vous êtes joli ! que vous me semblez beau !\n" +
                ",Sans mentir, si votre ramage\n" +
                ",Se rapporte à votre plumage\n" +
                ",Vous êtes le Phénix des hôtes de ces bois.\n" +
                ",À ces mots, le Corbeau ne se sent pas de joie ;\n" +
                ",Et pour montrer sa belle voix\n" +
                ",Il ouvre un large bec, laisse tomber sa proie.\n" +
                ",Le Renard s'en saisit, et dit : Mon bon Monsieur\n" +
                ",Apprenez que tout flatteur\n" +
                ",Vit aux dépens de celui qui l'écoute.\n" +
                ",Cette leçon vaut bien un fromage, sans doute.\n" +
                ",Le Corbeau honteux et confus\n" +
                ",Jura, mais un peu tard, qu'on ne l'y prendrait plus.",R.drawable.back_corbeau);
        H3=new Histoire("Jojo collectionneur","Bruno Heitz","3",R.drawable.jojo,"Jojo est un grand collectionneur.\n" +
                ",Ce que les enfants de son âge trimbalent dans leurs poches , \n les billes , \n les jouets trouvés dans les œufs-surprises , \nles petites voitures…\n" +
                ",lui il le range soigneusement.\n" +
                ",Il collectionne :\n" +
                ",les sucres (enveloppés)…\n" +
                ",les boîtes d'allumettes (vides)…\n" +
                ",les fossiles…\n" +
                ",les timbres…\n" +
                ",les cartes postales…\n" +
                ",les boules avec de la neige dedans…\n" +
                ",les savonnettes…\n" +
                ",les boîtes de camembert (vides)…",R.drawable.back_jojo);
        H4=new Histoire("La cigale Et la Fourmie","Jean de La Fontaine","4",R.drawable.fourmie,"La Cigale, ayant chanté\n" +
                ",Tout l'Été\n" +
                ",Se trouva fort dépourvue\n" +
                ",Quand la bise fut venue.\n" +
                ",Pas un seul petit morceau\n" +
                ",De mouche ou de vermisseau.\n" +
                ",Elle alla crier famine\n" +
                ",Chez la Fourmi sa voisine\n" +
                ",La priant de lui prêter\n" +
                ",Quelque grain pour subsister\n" +
                ",Jusqu'à la saison nouvelle.\n" +
                ",Je vous paierai, lui dit-elle\n" +
                ",Avant l'Oût, foi d'animal\n" +
                ",Intérêt et principal.\n" +
                ",La Fourmi n'est pas prêteuse \n" +
                ",C'est là son moindre défaut.\n" +
                ",« Que faisiez-vous au temps chaud ?\n" +
                ",Dit-elle à cette emprunteuse.\n" +
                ",— Nuit et jour à tout venant\n" +
                ",Je chantais ne vous déplaise.\n" +
                ",— Vous chantiez ? j'en suis fort aise.\n" +
                ",Eh bien !dansez maintenant. »",R.drawable.back_fourmie);
        registerForContextMenu(Listvi);

        tmp.addHistoire(H1);
        tmp.addHistoire(H2);
        tmp.addHistoire(H3);
        tmp.addHistoire(H4);

        C = tmp.getHistoires();

        if (C.moveToFirst()) {
            do {
                histoir.add(new Histoire(C.getString(C.getColumnIndex(KEY_TITRE_Histoire)),C.getString(C.getColumnIndex(KEY_AUTEUR_Histoire)),C.getString(C.getColumnIndex(KEY_NBRPAGES_Histoire)),parseInt(C.getString(C.getColumnIndex(KEY_IMAGE_Histoire))),C.getString(C.getColumnIndex(KEY_HISTORY_Histoire)),parseInt(C.getString(C.getColumnIndex(KEY_BACKGROUND_Histoire)))));
            }while (C.moveToNext());
        }

        adapter = new HistoireAdapter(Listehistoire.this, histoir);
        Listvi.setAdapter(adapter);

    }



}





