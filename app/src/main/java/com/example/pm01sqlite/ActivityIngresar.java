package com.example.pm01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pm01sqlite.configuracion.SQLiteConexion;
import com.example.pm01sqlite.configuracion.transaccion;

public class ActivityIngresar extends AppCompatActivity {
    EditText nombre, apellido, edad, correo,direccion;
    private Object transacciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        correo= (EditText) findViewById(R.id.txtcorreo);
        nombre= (EditText) findViewById(R.id.txtnombre);
        apellido= (EditText) findViewById(R.id.txtapellido);
        edad= (EditText) findViewById(R.id.txtedad);
        direccion= (EditText) findViewById(R.id.txtDireccion);

        Button btnagregar = (Button) findViewById(R.id.btnagregar);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarPersona();
            }
        });
    }

    private void AgregarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(this, transaccion.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(transaccion.nombre, nombre.getText().toString());
        valores.put(transaccion.apellido, apellido.getText().toString());
        valores.put(transaccion.edad, edad.getText().toString());
        valores.put(transaccion.correo, correo.getText().toString());
        valores.put(transaccion.direccion, direccion.getText().toString());

        Long resultado = db.insert(transaccion.tablaPersonas, transaccion.id, valores);
        Toast.makeText(getApplicationContext(), "Registro ingresado : Codigo " + resultado.toString(), Toast.LENGTH_LONG).show();

        db.close();

        LimpiarPantalla();

    }

    private void LimpiarPantalla() {
        nombre.setText("");
        apellido.setText("");
        edad.setText("");
        correo.setText("");
        direccion.setText("");
    }


}