package com.example.finaleproject.view.shipment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finaleproject.R;
import com.example.finaleproject.data.entity.DataShipment;
import com.example.finaleproject.view.activity.EditDataActivity;

import java.util.List;

public class ShipmentAdapter extends RecyclerView.Adapter<ShipmentAdapter.ViewHolder> {

    Context context;
    List<DataShipment> list;
    ShipmentContact.Delete view;

    public ShipmentAdapter(Context context, List<DataShipment> list, ShipmentContact.Delete view) {
        this.context = context;
        this.list = list;
        this.view = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_shipment, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataShipment data = list.get(position);
        holder.id.setText(String.valueOf(data.getIdShipment()));
        holder.tvName.setText(data.getName());
        holder.tvDate.setText(data.getDate());
        holder.tvTypes.setText(data.getTypes());
        holder.tvWeight.setText(String.valueOf(data.getWeight()));
        holder.tvCourier.setText(data.getCourierServices());
        holder.tvOrigin.setText(data.getOrigin());
        holder.tvSenderAdd.setText(data.getSenderAddress());
        holder.tvDestination.setText(data.getDestination());
        holder.tvReceiverAdd.setText(data.getReceiverAddress());
        holder.btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                view.deleteData(data);
            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditDataActivity.class);
                intent.putExtra("name", data.getName());
                intent.putExtra("date", data.getDate());
                intent.putExtra("types", data.getTypes());
                intent.putExtra("weight", data.getWeight());
                intent.putExtra("origin", data.getOrigin());
                intent.putExtra("senderAddress", data.getSenderAddress());
                intent.putExtra("destination", data.getDestination());
                intent.putExtra("receiverAddress", data.getReceiverAddress());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, tvName, tvDate, tvTypes, tvWeight, tvCourier, tvOrigin;
        TextView tvSenderAdd, tvDestination, tvReceiverAdd;
        Button btnDelete, btnUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idShipment);
            tvName = itemView.findViewById(R.id.nameText);
            tvDate = itemView.findViewById(R.id.dateText);
            tvTypes = itemView.findViewById(R.id.typesShipmentText);
            tvWeight = itemView.findViewById(R.id.weightText);
            tvCourier = itemView.findViewById(R.id.courierText);
            tvOrigin = itemView.findViewById(R.id.originText);
            tvSenderAdd = itemView.findViewById(R.id.senderAddressText);
            tvDestination = itemView.findViewById(R.id.destinationText);
            tvReceiverAdd = itemView.findViewById(R.id.receiverAddressText);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }
}
