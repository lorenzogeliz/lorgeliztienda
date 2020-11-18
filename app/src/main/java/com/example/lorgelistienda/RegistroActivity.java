package com.example.lorgelistienda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    private ImageView irAtras;
    private EditText nombreuser, telefono, correo, contraseña;
    private Button btnGuardar;

    private Context contex;



    //creamos la instacia a la autenticacion de firebase
    private FirebaseAuth mAuth;
    //creamos la instacia al a base de datos
    private DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        contex=this;
        // Inicializamos la conexion a Firebase Autenticacion
        mAuth = FirebaseAuth.getInstance();
        // Escrivimos los datos en la Bd Realtime database
        mdatabase = FirebaseDatabase.getInstance().getReference();

        irAtras = findViewById(R.id.btn_atras);
        nombreuser = findViewById(R.id.nombreuser);
        telefono = findViewById(R.id.telefonoUsuario);
        correo = findViewById(R.id.emailUsuario);
        contraseña = findViewById(R.id.passwordUsuario);
        btnGuardar = findViewById(R.id.btnRegistrarusuario);


        irAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombreuser.setError(null);
                telefono.setError(null);
                correo.setError(null);
                contraseña.setError(null);

                String user = nombreuser.getText().toString().trim();
                String tel = telefono.getText().toString().trim();
                String email = correo.getText().toString().trim();
                String pass = contraseña.getText().toString().trim();


                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(tel) || TextUtils.isEmpty(email) ||
                        TextUtils.isEmpty(pass)) {

                    if (TextUtils.isEmpty(user)) {
                        nombreuser.setError("Campo nombre vacio");
                        nombreuser.findFocus();
                        return;

                    }
                    if (TextUtils.isEmpty(tel)) {
                        telefono.setError("Campo telefono vacio");
                        telefono.findFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(email)) {
                        correo.setError("Campo E-mail vacio");
                        correo.findFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(pass)) {
                        contraseña.setError("Campo contraseña vacio");
                        contraseña.findFocus();
                        return;
                    }
                } else {
                    registrarUsuario(user, tel, email, pass);
                }
            }
        });
    }

    private void registrarUsuario(String user, String telf, String coreo, String pass) {

        mAuth.createUserWithEmailAndPassword(coreo, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("nombre", user);
                    map.put("telefono", telf);
                    map.put("email", coreo);
                    //map.put("password", pass);

                    Usuarios usuariosnuevos = new Usuarios(user, telf, coreo/*, pass*/);

                    String id = mAuth.getCurrentUser().getUid();

                    mdatabase.child("usuario").child(id).setValue(usuariosnuevos).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            //si la peticion es exitosa se guarda el usuario y lo manda a iniciar sesion
                            if (task2.isSuccessful()) {

                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(contex);
                                alertDialog.setTitle("Exitoso");
                                alertDialog.setMessage("Su registro fue EXitoso. :)");
                                alertDialog.setCancelable(false);
                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        
                                        startActivity(new Intent(contex, MainActivity.class));
                                        finish();
                                    }
                                }).show();

                            } else {
                                Toast.makeText(contex, "No se pudieron crear los datos", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                } else {
                    Toast.makeText(contex, "No se pudo registrar este usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}