package com.example.topquizz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquizz.R;
import com.example.topquizz.model.Question;
import com.example.topquizz.model.QuestionBank;
import com.example.topquizz.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txtViewQst;
    private Button btnRep1;
    private Button btnRep2;
    private Button btnRep3;
    private Button btnRep4;
    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;
    private int mNumberOfQuestions;
    private int mScoreUser;
    private User u;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        System.out.println("GameActivity::onCreate()");


        this.txtViewQst = (TextView) findViewById(R.id.txtViewQst);
        this.btnRep1 = (Button) findViewById(R.id.btnRep1);
        this.btnRep2 = (Button) findViewById(R.id.btnRep2);
        this.btnRep3 = (Button) findViewById(R.id.btnRep3);
        this.btnRep4 = (Button) findViewById(R.id.btnRep4);

        //Instance de la question bank
        mQuestionBank = this.generateQuestions();
        //Instance du nombre de questions du jeu
        mNumberOfQuestions = 4;
        //Instance du score du joueur en debut de partie
        mScoreUser = 0;

        //Instance de l'utilisateur venant de l'activité principale
        u = (User) getIntent().getSerializableExtra("UserObject");
        u.setmScore(mScoreUser);

        // Use the tag property to 'name' the buttons
        //permet d'assigner un bouton a un indexe de reponse
        btnRep1.setTag(0);
        btnRep2.setTag(1);
        btnRep3.setTag(2);
        btnRep4.setTag(3);

        //La classe GameActivity sera a l'ecoute de l'evenement du click
        btnRep1.setOnClickListener(this);
        btnRep2.setOnClickListener(this);
        btnRep3.setOnClickListener(this);
        btnRep4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);

    }

    public QuestionBank generateQuestions () {
        Question q1 = new Question("Quelle-est la température du soleil ?", Arrays.asList(
                "Un certain degré", "Attention ça brûle les doigts", "le 0 absolu", "je sais pas"), 1);
        Question q2 = new Question("ça va ?", Arrays.asList(
                "Oui", "Non", "Non", "Oui"), 3);
        Question q3 = new Question("Qui est naruto?", Arrays.asList(
                "Un ninja", "Mon père", "Ton père", "Son mère"), 0);
        Question q4 = new Question("Par quelle théorie, Einstein a-t-il pu évaluer la théorie des fragments ?", Arrays.asList(
                "Cette théorie n'existe pas", "Réponse A", "Réponse A", "Réponse A"), 2);

        Log.i("TEST DE MON TAG", "generateQuestions: ");

        return new QuestionBank(Arrays.asList(q1, q2, q3, q4));
    }

    //Affichage d'une question avec les elements graphiques
    private void displayQuestion(final Question question) {
        // Set the text for the question text view and the four buttons
        txtViewQst.setText(question.getmQuestion());
        btnRep1.setText(question.getmChoixListe().get(0));
        btnRep2.setText(question.getmChoixListe().get(1));
        btnRep3.setText(question.getmChoixListe().get(2));
        btnRep4.setText(question.getmChoixListe().get(3));

    }


    @Override
    public void onClick(View view) {

        int reponseIndex = (int) view.getTag();

        //Afficher message bonne ou mauvaise reponse en fonction de la reponse choisie par l'utilisateur
        if (reponseIndex == mCurrentQuestion.getmReponseIndex())
        {
            //toast maketext permet d'afficher un message systeme pendant une courte duree
            Toast.makeText(this, "Bonne réponse", Toast.LENGTH_SHORT).show();
            u.setmScore(mScoreUser++);
        }
        else {
            Toast.makeText(this, "Mauvaise réponse", Toast.LENGTH_SHORT).show();
        }

        //Si la question actuelle est la derniere, fin du jeu, sinon afficher la question suivante
        if (--mNumberOfQuestions == 0) {
            // No question left, end the game
            endgame();
        } else {
            mCurrentQuestion = mQuestionBank.getQuestion();
            displayQuestion(mCurrentQuestion);
        }

    }

    private void endgame() {
        //Ligne 1 : il est nécessaire d'utiliser un objet spécifique pour "construire" la boîte de dialogue. C'est la sous-classe Builder qui s'en charge ;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Ligne 3 : nous définissons le titre de la boîte de dialogue ;
            builder.setTitle("Bien joué! " + u.getmPrenom())
        //Ligne 4 : nous définissions le texte à afficher dans la boîte de dialogue ;
                .setMessage("Votre score est de : " + u.getmScore() + " points.")
                //Ligne 5 : nous définissons le texte du bouton à afficher, et fournissons l'implémentation de l'interface permettant de gérer le clic sur le bouton
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //a méthode finish() permet simplement de dire au système : j'en ai terminé avec l'activité courante, arrêtez-là et ramenez-moi à l'activité précédente.
                        finish();
                    }
                })
            //Ligne 11 : nous demandons à l'instance de Builder de construire la boîte de dialogue avec les paramètres que nous avons prédéfinis ;
            .create()
            //Ligne 12 : nous affichons notre belle boîte de dialogue.
            .show();
    }
}
