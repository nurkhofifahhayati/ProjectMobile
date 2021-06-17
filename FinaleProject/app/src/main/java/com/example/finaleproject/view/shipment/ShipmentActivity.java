package com.example.finaleproject.view.shipment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finaleproject.R;
import com.example.finaleproject.data.entity.AppDatabase;
import com.example.finaleproject.data.entity.DataShipment;
import com.example.finaleproject.view.activity.ReadDataActivity;
import com.google.android.material.textfield.TextInputEditText;

public class ShipmentActivity extends AppCompatActivity {
    private TextInputEditText tiId, tiName, tiDate, tiWeight, tiOrigin, tiSenderAdd, tiDestination, tiReceiverAdd;
    private RadioButton electronic, apparel, automotive, etc;
    private RadioGroup courierS;
    private RadioButton jne, pos, tiki;
    private Button btnSubmit, btnReset;
    private ImageView imgLogo;
    private String Types, setId, setName, setDate, setWeight, setOrigin, setSenderAdd, setDestination, setReceiverAdd;
    private String Courier;

    AppDatabase appDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment);

        tiId = findViewById(R.id.inputIdShipment);
        tiName = findViewById(R.id.inputSenderName);
        tiDate = findViewById(R.id.inputDate);
        tiWeight = findViewById(R.id.inputWeight);
        tiOrigin = findViewById(R.id.inputOrigin);
        tiSenderAdd = findViewById(R.id.inputSenderAddress);
        tiDestination = findViewById(R.id.inputDestination);
        tiReceiverAdd = findViewById(R.id.inputReceiverAddress);

        electronic = findViewById(R.id.electronicRadio);
        apparel = findViewById(R.id.apparelRadio);
        automotive = findViewById(R.id.automotiveRadio);
        etc = findViewById(R.id.etcRadio);

        imgLogo = findViewById(R.id.imgLogo);
        courierS = findViewById(R.id.typesCourier);
        jne = findViewById(R.id.jneRadio);
        pos = findViewById(R.id.posRadio);
        tiki = findViewById(R.id.tikiRadio);

/*

        if(jne.isChecked()){
            imgLogo.setImageDrawable(getResources().getDrawable(R.drawable.logo_jne));
            Courier = imgLogo.toString();
        } else if (pos.isChecked()){
            imgLogo.setImageDrawable(getResources().getDrawable(R.drawable.logo_pos));
            Courier = imgLogo.toString();
        } else if (tiki.isChecked()){
            imgLogo.setImageDrawable(getResources().getDrawable(R.drawable.logo_tiki));
            Courier = imgLogo.toString();
        }
*/


        btnSubmit = findViewById(R.id.btnSubmit);
        btnReset = findViewById(R.id.btnReset);
        appDatabase = AppDatabase.iniDb(getApplicationContext());
        btnSubmit.setOnClickListener(new View.OnClickListener() { //action perpindahan dari class ini ke class lihat data
            @Override
            public void onClick(View v) {

                if (tiId.getText().toString().isEmpty()) {
                    Toast.makeText(ShipmentActivity.this, "Shipment ID cannot be empty.", Toast.LENGTH_SHORT).show();
                } else {
                    input();
                    Intent submit = new Intent(getApplicationContext(), ReadDataActivity.class);
                    startActivity(submit);
                }

            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetForm();
            }
        });
    }

    private View.OnClickListener jneButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgLogo.setImageResource(R.drawable.logo_jne);
            }
        };
    private View.OnClickListener posButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgLogo.setImageResource(R.drawable.logo_pos);
            }
        };
    private View.OnClickListener tikiButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgLogo.setImageResource(R.drawable.logo_tiki);
            }
    };


    private void resetForm() {
        tiId.setText("");
        tiName.setText("");
        tiDate.setText("");
        tiWeight.setText("");
        tiOrigin.setText("");
        tiSenderAdd.setText("");
        tiDestination.setText("");
        tiReceiverAdd.setText("");
    }

    public void input(){

        if (electronic.isChecked()) {
            Types = "Electronics";
        } else if (apparel.isChecked()) {
            Types = "Apparel";
        } else if (automotive.isChecked()) {
            Types = "Automotive";
        } else if (etc.isChecked()) {
            Types = "Other Types";
        }

        if (jne.isChecked()){
            Courier = "JNE";
        } else if (pos.isChecked()){
            Courier = "POS Indonesia";
        } else if (tiki.isChecked()){
            Courier = "TIKI";
        }

        setId = tiId.getText().toString();
        setName = tiName.getText().toString();
        setDate = tiDate.getText().toString();
        setWeight = tiWeight.getText().toString();
        setOrigin = tiOrigin.getText().toString();
        setSenderAdd = tiSenderAdd.getText().toString();
        setDestination = tiDestination.getText().toString();
        setReceiverAdd = tiReceiverAdd.getText().toString();

        // memanggil kelas data shipment
        final DataShipment dataShipment = new DataShipment();

        dataShipment.setIdShipment(setId);
        dataShipment.setName(setName);
        dataShipment.setDate(setDate);
        dataShipment.setTypes(Types);
        dataShipment.setWeight(setWeight);
        dataShipment.setCourierServices(Courier);
        dataShipment.setOrigin(setOrigin);
        dataShipment.setSenderAddress(setSenderAdd);
        dataShipment.setDestination(setDestination);
        dataShipment.setReceiverAddress(setReceiverAdd);

        //memanggil fungsi insert data(variable appdatabase untuk koneksi ke app database, variabel kelas data shipment untuk masukin data
        //ke databasee terus nanti baru di eksekusi
        new InsertData(appDatabase, dataShipment).execute();
    }

    class InsertData extends AsyncTask<Void, Void, Long>{
        private AppDatabase appDatabase;
        private DataShipment dataShipment;

        public InsertData(AppDatabase appDatabase, DataShipment dataShipment) {
            this.appDatabase = appDatabase;
            this.dataShipment = dataShipment;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return appDatabase.dao().insertData(dataShipment);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            Toast.makeText(getApplicationContext(), "Success Added", Toast.LENGTH_SHORT).show();
        }
    }
}
