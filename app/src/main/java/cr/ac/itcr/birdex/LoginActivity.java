package cr.ac.itcr.birdex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import cr.ac.itcr.birdex.access_data.Connexion;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText txtEmail;
    private EditText txtPassword;
    private CheckBox alwaysConnect;

    //private SQLiteDatabase db;
    //public static final Connexion connexion = new Connexion(db);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SharedPreferences preferencias = getSharedPreferences("Birdex", Context.MODE_PRIVATE);
        if(preferencias.getBoolean("always_connect",false))
        {
            Intent i = new Intent(getApplicationContext(),DashboardActivity.class);;
            startActivity(i);
            return;
        }

        btnLogin =
                (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                txtEmail =
                        (EditText) findViewById(R.id.txtEmail);

                txtPassword =
                        (EditText) findViewById(R.id.txtPassword);

                alwaysConnect =
                        (CheckBox) findViewById(R.id.chkAlwaysConnect);


                if(alwaysConnect.isChecked()) {
                    SharedPreferences preferencias = getSharedPreferences("Birdex", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencias.edit();

                    editor.putString("email", txtEmail.getText().toString());
                    editor.putString("password", txtPassword.getText().toString());
                    editor.putBoolean("always_connect",true);
                    editor.commit();
                }
                else{
                    SharedPreferences preferencias = getSharedPreferences("Birdex", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencias.edit();

                    editor.putString("email", "");
                    editor.putString("password", "");
                    editor.putBoolean("always_connect",false);
                    editor.commit();
                }




               //Intent i = new Intent(getApplicationContext(),DashboardActivity.class);;
               //startActivity(i);


               if(txtEmail.getText().toString().equals("user")
                        && txtPassword.getText().toString().equals("123"))
                {
                    Intent i = new Intent(getApplicationContext(),DashboardActivity.class);;
                    startActivity(i);
                }
                else {
                    Toast mensajeError = Toast.makeText(getApplicationContext(), "Usuario inválido",
                            Toast.LENGTH_SHORT);
                    mensajeError.show();
                }
            }
        });
    }
}
