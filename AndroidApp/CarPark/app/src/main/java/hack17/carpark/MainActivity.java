package hack17.carpark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<CarPark> carParkList = new ArrayList<>();
    RecyclerView recyclerView;
    int freeSlots, totalSlots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref_floors = database.getReference().child("Floors");

        addCarkParks();

        ref_floors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                carParkList.remove(0);
                freeSlots = 0;
                totalSlots = 0;

                for (DataSnapshot data : dataSnapshot.getChildren()){
                    Floor floor = data.getValue(Floor.class);
                    freeSlots+=floor.getFree();
                    totalSlots+=floor.getTotal();
                }

                carParkList.add(0, new CarPark("KCC Car park", freeSlots, totalSlots));
                recyclerView.setAdapter(new LinearRecyclerAdapter(getApplicationContext(), carParkList));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void addCarkParks(){
        CarPark park = new CarPark("Park 02", 11, 18);
        carParkList.add(park);
        park = new CarPark("Park 03", 2, 18);
        carParkList.add(park);
        park = new CarPark("Park 04", 14, 18);
        carParkList.add(park);
        park = new CarPark("Park 05", 6, 18);
        carParkList.add(park);
        park = new CarPark("Park 06", 18, 18);
        carParkList.add(park);
        park = new CarPark("Park 07", 0, 18);
        carParkList.add(park);
    }
}
