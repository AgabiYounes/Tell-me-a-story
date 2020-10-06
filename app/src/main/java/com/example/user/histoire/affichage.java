package com.example.user.histoire;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class affichage extends AppCompatActivity {
    TableLayout T;
    Button B,suiv,prec;
    TextView hist;
    FrameLayout back;
    int c;
    int pos=0,tmp,k=0;
    HistoireManager tmp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);
        //Passage en Full Screen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        T=findViewById(R.id.table);
        B=findViewById(R.id.forme);
        hist=findViewById(R.id.hist);
        suiv=findViewById(R.id.suiv);
        prec=findViewById(R.id.prec);
        //Recuperation de nos information a travers L'intent envoyé
        Intent K=getIntent();
        tmp2=new HistoireManager(this);
        tmp2.open();
        Histoire H1=tmp2.getHistoire(K.getStringExtra("Titre"));
        String history=H1.getHistory();
        int background=H1.getBackground();
        back=findViewById(R.id.back);
        back.setBackgroundResource(background);

        //décomposition de notre histoire en ligne par le separateur ,
        final String[] a;
        a=history.split(",");
        c=0;
        final String[] Tab;
        int A=(int) a.length/7;
        //méthode pr afficher notre Histoire
        tmp=A;
        if((a.length-(A*7))!=0){
            A++;
            Tab=new String[A+1];
            while(A>1){
                for(int I=c;I<c+7;I++){
                    Tab[k]=Tab[k]+a[I];
                }
                c=c+7;
                A--;
                k++;
            }
            int y=(a.length-(tmp*7));
            for(int I=c;I<y;I++) Tab[k] = Tab[k] + a[I];
            tmp++;
        }

        else{
       Tab=new String[A];
        while(A>0){
            for(int I=c;I<c+7;I++){
                Tab[k]=Tab[k]+a[I];
            }
            c=c+7;
            A--;
            k++;
        }}
        hist.setText(Tab[0]);
        //passage a la page suivante lors du click sur la fleche suiv
        suiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos<tmp-1)pos++;
                else Toast.makeText(getApplicationContext(),"Vous etes a la derniere page", Toast.LENGTH_SHORT).show();
                hist.setText(Tab[pos]);



            }
        });
        //passage a la page precedante lors du click sur la fleche prec
        prec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos>0)pos--;
                else Toast.makeText(getApplicationContext(),"Vous etes a la premiere page", Toast.LENGTH_SHORT).show();
                hist.setText(Tab[pos]);


            }
        });


        //Interception du long click sur notre Text
        T.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                //T.setBackgroundResource(R.drawable.bordure1);
                T.setVisibility(View.INVISIBLE);
                B.setVisibility(View.VISIBLE);

                Toast.makeText(getApplicationContext(),"Cliqué sur le bouton en haut pour ré-afficher votre text", Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        //Interception du  click sur notre Bouton
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //T.setBackgroundResource(R.drawable.bordure);
                T.setVisibility(View.VISIBLE);
                B.setVisibility(View.INVISIBLE);
            }
        });
    }
}
