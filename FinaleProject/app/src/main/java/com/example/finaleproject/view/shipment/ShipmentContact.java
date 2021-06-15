package com.example.finaleproject.view.shipment;

import android.view.View;

import com.example.finaleproject.data.entity.AppDatabase;
import com.example.finaleproject.data.entity.DataShipment;

import java.util.List;

public interface ShipmentContact {
    interface view extends View.OnClickListener{
        void resetForm();
        void successAdd();
        void editData(DataShipment item);
    }

    interface DataPresenter {
        // readData (AppDatabase database);
        void editData(String name, String date, String types, int weight, String origin,
                      String senderAdd, String destination, String receiverAdd, AppDatabase appDatabase);
        void deleteData(DataShipment dataShipment, AppDatabase appDatabase);
    }

    interface Print extends View.OnClickListener {
        void getData(List<DataShipment> list);
    }

    interface Delete {
        void successDelete();
        void deleteData(DataShipment item);
    }
}
