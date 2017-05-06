package hack17.carpark;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LinearRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    View view;
    private Context context;
    private ArrayList<CarPark> carParkList;

    public LinearRecyclerAdapter(Context context, ArrayList<CarPark> list){
        this.context = context;
        this.carParkList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_car_park, parent, false);
        return new ListItemHolder(view);
    }

    public class ListItemHolder extends RecyclerView.ViewHolder{

        TextView txt_park_name, txt_park_slots;

        public ListItemHolder(View mView){
            super(mView);
            txt_park_name = (TextView)view.findViewById(R.id.txt_park_name);
            txt_park_slots = (TextView)view.findViewById(R.id.txt_park_slots);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final CarPark park = carParkList.get(position);
        ListItemHolder listItemHolder = (ListItemHolder)holder;
        listItemHolder.txt_park_name.setText(park.getName());
        listItemHolder.txt_park_slots.setText(park.getFreeSlots() + "/" + park.getTotalSlots());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CarParkMain.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carParkList.size();
    }

}
