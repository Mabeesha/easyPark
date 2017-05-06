package hack17.carpark;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FloorOne extends AppCompatActivity{

    ImageView slot1, slot2, slot3, slot4;
    ArrayList<ImageView> slotArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_floor);

        slot1 = (ImageView)findViewById(R.id.slot_1);
        slot2 = (ImageView)findViewById(R.id.slot_2);
        slot3 = (ImageView)findViewById(R.id.slot_3);
        slot4 = (ImageView)findViewById(R.id.slot_4);

        slotArray.add(slot1);
        slotArray.add(slot2);
        slotArray.add(slot3);
        slotArray.add(slot4);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref_floor1 = database.getReference().child("Slots");

        ref_floor1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(getApplicationContext(), "Data Changed", Toast.LENGTH_SHORT).show();
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    CarSlot slot = data.getValue(CarSlot.class);
                    if (slot.getIsFreeSlot() == 0){// free
                        slotArray.get(slot.getSlotNumber()-1).setVisibility(View.INVISIBLE);
                    }else {
                        slotArray.get(slot.getSlotNumber()-1).setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
