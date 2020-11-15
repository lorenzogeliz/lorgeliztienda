package com.example.lorgelistienda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class OlvidePasswordActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private Button buttonResetPassword;
    private String email;
    private FirebaseAuth mAuth;
    private ProgressDialog dialog;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvide_password);

        context = this;
        mAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        editTextEmail = findViewById(R.id.resetemail);
        buttonResetPassword = findViewById(R.id.btnResetPassword);

        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextEmail.setError(null);
                String email = editTextEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {

                    if (TextUtils.isEmpty(email)) {
                        editTextEmail.setError("Campo E-mail vacio");
                        editTextEmail.findFocus();
                        return;
                    }

                } else {
                    resetPassword(email);
                }
            }
        });

    }

    private void resetPassword(String email) {

        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    alertDialog.setTitle("Exitoso");
                    alertDialog.setMessage("Por favor revise su correo  para restablecer su contraseña");
                    alertDialog.setCancelable(false);
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            startActivity(new Intent(context, MainActivity.class));
                            finish();
                        }
                    }).show();

                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    alertDialog.setTitle("No se pudo enviar el correo para restablecer su contraseña");
                    alertDialog.setMessage("Por favor, intente nuevamente");
                    alertDialog.setCancelable(false);
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();
                    Toast.makeText(context, "No se pudo enviar el correo para restablecer su contraseña", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
    }
}