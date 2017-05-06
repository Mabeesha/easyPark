package hack17.carpark;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
        RelativeLayout card_layout;

        public ListItemHolder(View mView){
            super(mView);
            txt_park_name = (TextView)view.findViewById(R.id.txt_park_name);
            txt_park_slots = (TextView)view.findViewById(R.id.txt_park_slots);
            card_layout = (RelativeLayout)view.findViewById(R.id.card_layout);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final CarPark park = carParkList.get(position);
        ListItemHolder listItemHolder = (ListItemHolder)holder;
        if(park.getFreeSlots() > 12){
            listItemHolder.card_layout.setBackground(context.getDrawable(R.drawable.btn_sltr_blue));
            listItemHolder.txt_park_name.setTextColor(context.getResources().getColor(R.color.colorBlueText));
            listItemHolder.txt_park_slots.setTextColor(context.getResources().getColor(R.color.colorBlueText));
        }else if(park.getFreeSlots() > 5){
            listItemHolder.card_layout.setBackground(context.getDrawable(R.drawable.btn_sltr_yellow));
            listItemHolder.txt_park_name.setTextColor(context.getResources().getColor(R.color.colorYellowText));
            listItemHolder.txt_park_slots.setTextColor(context.getResources().getColor(R.color.colorYellowText));
        }else{
            listItemHolder.card_layout.setBackground(context.getDrawable(R.drawable.btn_sltr_red));
            listItemHolder.txt_park_name.setTextColor(context.getResources().getColor(R.color.colorRedText));
            listItemHolder.txt_park_slots.setTextColor(context.getResources().getColor(R.color.colorRedText));
        }
        listItemHolder.txt_park_name.setText(park.getName());
        listItemHolder.txt_park_slots.setText(park.getFreeSlots() + "/" + park.getTotalSlots());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(park.getFreeSlots() != 0){
                    Intent intent = new Intent(view.getContext(), CarParkMain.class);
                    view.getContext().startActivity(intent);
                }else {
                    Snackbar.make(view, "No Available Slots! Try another park", Snackbar.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return carParkList.size();
    }

}
