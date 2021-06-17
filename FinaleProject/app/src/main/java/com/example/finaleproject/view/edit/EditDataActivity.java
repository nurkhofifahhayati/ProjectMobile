package com.example.finaleproject.view.edit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.finaleproject.R;
import com.example.finaleproject.data.entity.AppDatabase;
import com.example.finaleproject.data.entity.DataShipment;
import com.example.finaleproject.view.activity.ReadDataActivity;
import com.example.finaleproject.view.shipment.ShipmentAdapter;
import com.example.finaleproject.view.shipment.ShipmentContact;
import com.example.finaleproject.view.shipment.ShipmentPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class EditDataActivity extends AppCompatActivity implements ShipmentContact.view {
    private AppDatabase appDatabase;
    private ShipmentPresenter shipmentPresenter;
    private ShipmentAdapter shipmentAdapter;
    private DataShipment data;

    private TextInputEditText tiName, tiDate, tiWeight, tiOrigin, tiSenderAdd, tiDestination, tiReceiverAdd;
    private RadioGroup typesGoods, courierS;
    private RadioButton electronic, apparel, automotive, etc;
    private RadioButton jne, pos, tiki;
    private Button btnSubmit, btnReset;
    private String Type, Courier, setName, setDate, setWeight, setOrigin, setSenderAdd, setDestination, setReceiverAdd;

    private boolean edit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

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

        jne = findViewById(R.id.jneRadio);
        pos = findViewById(R.id.posRadio);
        tiki = findViewById(R.id.tikiRadio);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnReset = findViewById(R.id.btnReset);
        shipmentPresenter = new ShipmentPresenter(this);
        appDatabase = AppDatabase.iniDb(getApplicationContext());

        setName = getIntent().getStringExtra("name");
        setDate = getIntent().getStringExtra("date");
        typesGoods = (RadioGroup) getIntent().getCharSequenceExtra("types");
        setWeight = getIntent().getStringExtra("weight");
        courierS = (RadioGroup) getIntent().getCharSequenceExtra("courierServices");
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

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbShipment").build();

        getDataShipment();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setName(tiName.getText().toString());
                data.setDate(tiDate.getText().toString());
                data.setTypes(Type);
                data.setWeight(tiWeight.getText().toString());
                data.setCourierServices(Courier);
                data.setOrigin(tiOrigin.getText().toString());
                data.setSenderAddress(tiSenderAdd.getText().toString());
                data.setDestination(tiDestination.getText().toString());
                data.setReceiverAddress(tiReceiverAdd.getText().toString());
                editData(data);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetForm();
            }
        });

    }

    // method untuk menampilkan data shipment yang akan diedit
    private void getDataShipment() {
        data = (DataShipment) getIntent().getSerializableExtra("data");

        tiName.setText(data.getName());
        tiDate.setText(data.getDate());
        tiWeight.setText(data.getWeight());
        tiOrigin.setText(data.getOrigin());
        tiSenderAdd.setText(data.getSenderAddress());
        tiDestination.setText(data.getDestination());
        tiReceiverAdd.setText(data.getReceiverAddress());
        switch (data.getTypes()) {
            case "Electronics":
                electronic.setChecked(true);
                Type = "Electronics";
                break;
            case "Apparel":
                apparel.setChecked(true);
                Type = "Apparel";
                break;
            case "Automotive":
                automotive.setChecked(true);
                Type = "Automotive";
                break;
            case "Other Types":
                etc.setChecked(true);
                Type = "Other Types";
                break;
        }
        switch (data.getCourierServices()) {
            case "JNE":
                jne.setChecked(true);
                Courier = "JNE";
                break;
            case "POS Indonesia":
                pos.setChecked(true);
                Courier = "POS Indonesia";
                break;
            case "TIKI":
                tiki.setChecked(true);
                Courier = "TIKI";
                break;
        }
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
        Toast.makeText(EditDataActivity.this, "Success Updated!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditDataActivity.this, ReadDataActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditDataActivity.this, ReadDataActivity.class));
        finish();
    }

    @Override
    public void editData(DataShipment item) {
        tiName.setText(item.getName());
        tiDate.setText(item.getDate());
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
    /*    // int selectedId = typesGoods.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        //RadioButton radioButton = (RadioButton) findViewById(selectedId);
        //String typesGoods = radioButton.getText().toString();

        String SenderName, Date, Weight, Origin, SenderAdd, Destination, ReceiverAdd, Type, Courier = "";

        SenderName = tiName.getText().toString();
        Date = tiDate.getText().toString();
        Type = String.valueOf(typesGoods.getCheckedRadioButtonId());
        Weight = tiWeight.getText().toString();
        Origin = tiOrigin.getText().toString();
        SenderAdd = tiSenderAdd.getText().toString();
        Destination = tiDestination.getText().toString();
        ReceiverAdd = tiReceiverAdd.getText().toString();

        if(v == btnSubmit){
            if(SenderName.equals("") || Date.equals("") || Type.equals("") || Weight.equals("") || Courier.equals("") || Origin.equals("")
            || SenderAdd.equals("") || Destination.equals("") || ReceiverAdd.equals("")) {
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
            } else {
                shipmentPresenter.editData(SenderName, Date, Type, Weight, Courier, Origin, SenderAdd, Destination, ReceiverAdd, appDatabase);
                edit = false;
            }
            resetForm();
        }
    }*/
    }
}
