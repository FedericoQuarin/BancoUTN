package chort_quarin_reynoso.bancoutn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.SeekBar;
import android.widget.TextView;

import chort_quarin_reynoso.bancoutn.databinding.ActivityActividad2Binding;

public class Actividad2 extends AppCompatActivity {

    private ActivityActividad2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityActividad2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Seekbar
        SeekBar seekbar = binding.seekBarDias;


        // EditTexts
        TextView textEditTasaNominalAnual = binding.TSAEditText;
        TextView textEditTasaEfectivaAnual = binding.TEAEditText;
        TextView textEditCapitalAInvertir = binding.editTextTextCapital;

        // TextViews
        TextView txtDias = binding.textViewDias;
        TextView textViewCapital = binding.textViewCapital;
        TextView textViewInteresesGanados = binding.textViewInteresesGanados;
        TextView textViewMontoTotal = binding.textViewMontoTotal;
        TextView textViewMontoTotalAnual = binding.textViewMontoTotalAnual;


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                txtDias.setText(i * 30 + " Días");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textEditTasaNominalAnual.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Claramente no anda porque no siempre tiene todos los valores seteados y tira nullpointerexception,
//                Integer interesAnual = Integer.parseInt(textEditTasaEfectivaAnual.getText().toString());
//                Integer capital = Integer.parseInt(textEditCapitalAInvertir.getText().toString());
//                textViewInteresesGanados.setText(interesAnual / 12 * capital);

                // Si el editText tasaNominalAnual y el capital no estan vacios puedo hacer el calculo
                System.out.println(textEditTasaNominalAnual.getText().length());
                System.out.println(textEditCapitalAInvertir.getText().length());
                if (textEditTasaNominalAnual.getText().length() > 0 && textEditCapitalAInvertir.getText().length() > 0) {
                    System.out.println("Hola");
                    Integer interesAnual = Integer.parseInt(textEditTasaEfectivaAnual.getText().toString());
                    Integer capital = Integer.parseInt(textEditCapitalAInvertir.getText().toString());
                    textViewInteresesGanados.setText(interesAnual / 12 * capital);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textEditTasaEfectivaAnual.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textEditCapitalAInvertir.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textViewCapital.setText(charSequence);
                //textViewInteresesGanados.setText();

                // Si el editText tasaNominalAnual y el capital no estan vacios puedo hacer el calculo
                System.out.println(textEditTasaNominalAnual.getText().length());
                System.out.println(textEditCapitalAInvertir.getText().length());
                if (textEditTasaNominalAnual.getText().length() > 0 && textEditCapitalAInvertir.getText().length() > 0) {
                    System.out.println("Hola");
                    Integer interesAnual = Integer.parseInt(textEditTasaEfectivaAnual.getText().toString());
                    Integer capital = Integer.parseInt(textEditCapitalAInvertir.getText().toString());
                    textViewInteresesGanados.setText(interesAnual / 12 * capital);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}