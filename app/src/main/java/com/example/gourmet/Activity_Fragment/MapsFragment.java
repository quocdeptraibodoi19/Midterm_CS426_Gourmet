package com.example.gourmet.Activity_Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gourmet.DataElement.StoreElement;
import com.example.gourmet.R;
import com.example.gourmet.ViewModel.StoreViewModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment {
    private GoogleMap mMap;
    private Spinner spinner;
    private StoreViewModel viewModel;

    private List<StoreElement> stores = new ArrayList<>();

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        View ActionBarFragment = view.findViewById(R.id.actionBar_homefrag_id);
        TextView namefragment = ActionBarFragment.findViewById(R.id.name_fragment_id);

        namefragment.setText("Cửa hàng");

        viewModel = new ViewModelProvider(this).get(StoreViewModel.class);

        // Initialize spinner
        spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setMapPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this.getActivity(), R.layout.spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        viewModel.getStoreList().observe(getViewLifecycleOwner(), new Observer<List<StoreElement>>() {
            @Override
            public void onChanged(List<StoreElement> storeElements) {
                stores = storeElements;

                List<String> address = new ArrayList<>();
                for(StoreElement element : storeElements){
                    address.add(element.getAddress());
                }
                arrayAdapter.clear();
                arrayAdapter.addAll(address);
            }
        });

        View Navfragment = view.findViewById(R.id.navigationbarID_homefrag);
        Navfragment.findViewById(R.id.homeIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MapsFragment.this).navigate(R.id.action_mapsFragment_to_homeFragment);
            }
        });
        Navfragment.findViewById(R.id.transactionIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MapsFragment.this).navigate(R.id.action_mapsFragment_to_transactionHistoryFragment);
            }
        });
        Navfragment.findViewById(R.id.recipeIconId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MapsFragment.this).navigate(R.id.action_mapsFragment_to_recipeListFragment);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private void setMapPosition(int position){
        StoreElement store = stores.get(position);
        LatLng pos = new LatLng(store.getLatitude(), store.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(pos, 15);
        mMap.moveCamera(cameraUpdate);
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(pos)
                .title("Store " + String.valueOf(position + 1))
                .snippet(store.getAddress()));

        assert marker != null;
        marker.showInfoWindow();
    }
}