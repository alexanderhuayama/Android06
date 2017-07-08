package com.example.giovanni_alexander.listview_demo04;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView lvMain;
    private Button btnCrear;
    private AdaptadorLV adaptadorLV;
    private final int REQ_CODE_ADD = 0;
    private final int REQ_CODE_UPDATE = 1;
    private TextView tvId, tvNombreCompleto, tvDocumento, tvEdad;

    private final View.OnClickListener btnCrearOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AgregarActivity.class);
            startActivityForResult(intent, REQ_CODE_ADD);
        }
    };

    private final AdapterView.OnItemClickListener adapdatorLVOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, AgregarActivity.class);
            tvId = (TextView)view.findViewById(R.id.tvId);
            tvNombreCompleto = (TextView)view.findViewById(R.id.tvNombreCompleto);
            tvDocumento = (TextView)view.findViewById(R.id.tvDocumento);
            tvEdad = (TextView)view.findViewById(R.id.tvEdad);
            String nombreCompleto[] = tvNombreCompleto.getText().toString().split(" ");

            intent.putExtra("id", tvId.getText().toString());
            intent.putExtra("nombre", nombreCompleto[0]);
            intent.putExtra("apellido", nombreCompleto[1]);
            intent.putExtra("documento", tvDocumento.getText().toString());
            intent.putExtra("edad", tvEdad.getText().toString());

            startActivityForResult(intent, REQ_CODE_UPDATE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = (ListView)findViewById(R.id.lvMain);
        btnCrear = (Button)findViewById(R.id.btnCrear);
        adaptadorLV = new AdaptadorLV(this);
        lvMain.setAdapter(adaptadorLV);

        btnCrear.setOnClickListener(btnCrearOnClickListener);
        lvMain.setOnItemClickListener(adapdatorLVOnItemClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_ADD){
            if (resultCode == RESULT_OK){
                Persona persona = new Persona();
                persona.setId(data.getStringExtra("id"));
                persona.setNombre(data.getStringExtra("nombre"));
                persona.setApellido(data.getStringExtra("apellido"));
                persona.setDocumento(data.getStringExtra("documento"));
                persona.setEdad(Integer.parseInt(data.getStringExtra("edad")));
                adaptadorLV.add(persona);
            }
        }

        if (requestCode == REQ_CODE_UPDATE){
            if (resultCode == RESULT_OK){
                for (int i=0; i< lvMain.getCount(); i++){
                    if (adaptadorLV.getItem(i).getId().equals(data.getStringExtra("id"))){
                        adaptadorLV.getItem(i).setNombre(data.getStringExtra("nombre"));
                        adaptadorLV.getItem(i).setApellido(data.getStringExtra("apellido"));
                        adaptadorLV.getItem(i).setDocumento(data.getStringExtra("documento"));
                        adaptadorLV.getItem(i).setEdad(Integer.parseInt(data.getStringExtra("edad")));
                        adaptadorLV.notifyDataSetChanged();
                    }
                }
            }
        }
    }
}
