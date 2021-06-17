package com.example.finaleproject.view.fragment;

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
import com.example.finaleproject.view.activity.ReadDataActivity;

public class ListFragment extends Fragment {
    private Button btnClickList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        btnClickList = (Button) view.findViewById(R.id.btnClickList);

        btnClickList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), ReadDataActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
