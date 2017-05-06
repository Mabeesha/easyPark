package hack17.carpark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<CarPark> carParkList = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        addCarkParks();

    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView.setAdapter(new LinearRecyclerAdapter(getApplicationContext(), carParkList));
    }

    private void addCarkParks(){
        CarPark park = new CarPark("Park 01", 5, 10);
        carParkList.add(park);
        park = new CarPark("Park 02", 14, 25);
        carParkList.add(park);
    }
}
