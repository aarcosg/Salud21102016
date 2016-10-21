package es.us.salud21102016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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
        // Inicializar FirebaseDatabase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference patientsRef = database.getReference(REF_PATIENTS);

        // Crear listener que estará pendiente a los nuevos cambios que se produzcan en los pacientes.
        ValueEventListener patientListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Se ha producido un cambio en los pacientes. Obtenerlo y actualizar la interfaz
                // gráfica con sus datos.
                mProgressBar.setVisibility(View.GONE);

                Patient patient = null;
                for(DataSnapshot patientSnapshot : dataSnapshot.getChildren()){
                    patient = patientSnapshot.getValue(Patient.class);
                }

                if(patient != null){
                    // Actualizar UI
                    mNameTextView.setText(patient.name);
                    mAgeTextView.setText(patient.age.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Se ha producido un error al obtener al paciente. Mostrar un mensaje
                Toast.makeText(ViewPatientActivity.this, "Patient could not be loaded", Toast.LENGTH_LONG).show();
            }
        };

        // Crear consulta para obtener el último paciente insertado
        Query lastPatientQuery = patientsRef
                .orderByChild("timestamp")
                .limitToLast(1);

        // Añadir el listener a la base de datos
        lastPatientQuery.addValueEventListener(patientListener);
    }
}
