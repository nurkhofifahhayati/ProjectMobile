package com.example.finaleproject.view.shipment;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.finaleproject.data.entity.AppDatabase;
import com.example.finaleproject.data.entity.DataShipment;

public class ShipmentPresenter implements ShipmentContact.DataPresenter{
    ShipmentContact.view view;
    ShipmentContact.Delete viewDelete;

    public ShipmentPresenter(ShipmentContact.view view) {
        this.view = view;
    }

    public ShipmentPresenter(ShipmentContact.Delete viewDelete) {
        this.viewDelete = viewDelete;
    }

    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase appDatabase;
        private DataShipment dataShipment;

        public EditData(AppDatabase appDatabase, DataShipment dataShipment) {
            this.appDatabase = appDatabase;
            this.dataShipment = dataShipment;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return appDatabase.dao().updateData(dataShipment);
        }
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute: " + integer);
            view.successAdd();
        }
    }

    @Override
    public void editData(String name, String date, String types, String weight, String courier, String origin, String senderAdd, String destination, String receiverAdd, AppDatabase appDatabase) {
        final DataShipment dataShipment = new DataShipment();
        dataShipment.setName(name);
        dataShipment.setDate(date);
        dataShipment.setTypes(types);
        dataShipment.setWeight(weight);
        dataShipment.setCourierServices(courier);
        dataShipment.setOrigin(origin);
        dataShipment.setSenderAddress(senderAdd);
        dataShipment.setDestination(destination);
        dataShipment.setReceiverAddress(receiverAdd);
        new EditData(appDatabase, dataShipment).execute();
    }

    class DeleteData extends AsyncTask<Void, Void, Void>{
        private AppDatabase appDatabase;
        private DataShipment dataShipment;
        Context context;

        public DeleteData(AppDatabase appDatabase, DataShipment dataShipment) {
            this.appDatabase = appDatabase;
            this.dataShipment = dataShipment;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            appDatabase.dao().deleteData(dataShipment);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewDelete.successDelete();
        }
    }

    @Override
    public void deleteData(DataShipment dataShipment, AppDatabase appDatabase) {
        new DeleteData(appDatabase, dataShipment).execute();
    }
}
