package com.example.finaleproject.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finaleproject.R;
import com.example.finaleproject.data.entity.AppDatabase;
import com.example.finaleproject.data.entity.DataShipment;
import com.example.finaleproject.view.shipment.ShipmentAdapter;
import com.example.finaleproject.view.shipment.ShipmentContact;
import com.example.finaleproject.view.shipment.ShipmentPresenter;

import java.util.List;

public class ReadDataActivity extends AppCompatActivity implements ShipmentContact.Delete {
    private AppDatabase appDatabase;
    private ShipmentAdapter shipmentAdapter;
    private ShipmentPresenter shipmentPresenter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);

        shipmentPresenter = new ShipmentPresenter( this);
        recyclerView = findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        appDatabase = AppDatabase.iniDb(getApplicationContext());

        readData(appDatabase);
    }

    private void readData(AppDatabase appDatabase){
        List list;
        list = appDatabase.dao().getData();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            shipmentAdapter = new ShipmentAdapter(getApplicationContext(), list, this);
        }
        recyclerView.setAdapter(shipmentAdapter);
    }

    @Override
    public void successDelete() {
        Toast.makeText(getApplicationContext(), "Success Deleted!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), ReadDataActivity.class));
    }

    @Override
    public void deleteData(DataShipment item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Delete Data")
                .setMessage("Are you sure want to delete this?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shipmentPresenter.deleteData(item, appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .show();
    }
}
