package com.example.binatech_ds.myapplication;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.sql.Connection;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MainActivity extends AppCompatActivity {
    EditText edtUsuario, edtClave;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario = (EditText)findViewById(R.id.edtUsuario);
        edtClave = (EditText)findViewById(R.id.edtClave);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Agregar_Usuario();
            }
        });
    }

    public Connection conexionDB(){
        Connection conexion = null;
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.8:1433/Taller;instance=SQLEXPRESS;user=sa;password=binarylemon;");
            /*conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.8:1433;instance=SQLEXPRESS;DatabaseName=Taller;user=sa;password=binarylemon;");*/
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }

    public void Agregar_Usuario(){

        try{
            PreparedStatement pst=conexionDB().prepareStatement("INSERT INTO usuarios VALUES(?, ?)");
            pst.setString(1, edtUsuario.getText().toString());
            pst.setString(2, edtClave.getText().toString());
            pst.executeUpdate();

            Toast.makeText(getApplicationContext(), "Registro agregado", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}