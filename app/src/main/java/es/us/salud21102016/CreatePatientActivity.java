package es.us.salud21102016;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        // TODO 2. Inicializar EditText de la edad
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
        // TODO 3. Obtener instancia de Firebase Database

        // Crear un identificador único que represente al paciente
        String patientId = UUID.randomUUID().toString();

        // TODO 4. Obtener los datos del formulario y crear un paciente

        // Mostrar la barra de progreso y ocultar el formulario
        showProgress(true);

        // TODO 5. Almacenar el paciente en la base de datos

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
