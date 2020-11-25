package com.example.lorgelistienda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button register, iniciarSesion;
    private String useremail, password;
    private EditText EditTextuseremail, EditTextpassword;
    private TextView textViewOlvidepass;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;

    private ProgressDialog dialog;
     Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;
        mAuth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        dialog = new ProgressDialog(this);

        register=findViewById(R.id.buttonRegister);
        iniciarSesion=findViewById(R.id.login);
        EditTextuseremail=findViewById(R.id.username);
        EditTextpassword=findViewById(R.id.password);
        textViewOlvidepass=findViewById(R.id.btnOlvidarpasswor);

        //Accion olvidar contraseña
        textViewOlvidepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, OlvidePasswordActivity.class));
            }
        });

        //iniciar secion
        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditTextuseremail.setError(null);
                EditTextpassword.setError(null);

                String user = EditTextuseremail.getText().toString().trim();
                String pass = EditTextpassword.getText().toString().trim();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    if (TextUtils.isEmpty(user)) {
                        EditTextuseremail.setError("Campo E-mail vacio");
                        EditTextuseremail.findFocus();
                        return;

                    } else if (TextUtils.isEmpty(pass)) {
                        EditTextpassword.setError("Campo contraseña vacio");
                        EditTextpassword.findFocus();
                        return;
                    }

                } else {
                    iniciarSesionUser(user,pass);
                }


            }
        });


        //registrar nuevo usuario
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

    }

    private void iniciarSesionUser(String useremail, String password) {

        dialog.setMessage("Espere un momento...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        mAuth.signInWithEmailAndPassword(useremail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    String iduser = task.getResult().getUser().getUid();

                   //getUserInfo();
                    startActivity(new Intent(context,contentActivity.class));
                    dialog.dismiss();
                   // finish();

                } else {
                    dialog.dismiss();
                    Toast.makeText(context, "Verifique los datos o su Conexion ...", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    //verifica si ya inicio secion para no volver a login
    protected void onStart() {

        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            //getUserInfo();
            //Toast.makeText(this, "No cerraste cesion la ultima ve...", Toast.LENGTH_LONG).show();
            startActivity(new Intent(context,contentActivity.class));
        }
    }

}