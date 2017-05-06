package hack17.carpark;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CarParkMain extends AppCompatActivity{

    TextView txt_floor1_slots, txt_floor2_slots, txt_floor3_slots;
    LinearLayout layoutfloor1, layoutfloor2, layoutfloor3;
    ArrayList<TextView> floorArray = new ArrayList<>();
    ArrayList<LinearLayout> layouts = new ArrayList<>();
    boolean[] haveFree = new boolean[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_carpark);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref_floors = database.getReference().child("Floors");

        txt_floor1_slots = (TextView)findViewById(R.id.txt_floor1_slots);
        txt_floor2_slots = (TextView)findViewById(R.id.txt_floor2_slots);
        txt_floor3_slots = (TextView)findViewById(R.id.txt_floor3_slots);

        layoutfloor1 = (LinearLayout)findViewById(R.id.linearlayout_floor1);
        layoutfloor2 = (LinearLayout)findViewById(R.id.linearlayout_floor2);
        layoutfloor3 = (LinearLayout)findViewById(R.id.linearlayout_floor3);

        layouts.add(layoutfloor1);
        layouts.add(layoutfloor2);
        layouts.add(layoutfloor3);

        floorArray.add(txt_floor1_slots);
        floorArray.add(txt_floor2_slots);
        floorArray.add(txt_floor3_slots);

        layoutfloor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(haveFree[0]){
                    Intent intent = new Intent(getApplicationContext(), Floor1.class);
                    startActivity(intent);
                }
                Snackbar.make(view, "Sorry! No Available Slots", Snackbar.LENGTH_SHORT).show();
            }
        });

        layoutfloor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(haveFree[1]){
                    Intent intent = new Intent(getApplicationContext(), Floor2.class);
                    startActivity(intent);
                }
                Snackbar.make(view, "Sorry! No Available Slots", Snackbar.LENGTH_SHORT).show();
            }
        });

        layoutfloor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(haveFree[2]){
                    Intent intent = new Intent(getApplicationContext(), Floor3.class);
                    startActivity(intent);
                }
                Snackbar.make(view, "Sorry! No Available Slots", Snackbar.LENGTH_SHORT).show();
            }
        });

        ref_floors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()){
                    Floor floor = data.getValue(Floor.class);
                    floorArray.get(floor.getFloor()-1).setText(floor.getFree() + "/" + floor.getTotal());
                    if(floor.getFree() == 0){
                        haveFree[floor.getFloor()-1] = false;
                    }else {
                        haveFree[floor.getFloor()-1] = true;
                    }
                    if(floor.getFree() > 4){
                        floorArray.get(floor.getFloor()-1).setTextColor(getResources().getColor(R.color.colorBlueText));
                        layouts.get(floor.getFloor()-1).setBackground(getResources().getDrawable(R.drawable.btn_sltr_blue));
                    }else if(floor.getFree() > 2){
                        floorArray.get(floor.getFloor()-1).setTextColor(getResources().getColor(R.color.colorYellowText));
                        layouts.get(floor.getFloor()-1).setBackground(getResources().getDrawable(R.drawable.btn_sltr_yellow));
                    }else{
                        floorArray.get(floor.getFloor()-1).setTextColor(getResources().getColor(R.color.colorRedText));
                        layouts.get(floor.getFloor()-1).setBackground(getResources().getDrawable(R.drawable.btn_sltr_red));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
