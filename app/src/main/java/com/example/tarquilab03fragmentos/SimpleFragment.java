package com.example.tarquilab03fragmentos;

import android.content.Context;
import android.os.Bundle;


import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SimpleFragment extends Fragment {


    RadioButton r1,r2;
    private static final int NONE = 2;
    public int mRadioButtonChoice = NONE;
    private static final String CHOICE = "choice";
    private static final int pelicula1 = 0;
    private static final int pelicula2 = 1;
    OnFragmentInteractionListener mListener;

    interface  OnFragmentInteractionListener{

        void onRadioButtonChoice(int choice);
    }

    public static SimpleFragment newInstance(int choice){
        SimpleFragment fragment = new SimpleFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(CHOICE,choice);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;
        }else{
            throw new ClassCastException(context.toString()+getResources().getString(R.string.exception_message));
        }
    }



    public SimpleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_simple, container, false);

        final RadioGroup radioGroupPeliculas = root.findViewById(R.id.radio_group_peliculas);

        if (getArguments().containsKey(CHOICE)){
            mRadioButtonChoice = getArguments().getInt(CHOICE);
            if (mRadioButtonChoice != NONE){
                radioGroupPeliculas.check(radioGroupPeliculas.getChildAt(mRadioButtonChoice).getId());

            }
        }

        radioGroupPeliculas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                View radioButton = radioGroupPeliculas.findViewById(i);
                int index = radioGroupPeliculas.indexOfChild(radioButton);
                TextView tv = root.findViewById(R.id.fragment_header);
                switch (index){
                    case pelicula1:
                        mListener.onRadioButtonChoice(pelicula1);
                        tv.setText(R.string.pelicula1);

                        break;
                    case pelicula2:
                        mListener.onRadioButtonChoice(pelicula2);
                        tv.setText(R.string.pelicula2);
                        break;
                    default:
                        mRadioButtonChoice=NONE;
                        mListener.onRadioButtonChoice(NONE);
                        tv.setText(R.string.pelicula3);
                        break;
                }

            }
        });


        return root;
    }
}