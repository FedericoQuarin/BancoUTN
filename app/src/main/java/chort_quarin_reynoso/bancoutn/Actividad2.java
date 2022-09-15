package chort_quarin_reynoso.bancoutn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
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
        TextView textEditCapitalAInvertir = binding.editTextCapital;

        // TextViews
        TextView txtDias = binding.textViewDias;
        TextView textViewCapital = binding.textViewCapital;
        TextView textViewInteresesGanados = binding.textViewInteresesGanados;
        TextView textViewMontoTotal = binding.textViewMontoTotal;
        TextView textViewMontoTotalAnual = binding.textViewMontoTotalAnual;
        Button buttonConfirmar = binding.buttonConfirmar;


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
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        buttonConfirmar.setOnClickListener(view -> {
            Intent i = new Intent();
            String capital = textEditCapitalAInvertir.getText().toString();

            if (TextUtils.isEmpty(capital)) {
                textEditCapitalAInvertir.setError("Campo obligatorio");
            }
            else {
                i.putExtra("capita", capital);
                i.putExtra("dias", seekbar.getProgress());
                setResult(0, i);
                finish();
            }
        });
    }


}