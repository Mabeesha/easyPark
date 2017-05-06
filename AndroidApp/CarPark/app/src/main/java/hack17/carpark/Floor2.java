package hack17.carpark;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SignalStrength;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Floor2 extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_floor);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref_floors = database.getReference().child("Floors");

        final TextView txt_available_slots = (TextView)findViewById(R.id.txt_floor_slots);
        TextView txt_predicted_slots = (TextView)findViewById(R.id.txt_floor_predicted_slots);
        Button btn_reserve = (Button)findViewById(R.id.btn_reserve);

        ref_floors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()){
                    Floor floor = data.getValue(Floor.class);
                    if(floor.getFloor() == 2){
                        txt_available_slots.setText(String.valueOf(floor.getFree()));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btn_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Your slot is reserved", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

}
