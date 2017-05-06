package hack17.carpark;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CarParkMain extends AppCompatActivity{

    TextView txt_floor1_slots, txt_floor2_slots, txt_floor3_slots, txt_floor4_slots;
    ArrayList<TextView> floorArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_carpark);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref_floors = database.getReference().child("Floors");

        txt_floor1_slots = (TextView)findViewById(R.id.txt_floor1_slots);
        txt_floor2_slots = (TextView)findViewById(R.id.txt_floor2_slots);
        //txt_floor3_slots = (TextView)findViewById(R.id.txt_floor3_slots);
        //txt_floor4_slots = (TextView)findViewById(R.id.txt_floor4_slots);

        floorArray.add(txt_floor1_slots);
        floorArray.add(txt_floor2_slots);

        ref_floors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(getApplicationContext(), "Data Changed", Toast.LENGTH_SHORT).show();
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    Floor floor = data.getValue(Floor.class);
                    floorArray.get(floor.getFloor()-1).setText(floor.getFree() + "/" + floor.getTotal());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
