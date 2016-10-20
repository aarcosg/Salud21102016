package es.us.salud21102016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar elementos de la interfaz gráfica
        Button createPatientButton = (Button) findViewById(R.id.create_btn);
        Button viewPatientButton = (Button) findViewById(R.id.view_btn);

        // Añadir evento onClick a ambos botones
        createPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCreatePatientActivity();
            }
        });

        viewPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToViewPatientActivity();
            }
        });
    }

    private void goToCreatePatientActivity(){

    }

    private void goToViewPatientActivity(){

    }
}
