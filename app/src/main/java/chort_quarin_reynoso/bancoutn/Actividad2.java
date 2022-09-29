package chort_quarin_reynoso.bancoutn;

import androidx.annotation.NonNull;
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
    TextView textCapitalAInvertir;
    TextView textViewSimuladorPlazoFijo;
    TextView textDias;
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
    Float montoTotalAnual;

    String tipoMoneda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tipoMoneda = getIntent().getExtras().get("moneda").toString(); //Recupera el tipo de moneda elegido por el usuario

        binding = ActivityActividad2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Seekbar
        seekbar = binding.seekBarDias;


        // EditTexts
        textEditTasaNominalAnual = binding.tasaNominalAnualEditText;
        textEditTasaEfectivaAnual = binding.TEAEditText;
        textEditCapitalAInvertir = binding.editTextCapital;

        // TextViews
        textCapitalAInvertir = binding.TxtviewCapital;
        textViewSimuladorPlazoFijo = binding.labelSimuladorPlazoFijo;
        textDias = binding.textViewDias;
        textViewPlazoDias = binding.textViewPlazoDias;
        textViewCapital = binding.textViewCapital;
        textViewInteresesGanados = binding.textViewInteresesGanados;
        textViewMontoTotal = binding.textViewMontoTotal;
        textViewMontoTotalAnual = binding.textViewMontoTotalAnual;
        buttonConfirmar = binding.buttonConfirmar;

        //Texto de capital a invertir
        textCapitalAInvertir.setText("Capital a Invertir (" + tipoMoneda + "): ");

        //Texto de simulador de plazo fijo
        if(tipoMoneda.equals("US$"))
            textViewSimuladorPlazoFijo.setText("Simulador Plazo Fijo en Dólares");

        //Texto de los campos numéricos con sus correspondientes monedas
        textViewCapital.setText(tipoMoneda + " 0");
        textViewInteresesGanados.setText(tipoMoneda + " 0");
        textViewMontoTotal.setText(tipoMoneda + " 0");
        textViewMontoTotalAnual.setText(tipoMoneda + " 0");

        // SeekBar
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                textDias.setText(i * 30 + " Días");
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
            boolean hayAlgunCampoDistintoDeNumero = !textoIngresadoSonNumeros();

            if (hayAlgunCampoVacio || hayAlgunCampoDistintoDeNumero) {
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
                i.putExtra("dias", seekbar.getProgress() * 30);
                setResult(0, i);
                finish();
            }
        });
    }

    private void cambiarInformacionPlazo() {
        // Si el editText tasaNominalAnual y el capital no estan vacios puedo hacer el calculo
        if (TextUtils.isEmpty(textEditTasaNominalAnual.getText()) || TextUtils.isEmpty(textEditCapitalAInvertir.getText()) || !textoIngresadoSonNumeros()) {

                textViewCapital.setText(tipoMoneda + " 0");
                textViewInteresesGanados.setText(tipoMoneda + " 0");
                textViewMontoTotal.setText(tipoMoneda + " 0");
                textViewMontoTotalAnual.setText(tipoMoneda + " 0");

        }

        else {

            if(textoIngresadoSonNumeros()) {
                interesAnual = Float.parseFloat(textEditTasaNominalAnual.getText().toString());
                interesPorMes = interesAnual / 12;
                cantidadMeses = seekbar.getProgress();
                capital = Float.parseFloat(textEditCapitalAInvertir.getText().toString());
                interesesGanados = (interesPorMes) / 100 * capital * cantidadMeses;
                montoTotalAnual = (interesPorMes) / 100 * capital * 12 + capital;

                textViewCapital.setText(tipoMoneda + " " + String.valueOf(capital));
                textViewInteresesGanados.setText(tipoMoneda + " " + String.valueOf(interesesGanados));
                textViewMontoTotal.setText(tipoMoneda + " " + String.valueOf(interesesGanados + capital));
                textViewMontoTotalAnual.setText(tipoMoneda + " " + String.valueOf(montoTotalAnual));
            }
        }
    }

    private boolean textoIngresadoSonNumeros(){
        boolean veredicto = true;

        try {
            Float.parseFloat(textEditCapitalAInvertir.getText().toString());
            Float.parseFloat(textEditTasaNominalAnual.getText().toString());
            Float.parseFloat(textEditTasaEfectivaAnual.getText().toString());
        } catch (NumberFormatException e) {
            veredicto = false;
        }

        return veredicto;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("capital", capital);
        outState.putString("TNA", textEditTasaEfectivaAnual.getText().toString());
        outState.putString("TEA", textEditTasaEfectivaAnual.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}