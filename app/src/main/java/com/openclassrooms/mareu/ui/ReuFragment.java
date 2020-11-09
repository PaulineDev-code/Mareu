package com.openclassrooms.mareu.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.openclassrooms.mareu.di.DI;
import com.openclassrooms.mareu.model.Reunion;
import com.openclassrooms.mareu.service.ReunionApiService;

import java.util.List;
import java.util.Objects;

import static java.security.AccessController.getContext;

//public class ReuFragment extends Fragment {

//    private ReunionApiService mApiService;
//    private List<Reunion> mReunion ;
//    private RecyclerView mRecyclerView ;



//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mApiService = DI.getReunionApiService();}
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_reu, container, false);
////        Context context = view.getContext();
////        mRecyclerView = (RecyclerView) view;
////        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
//        return view;
//    }
//
//
//
//}
