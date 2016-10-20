package es.us.salud21102016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ViewPatientActivity extends AppCompatActivity {

    private static final String REF_PATIENTS = "patients";

    private TextView mNameTextView;
    private TextView mAgeTextView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);

        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNameTextView = (TextView) findViewById(R.id.name_text_view);
        mAgeTextView = (TextView) findViewById(R.id.age_text_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);

        loadPatientFromFirebaseDatabase();
    }

    private void loadPatientFromFirebaseDatabase(){
        // TODO 6. Obtener instancia de Firebase Database

        // TODO 7. Crear listener que se lanzará cuando haya cambios en los datos de los pacientes

        ValueEventListener patientListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Se ha producido un cambio en los pacientes. Obtenerlo y actualizar la interfaz
                // gráfica con sus datos.

                mProgressBar.setVisibility(View.GONE);

                // TODO 7.1 Obtener paciente y actualizar UI
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Se ha producido un error al obtener al paciente. Mostrar un mensaje
                // TODO 7.2 Mostrar mensaje de error con un Toast
            }
        };

        /* TODO 8 Crear objeto de tipo Query que consulte la base de datos
        - Crear consulta para obtener el último paciente insertado.
        - La consulta se debe realizar sobre la base de datos de pacientes.
        - Los pacientes devueltos tienen que estar ordenados por "timestamp".
        - Solo hay que devolver un único paciente.
         */

        // TODO 9 Añadir el listener que se ha creado anteriormente al objeto Query anterior
    }
}
