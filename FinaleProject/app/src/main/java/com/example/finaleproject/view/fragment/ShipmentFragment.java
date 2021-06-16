package com.example.finaleproject.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finaleproject.R;
import com.example.finaleproject.view.shipment.ShipmentActivity;

public class ShipmentFragment extends Fragment {
    private Button btnClickInput;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shipment, container, false);

        btnClickInput = (Button) view.findViewById(R.id.btnClickInput);

        btnClickInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ShipmentActivity.class));
            }
        });

        return view;
    }
}

    /*   private TextInputEditText tiId, tiName, tiDate, tiWeight, tiOrigin, tiSenderAdd, tiDestination, tiReceiverAdd;
    private RadioGroup typesGoods, courierServices;
    private RadioButton electronic, apparel, automotive, etc;
    private RadioButton jne, pos, tiki;
    private Button btnSubmit;
    private String Types, Courier, setId, setName, setDate, setWeight, setOrigin, setSenderAdd, setDestination, setReceiverAdd;

    AppDatabase appDatabase;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_shipment);

        tiId = findViewById(R.id.inputIdShipment);
        tiName = findViewById(R.id.inputSenderName);
        tiDate = findViewById(R.id.inputDate);
        tiWeight = findViewById(R.id.inputWeight);
        tiOrigin = findViewById(R.id.inputOrigin);
        tiSenderAdd = findViewById(R.id.inputSenderAddress);
        tiDestination = findViewById(R.id.inputDestination);
        tiReceiverAdd = findViewById(R.id.inputReceiverAddress);

        typesGoods = findViewById(R.id.typesShipment);
        electronic = findViewById(R.id.electronicRadio);
        apparel = findViewById(R.id.apparelRadio);
        automotive = findViewById(R.id.automotiveRadio);
        etc = findViewById(R.id.etcRadio);
        courierServices = findViewById(R.id.typesCourier);
        jne = findViewById(R.id.jneRadio);
        pos = findViewById(R.id.posRadio);
        tiki = findViewById(R.id.tikiRadio);

        btnSubmit = findViewById(R.id.btnSubmit);
        appDatabase = AppDatabase.iniDb(context.getApplicationContext());
        btnSubmit.setOnClickListener(new View.OnClickListener() { //action perpindahan dari class ini ke class lihat data
            @Override
            public void onClick(View v) {
                input();
                Intent submit = new Intent(context.getApplicationContext(), ListFragment.class);
                startActivity(submit);
            }
        });
    }*/

    /* @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tiId = view.findViewById(R.id.inputIdShipment);
        tiName = view.findViewById(R.id.inputSenderName);
        tiDate = view.findViewById(R.id.inputDate);
        tiWeight = view.findViewById(R.id.inputWeight);
        tiOrigin = view.findViewById(R.id.inputOrigin);
        tiSenderAdd = view.findViewById(R.id.inputSenderAddress);
        tiDestination = view.findViewById(R.id.inputDestination);
        tiReceiverAdd = view.findViewById(R.id.inputReceiverAddress);

        typesGoods = view.findViewById(R.id.typesShipment);
        electronic = view.findViewById(R.id.electronicRadio);
        apparel = view.findViewById(R.id.apparelRadio);
        automotive = view.findViewById(R.id.automotiveRadio);
        etc = view.findViewById(R.id.etcRadio);
        courierServices = view.findViewById(R.id.typesCourier);
        jne = view.findViewById(R.id.jneRadio);
        pos = view.findViewById(R.id.posRadio);
        tiki = view.findViewById(R.id.tikiRadio);

        btnSubmit = view.findViewById(R.id.btnSubmit);
        appDatabase = AppDatabase.iniDb(context.getApplicationContext());
        btnSubmit.setOnClickListener(new View.OnClickListener() { //action perpindahan dari class ini ke class lihat data
            @Override
            public void onClick(View v) {
                input();
                Intent submit = new Intent(context.getApplicationContext(), ListFragment.class);
                startActivity(submit);
            }
        });
    }*/

/*    public void input(){
        setId = tiId.getText().toString();
        setName = tiName.getText().toString();
        setDate = tiDate.getText().toString();
        Types = String.valueOf(typesGoods.getCheckedRadioButtonId());
        setWeight = tiWeight.getText().toString();
        Courier = String.valueOf(courierServices.getCheckedRadioButtonId());
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

        //memanggil fungsi insert data(variable appdatabase untuk koneksi ke app database, variabel kelas data sekolah untuk masukin data
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
            Toast.makeText(context.getApplicationContext(), "Success Added", Toast.LENGTH_SHORT).show();
        }
    }*/

