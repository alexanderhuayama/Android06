package com.example.giovanni_alexander.listview_demo04;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgregarActivity extends AppCompatActivity {
    private EditText etId, etNombre, etApellido, etDocumento, etEdad;
    private Button btnAgregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        etId = (EditText)findViewById(R.id.etId);
        etNombre = (EditText)findViewById(R.id.etNombre);
        etApellido = (EditText)findViewById(R.id.etApellido);
        etDocumento = (EditText)findViewById(R.id.etDocumento);
        etEdad = (EditText)findViewById(R.id.etEdad);
        btnAgregar = (Button)findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(btnAgregarOnClickListener);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            etId.setText((String)bundle.get("id"));
            etNombre.setText((String)bundle.get("nombre"));
            etApellido.setText((String)bundle.get("apellido"));
            etDocumento.setText((String)bundle.get("documento"));
            etEdad.setText((String)bundle.get("edad"));
            etId.setFocusable(false);
            etId.setBackgroundColor(Color.TRANSPARENT);
            btnAgregar.setText("Actualizar");
        }
    }

    private final View.OnClickListener btnAgregarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtra("id", etId.getText().toString());
            intent.putExtra("nombre", etNombre.getText().toString());
            intent.putExtra("apellido", etApellido.getText().toString());
            intent.putExtra("documento", etDocumento.getText().toString());
            intent.putExtra("edad", etEdad.getText().toString());

            setResult(RESULT_OK, intent);
            finish();
        }
    };
}
