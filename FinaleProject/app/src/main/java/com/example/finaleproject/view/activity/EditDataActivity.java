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

    private TextInputEditText tiName, tiDate, tiWeight, tiOrigin, tiSenderAdd, tiDestination, tiReceiverAdd;
    private RadioGroup typesGoods;
    private RadioButton electronic, apparel, automotive, etc;
    private Button btnSubmit;
    private String Types, setName, setDate, setWeight, setOrigin, setSenderAdd, setDestination, setReceiverAdd;

    private boolean edit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        tiName = findViewById(R.id.inputSenderName);
        tiDate = findViewById(R.id.inputDate);
        typesGoods = findViewById(R.id.typesShipment);
        electronic = findViewById(R.id.electronicRadio);
        apparel = findViewById(R.id.apparelRadio);
        automotive = findViewById(R.id.automotiveRadio);
        etc = findViewById(R.id.etcRadio);
        tiWeight = findViewById(R.id.inputWeight);
        tiOrigin = findViewById(R.id.inputOrigin);
        tiSenderAdd = findViewById(R.id.inputSenderAddress);
        tiDestination = findViewById(R.id.inputDestination);
        tiReceiverAdd = findViewById(R.id.inputReceiverAddress);

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

        tiName.setText(setName);
        tiDate.setText(setDate);
        tiWeight.setText(setWeight);
        tiOrigin.setText(setOrigin);
        tiSenderAdd.setText(setSenderAdd);
        tiDestination.setText(setDestination);
        tiReceiverAdd.setText(setReceiverAdd);

        btnSubmit.setOnClickListener(this);

    }

    @Override
    public void resetForm() {
        tiName.setText("");
        tiDate.setText("");
        tiWeight.setText("");
        tiOrigin.setText("");
        tiSenderAdd.setText("");
        tiDestination.setText("");
        tiReceiverAdd.setText("");
        btnSubmit.setText("Submit");
    }

    @Override
    public void successAdd() {
        Toast.makeText(getApplicationContext(), "Successed", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), ListFragment.class));
    }

    @Override
    public void editData(DataShipment item) {
        tiName.setText(item.getName());
        tiDate.setText(item.getDate());
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
        tiWeight.setText(item.getWeight());
        tiOrigin.setText(item.getOrigin());
        tiSenderAdd.setText(item.getSenderAddress());
        tiDestination.setText(item.getDestination());
        tiReceiverAdd.setText(item.getReceiverAddress());

        edit = true;
        btnSubmit.setText("Update");
    }

    @Override
    public void onClick(View v) {
        // int selectedId = typesGoods.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        //RadioButton radioButton = (RadioButton) findViewById(selectedId);
        //String typesGoods = radioButton.getText().toString();

        String SenderName, Date, Type, Weight, Origin, SenderAdd, Destination, ReceiverAdd;

        SenderName = tiName.getText().toString();
        Date = tiDate.getText().toString();
        Type = String.valueOf(typesGoods.getCheckedRadioButtonId());
        Weight = tiWeight.getText().toString();
        Origin = tiOrigin.getText().toString();
        SenderAdd = tiSenderAdd.getText().toString();
        Destination = tiDestination.getText().toString();
        ReceiverAdd = tiReceiverAdd.getText().toString();
        if(v == btnSubmit){
            if(SenderName.equals("") || Date.equals("") || Type.equals("") || Weight.equals("") || Origin.equals("")
            || SenderAdd.equals("") || Destination.equals("") || ReceiverAdd.equals("")) {
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
            } else {
                shipmentPresenter.editData(SenderName, Date, Type, Weight, Origin, SenderAdd, Destination, ReceiverAdd, appDatabase);
                edit = false;
            }
            resetForm();
        }
    }
}
