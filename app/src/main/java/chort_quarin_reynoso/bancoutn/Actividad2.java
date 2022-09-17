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

    // Seekbar
    SeekBar seekbar;

    // EditTexts
    TextView textEditTasaNominalAnual;
    TextView textEditTasaEfectivaAnual;
    TextView textEditCapitalAInvertir;

    // TextViews
    TextView txtDias;
    TextView textViewPlazoDias;
    TextView textViewCapital;
    TextView textViewInteresesGanados;
    TextView textViewMontoTotal;
    TextView textViewMontoTotalAnual;
    Button buttonConfirmar;

    Float interesAnual;
    Float interesPorMes;
    Integer cantidadMeses;
    Float capital;
    Float interesesGanados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityActividad2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Seekbar
        seekbar = binding.seekBarDias;


        // EditTexts
        textEditTasaNominalAnual = binding.tasaNominalAnualEditText;
        textEditTasaEfectivaAnual = binding.TEAEditText;
        textEditCapitalAInvertir = binding.editTextCapital;

        // TextViews
        txtDias = binding.textViewDias;
        textViewPlazoDias = binding.textViewPlazoDias;
        textViewCapital = binding.textViewCapital;
        textViewInteresesGanados = binding.textViewInteresesGanados;
        textViewMontoTotal = binding.textViewMontoTotal;
        textViewMontoTotalAnual = binding.textViewMontoTotalAnual;
        buttonConfirmar = binding.buttonConfirmar;

        // SeekBar
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                txtDias.setText(i * 30 + " Días");
                if (i == 1) {
                    textViewPlazoDias.setText(i * 30 + " Días / " + i + " Mes" );
                } else {
                    textViewPlazoDias.setText(i * 30 + " Días / " + i + " Meses" );
                }


                cambiarInformacionPlazo();
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
               cambiarInformacionPlazo();
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
                cambiarInformacionPlazo();
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

                cambiarInformacionPlazo();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        buttonConfirmar.setOnClickListener(view -> {
            Intent i = new Intent();
            String capital = textEditCapitalAInvertir.getText().toString();
            String TasaNominalAnual = textEditTasaNominalAnual.getText().toString();
            String TasaEfectivaAnual = textEditTasaEfectivaAnual.getText().toString();

            boolean hayAlgunCampoVacio = TextUtils.isEmpty(capital) || TextUtils.isEmpty(TasaNominalAnual) || TextUtils.isEmpty(TasaEfectivaAnual);

            if (hayAlgunCampoVacio) {
                if (TextUtils.isEmpty(capital) ) {
                    textEditCapitalAInvertir.setError("Campo obligatorio");
                }
                if (TextUtils.isEmpty(TasaNominalAnual) ) {
                    textEditTasaNominalAnual.setError("Campo obligatorio");
                }
                if (TextUtils.isEmpty(TasaEfectivaAnual) ) {
                    textEditTasaEfectivaAnual.setError("Campo obligatorio");
                }
            }
            else {
                i.putExtra("capital", capital);
                i.putExtra("dias", seekbar.getProgress());
                setResult(0, i);
                finish();
            }
        });
    }

    private void cambiarInformacionPlazo() {
        // Si el editText tasaNominalAnual y el capital no estan vacios puedo hacer el calculo
        if (TextUtils.isEmpty(textEditTasaNominalAnual.getText()) || TextUtils.isEmpty(textEditCapitalAInvertir.getText())) {
            textViewCapital.setText("0");
            textViewInteresesGanados.setText("0");
            textViewMontoTotal.setText("0");
        }

        else {
            interesAnual = Float.parseFloat(textEditTasaNominalAnual.getText().toString());
            interesPorMes = interesAnual/12;
            cantidadMeses = seekbar.getProgress();
            capital = Float.parseFloat(textEditCapitalAInvertir.getText().toString());
            interesesGanados = (interesPorMes) /100  * capital * cantidadMeses;

            textViewInteresesGanados.setText(String.valueOf(interesesGanados));
            textViewMontoTotal.setText(String.valueOf(interesesGanados + capital));
        }
    }
}