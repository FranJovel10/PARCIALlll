package com.ugb.parcial_programacin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TabHost tbh;
    private Button btn;
    private TextView tempVal;
    private Spinner spn;

    conversores miObj = new conversores();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Codigo de pago de Agua

        tbh = findViewById(R.id.tbhConversor);
        tbh.setup();

        tbh.addTab(tbh.newTabSpec("Valor_Pagar").setContent(R.id.Valor_Pagar).setIndicator("Valor_Pagar", null));
        tbh.addTab(tbh.newTabSpec("Area").setContent(R.id.Area).setIndicator("Area", null));

        btn = findViewById(R.id.btnConvertirValor_Pagar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempVal = findViewById(R.id.txtCantidadValor_Pagar);
                double numero1 = Double.parseDouble(tempVal.getText().toString());

                double Calcular = 0;
                if (numero1 <= 18) {
                    Calcular = 6;
                } else if (numero1 <= 28) {
                    Calcular = (numero1 = 18) * 0.45 + 6;
                } else if (numero1 >= 29) {
                    Calcular = (numero1 = 28) * 0.65 + 4.5 + 6;
                }

                tempVal = findViewById(R.id.lblRespuesta);
                tempVal.setText("Precio: " + Calcular + "$");
            }

        });


        //Conversor area
        btn = findViewById(R.id.btnConvertirArea);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spn = findViewById(R.id.spnDeArea);
                int de = spn.getSelectedItemPosition();
                spn = findViewById(R.id.spnAArea);
                int a = spn.getSelectedItemPosition();

                tempVal = findViewById(R.id.txtCantidadArea);
                double cantidad = Double.parseDouble(tempVal.getText().toString());

                double resp = miObj.convertir(1, de, a, cantidad);
                Toast.makeText(getApplicationContext(), "Respuesta: " + resp, Toast.LENGTH_LONG).show();
            }
        });
    }
}
//cambio de valores
class conversores {
    double[][] valores = {
            {1,},
            {10.7639, 0.698796, 0.836127, 1, 628.8, 6474.9702758, 10000}, //Área
    };

    public double convertir(int opcion, int de, int a, double cantidad) {
        return valores[opcion][a] / valores[opcion][de] * cantidad;
    }
}

//Nombre de los estudiantes
//Francisco Jose Lopez Jovel
//Jose Ignacio Gonzales Alegria
