package chort_quarin_reynoso.bancoutn;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Locale;

import chort_quarin_reynoso.bancoutn.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    final String[] monedas = {"PESOS", "DOLARES"};
    private static final int CODIGO_ACTIVIDAD2 = 11;

    private Button buttonConstituir;

    private ActivityMainBinding binding;

    private EditText nombreEditText;
    private EditText apellidoEditText;

    private String capitalInversion;
    private int diasInversion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        buttonConstituir = binding.buttonConstituir;

        // Spinner
        Spinner spinner = binding.monedasSpinner;
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, monedas);
        spinner.setAdapter(adapter);

        // EditTexts
        nombreEditText = binding.nombreEditText;
        apellidoEditText = binding.apellidoEditText;

        // Boton simular
        Button botonSimular = binding.buttonSimular;
        botonSimular.setOnClickListener((v) -> {
            if (camposValidos()) {
                Intent i = new Intent(MainActivity.this, Actividad2.class);

                i.putExtra("moneda", adapter.getItem(spinner.getSelectedItemPosition()));

                startActivityForResult(i, CODIGO_ACTIVIDAD2);
            }
        });



        Button botonConstituir = binding.buttonConstituir;
        botonConstituir.setOnClickListener(view -> {
            if(camposValidos()) {
                AlertDialog dialog = new MaterialAlertDialogBuilder(this)
                        .setTitle("Felicitaciones "
                                + nombreEditText.getText() + " "
                                + apellidoEditText.getText())
                        .setMessage("Tu plazo fijo de "
                                + capitalInversion + " "
                                + adapter.getItem(spinner.getSelectedItemPosition()).toString().toLowerCase(Locale.ROOT)
                                + " por " + diasInversion
                                + " dias ha sido constituido.")
                        .setNeutralButton("PIOLA!", (dialogInterface, i) -> listenerBotonAceptar())
                        .show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CODIGO_ACTIVIDAD2:
                if (data != null) {
                    buttonConstituir.setEnabled(true);
                    capitalInversion = data.getExtras().getString("capital");
                    diasInversion = data.getExtras().getInt("dias");
                }
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("buttonConstituir", buttonConstituir.isEnabled());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        buttonConstituir.setEnabled(savedInstanceState.getBoolean("buttonConstituir"));
    }

    // Devuelve verdadero si los campos son validos, falso en caso contrario
    // Para esta actividad solo se valida que los campos Nombre y Apellido no esten vacios
    protected boolean camposValidos() {
        boolean hayCamposVacios = false;

        if (TextUtils.isEmpty(nombreEditText.getText())) {
            nombreEditText.setError("Campo obligatorio");
            hayCamposVacios = true;
        }
        if (TextUtils.isEmpty(apellidoEditText.getText())) {
            apellidoEditText.setError("Campo obligatorio");
            hayCamposVacios = true;
        }
        return !hayCamposVacios;
    }

    // Metodo utilizado cuando se presiona el boton Aceptar del Dialog
    protected void listenerBotonAceptar() {
        buttonConstituir.setEnabled(false);
        capitalInversion = null;
        diasInversion = 0;
    }
}