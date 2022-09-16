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

        // Tasa Nominal Anual
        textEditTasaNominalAnual.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Si el editText tasaNominalAnual y el capital no estan vacios puedo hacer el calculo
                if (textEditTasaNominalAnual.getText().length() > 0 && textEditCapitalAInvertir.getText().length() > 0) {
                    Float interesAnual = Float.parseFloat(textEditTasaNominalAnual.getText().toString());
                    Float capital = Float.parseFloat(textEditCapitalAInvertir.getText().toString());
                    textViewInteresesGanados.setText(String.valueOf(interesAnual / 12 * capital));
                }

                /*else {
                    textViewInteresesGanados.setText("");
                }*/
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Tasa Efectiva Anual
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

        // Capital a invertir
        textEditCapitalAInvertir.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textViewCapital.setText(charSequence);

                // Si el editText tasaNominalAnual y el capital no estan vacios puedo hacer el calculo
                if (textEditTasaNominalAnual.getText().length() > 0 && textEditCapitalAInvertir.getText().length() > 0) {
                    System.out.println("Hola");
                    Float interesAnual = Float.parseFloat(textEditTasaNominalAnual.getText().toString());
                    Float capital = Float.parseFloat(textEditCapitalAInvertir.getText().toString());
                    textViewInteresesGanados.setText(String.valueOf(interesAnual / 12 * capital));
                }
                /*else {
                    textViewInteresesGanados.setText("");
                }*/
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