package com.example.einzelprojekt_se2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TextChange {

    private Button buttonServerAnfrage;
    private Button buttonBerechnungCommonDivisor;

    private TextView txtOut;
    private EditText txtIn;

    private TCP connection;

    public void updateText(String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtOut.setText(text);
            }
        });
    }

    private void addEvtHandler(Button btn, View.OnClickListener listener){
        btn.setOnClickListener(listener);
    }

    private View.OnClickListener btnNetworkListener = new View.OnClickListener() {
        public void onClick(View v) {
            String matrikelnummer = txtIn.getText().toString();
            connection.setPayload(matrikelnummer);
            Thread thread = new Thread(connection);
            thread.start();
        }
    };

    private View.OnClickListener btnCalculateListener = new View.OnClickListener() {
        public void onClick(View v) {
            DivisorInfo divisor = CommonDivisor.getCommonDivisor(Integer.parseInt(txtIn.getText().toString()));
            txtOut.setText("Divisor:"+divisor.getDivisor()+" on Idx1:"+divisor.getIndex1()+" on Idx2:"+divisor.getIndex2());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtOut = (TextView)findViewById(R.id.textViewOutput);
        txtIn = (EditText)findViewById(R.id.editTextMatrikelnummer);

        connection = new TCP("se2-isys.aau.at",53212,this);

        buttonServerAnfrage = (Button)findViewById(R.id.buttonServerAnfrage);
        addEvtHandler(buttonServerAnfrage, btnNetworkListener);

        buttonBerechnungCommonDivisor = (Button)findViewById(R.id.buttonBerechnungCommonDivisor);
        addEvtHandler(buttonBerechnungCommonDivisor, btnCalculateListener);

    }
}