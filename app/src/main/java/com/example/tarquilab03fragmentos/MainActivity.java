package com.example.tarquilab03fragmentos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements SimpleFragment.OnFragmentInteractionListener {


    private boolean isFragmentDisplayed = false;
    static final String FRAGMENT_STATE = "state of Fragment";
    private int mRadioButtonChoice = 2;
    ImageButton imagen1, imagen2;
    Button resultado;
    int voto1=0, voto2=0;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState!=null){
            isFragmentDisplayed = savedInstanceState.getBoolean(FRAGMENT_STATE);
            if (isFragmentDisplayed){

                mButton.setText(R.string.close);
            }
        }
        mButton = (Button)findViewById(R.id.open_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isFragmentDisplayed){
                    displayNewFragment();

                }else {
                    closeFragment();
                }
            }
        });
    }

    public void displayNewFragment(){
        SimpleFragment simpleFragment = SimpleFragment.newInstance(mRadioButtonChoice);
        //TODO: Obtener el FragmentManager e iniciar la transacción
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //TODO: Agregar el Fragment
        fragmentTransaction.add(R.id.fragment_container,simpleFragment).addToBackStack(null).commit();
        mButton.setText(R.string.close);
        isFragmentDisplayed = true;
    }

    public void closeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        SimpleFragment simpleFragment = (SimpleFragment)fragmentManager.findFragmentById(R.id.fragment_container);
        if (simpleFragment!=null){
            fragmentManager.beginTransaction().remove(simpleFragment).commit();
            mButton.setText(R.string.open);
            isFragmentDisplayed=false;
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putBoolean(FRAGMENT_STATE,isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRadioButtonChoice(int choice) {
        mRadioButtonChoice = choice;

        //Toast.makeText(this,"Vota por la pelicula tocando "+Integer.toString(choice)+" la Imagen",Toast.LENGTH_SHORT).show();
        imagen1 = findViewById(R.id.img1);
        imagen2 = findViewById(R.id.img2);
        resultado = findViewById(R.id.open_votacion);

        if (choice==0)
        {
            voto1 = voto1 + 1;
            Toast.makeText(getApplicationContext(),"Suma "+voto1+" para PARASITE", Toast.LENGTH_SHORT).show();
        }
        if (choice==1)
        {
            voto2 = voto2 + 1;
            Toast.makeText(getApplicationContext(),"Suma  "+voto2+" para SCODYDOO", Toast.LENGTH_SHORT).show();
        }


    }

    public void Calcular(View view)
    {
        if(voto1>voto2)
        {
            Toast.makeText(getApplicationContext(),"PARASITE esta ganando  \ncon "+voto1 + " votos", Toast.LENGTH_SHORT).show();
        }
        if(voto1<voto2)
        {
            Toast.makeText(getApplicationContext(),"SCOBYDOO esta ganando \ncon "+voto2+" votos", Toast.LENGTH_SHORT).show();
        }


    }
}