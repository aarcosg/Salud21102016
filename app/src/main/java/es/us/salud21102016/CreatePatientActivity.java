package es.us.salud21102016;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.UUID;

public class CreatePatientActivity extends AppCompatActivity {

    private static final String REF_PATIENTS = "patients";

    private EditText mNameText;
    private EditText mAgeText;
    private Button mSaveBtn;
    private View mFormView;
    private View mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inicializar elementos de la interfaz gráfica
        mNameText = (EditText)findViewById(R.id.name_edit_text);
        mAgeText = (EditText)findViewById(R.id.age_edit_text);
        mSaveBtn = (Button) findViewById(R.id.save_btn);
        mProgressView = findViewById(R.id.progress_bar);
        mFormView = findViewById(R.id.create_patient_form);

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePatientToFirebaseDatabase();
            }
        });
    }

    private void savePatientToFirebaseDatabase(){
        // Inicializar FirebaseDatabase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference patientsRef = database.getReference(REF_PATIENTS);

        // Obtener los valores introducidos en el formulario de la interfaz gráfica
        String name = mNameText.getText().toString();
        Long age = Long.valueOf(mAgeText.getText().toString());

        // Crear un identificador único que represente al paciente
        String patientId = UUID.randomUUID().toString();

        // Crear el objeto Patient que se guardará en la base de datos
        Patient patient = new Patient(patientId, name, age, new Date().getTime());

        // Mostrar la barra de progreso y ocultar el formulario
        showProgress(true);

        // Almacenar el paciente
        patientsRef.child(patientId).setValue(patient)
                // En caso de éxito
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Mostrar un mensaje y volver al menú principal
                        showProgress(false);
                        Toast.makeText(CreatePatientActivity.this, "Patient saved", Toast.LENGTH_SHORT).show();
                        CreatePatientActivity.this.finish();
                    }
                })
                // En caso de fallo
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Mostrar mensaje de error
                        showProgress(false);
                        Toast.makeText(CreatePatientActivity.this, "Error creating the new patient. Try again later", Toast.LENGTH_LONG).show();
                    }
                });

    }

    /**
     * Muestra la barra de progreso y oculta el formulario.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
