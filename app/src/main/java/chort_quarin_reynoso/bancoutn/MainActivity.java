package chort_quarin_reynoso.bancoutn;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import chort_quarin_reynoso.bancoutn.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    final String[] monedas = {"PESOS", "DOLARES"};
    private static final int CODIGO_ACTIVIDAD2 = 11;

    private Button buttonConstituir;

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

        // Boton simular
        EditText nombreEditText = binding.nombreEditText;
        EditText apellidoEditText = binding.apellidoEditText;

        Button botonSimular = binding.buttonSimular;

        botonSimular.setOnClickListener((v) -> {
            Boolean errores = false;
            if (TextUtils.isEmpty(nombreEditText.getText())) {
                nombreEditText.setError("Campo obligatorio");
                errores = true;
            }
            if (TextUtils.isEmpty(apellidoEditText.getText())) {
                apellidoEditText.setError("Campo obligatorio");
                errores = true;
            }
            if (!errores) {
                Intent i = new Intent(MainActivity.this, Actividad2.class);

                i.putExtra("moneda", adapter.getItem(spinner.getSelectedItemPosition()));

                startActivityForResult(i, CODIGO_ACTIVIDAD2);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CODIGO_ACTIVIDAD2:
                buttonConstituir.setEnabled(true);
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
}