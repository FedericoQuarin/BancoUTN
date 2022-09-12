package chort_quarin_reynoso.bancoutn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        // TextView Dias
        TextView txtDias = binding.textViewDias;

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                txtDias.setText(i + " Días");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}