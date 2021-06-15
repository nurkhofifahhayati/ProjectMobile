package com.example.finaleproject.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finaleproject.R;
import com.example.finaleproject.data.entity.AppDatabase;
import com.example.finaleproject.data.entity.DataShipment;
import com.example.finaleproject.view.fragment.ListFragment;
import com.example.finaleproject.view.shipment.ShipmentAdapter;
import com.example.finaleproject.view.shipment.ShipmentContact;
import com.example.finaleproject.view.shipment.ShipmentPresenter;
import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Type;

public class EditDataActivity extends AppCompatActivity implements ShipmentContact.view {
    private AppDatabase appDatabase;
    private ShipmentPresenter shipmentPresenter;
    private ShipmentAdapter shipmentAdapter;

    private TextInputEditText etName, etDate, etWeight, etOrigin, etSenderAdd, etDestination, etReceiverAdd;
    private RadioGroup typesGoods;
    private RadioButton electronic, apparel, automotive, etc;
    private Button btnSubmit;
    private String id, Types, setName, setDate, setWeight, setOrigin, setSenderAdd, setDestination, setReceiverAdd;

    private boolean edit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        etName = findViewById(R.id.inputSenderName);
        etDate = findViewById(R.id.inputDate);
        typesGoods = findViewById(R.id.typesShipment);
        electronic = findViewById(R.id.electronicRadio);
        apparel = findViewById(R.id.apparelRadio);
        automotive = findViewById(R.id.automotiveRadio);
        etc = findViewById(R.id.etcRadio);
        etWeight = findViewById(R.id.inputWeight);
        etOrigin = findViewById(R.id.inputOrigin);
        etSenderAdd = findViewById(R.id.inputSenderAddress);
        etDestination = findViewById(R.id.inputDestination);
        etReceiverAdd = findViewById(R.id.inputReceiverAddress);

        btnSubmit = findViewById(R.id.btnSubmit);
        shipmentPresenter = new ShipmentPresenter(this);
        appDatabase = AppDatabase.iniDb(getApplicationContext());

        setName = getIntent().getStringExtra("name");
        setDate = getIntent().getStringExtra("date");
        typesGoods = (RadioGroup) getIntent().getCharSequenceExtra("types");
        setWeight = getIntent().getStringExtra("weight");
        setOrigin = getIntent().getStringExtra("origin");
        setSenderAdd = getIntent().getStringExtra("senderAddress");
        setDestination = getIntent().getStringExtra("destination");
        setReceiverAdd = getIntent().getStringExtra("receiverAddress");

        id = getIntent().getStringExtra("idShipment");

        etName.setText(setName);
        etDate.setText(setDate);
        etWeight.setText(setWeight);
        etOrigin.setText(setOrigin);
        etSenderAdd.setText(setSenderAdd);
        etDestination.setText(setDestination);
        etReceiverAdd.setText(setReceiverAdd);

        btnSubmit.setOnClickListener(this);

    }

    @Override
    public void resetForm() {
        etName.setText("");
        etDate.setText("");
        etWeight.setText("");
        etOrigin.setText("");
        etSenderAdd.setText("");
        etDestination.setText("");
        etReceiverAdd.setText("");
        btnSubmit.setText("Submit");
    }

    @Override
    public void successAdd() {
        Toast.makeText(getApplicationContext(), "Successed", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), ListFragment.class));
    }

    @Override
    public void editData(DataShipment item) {
        etName.setText(item.getName());
        etDate.setText(item.getDate());
        switch (item.getTypes()){
            case "Electronics":
                electronic.setChecked(true);
                Types = "Electronics";
                break;
            case "Apparel":
                apparel.setChecked(true);
                Types = "Apparel";
                break;
            case "Automotive":
                automotive.setChecked(true);
                break;
            case "Etc":
                etc.setChecked(true);
                break;
        }
        etWeight.setText(item.getWeight());
        etOrigin.setText(item.getOrigin());
        etSenderAdd.setText(item.getSenderAddress());
        etDestination.setText(item.getDestination());
        etReceiverAdd.setText(item.getReceiverAddress());

        edit = true;
        btnSubmit.setText("Update");
    }

    @Override
    public void onClick(View v) {
        int selectedId = typesGoods.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        String typesGoods = radioButton.getText().toString();

        String SenderName, Date, Type, Weight, Origin, SenderAdd, Destination, ReceiverAdd;
        SenderName = etName.getText().toString();
        Date = etDate.getText().toString();
        Type = 
        Weight = etWeight.getText().toString();
        Origin = etOrigin.getText().toString();
        SenderAdd = etSenderAdd.getText().toString();
        Destination = etDestination.getText().toString();
        ReceiverAdd = etReceiverAdd.getText().toString();
        if(v == btnSubmit){
            if(SenderName.equals("") || Date.equals("") || Types.equals("") || Weight.equals("") || Origin.equals("")
            || SenderAdd.equals("") || Destination.equals("") || ReceiverAdd.equals("")) {
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
            } else {
                shipmentPresenter.editData(SenderName, Date, Weight, Origin, SenderAdd, Destination, ReceiverAdd, id, appDatabase);
            }
        }
    }
}
