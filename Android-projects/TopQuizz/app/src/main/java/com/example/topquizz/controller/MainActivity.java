package com.example.topquizz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquizz.R;
import com.example.topquizz.model.User;

public class MainActivity extends AppCompatActivity {

    //Déclaration des variables correspondant aux elements graphiques du builder
    private TextView txtBvn;
    private EditText txtFieldNom;
    private Button btnJouer;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Mapping entre les variables et les elements gra^phiques grace a la fonction findViewById
        txtBvn = (TextView) findViewById(R.id.txtViewBvn);
        txtFieldNom = (EditText) findViewById(R.id.txtFieldNom);
        btnJouer = (Button) findViewById(R.id.btnJouer);

        //désactiver bouton avant de taper quoique ce soir
        btnJouer.setEnabled(false);

        //instance de l'utilisateur
        user = new User();

        //se mettre a l'ecoute du texteEdit
        txtFieldNom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //L'utilisateur tape au moins un caractère
                btnJouer.setEnabled(charSequence.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //se mettre a l'ecoute du click sur le bouton
        btnJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //l'utilisateur vient de cliquer

                //enregistrer le prenom de l'utilisateur
                user.setmPrenom(txtFieldNom.getText().toString());
                //on lance l'activité2
                Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                //Permet d'envoyer a l'activité GameActivity l'utilisateur identifié par "UserObject"
                gameActivity.putExtra("UserObject", user);
                //Lancement de l'activité
                startActivity(gameActivity);


            }
        });
    }

}