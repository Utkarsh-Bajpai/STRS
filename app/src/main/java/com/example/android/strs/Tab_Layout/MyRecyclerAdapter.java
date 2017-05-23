package com.example.android.strs.Tab_Layout;

/**
 * Created by Utkarsh._.Bajpai on 29-Apr-17.
 */


        import android.content.Context;
        import android.content.res.Resources;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.example.android.strs.R;
        import com.example.android.strs.data_company.CompanyDatabaseHelper;
        import com.example.android.strs.data_transport.TransportContact;

        import java.util.ArrayList;
        import java.util.List;

public class MyRecyclerAdapter extends RecyclerView
        .Adapter<MyRecyclerAdapter
        .DataObjectHolder>{
    private static String LOG_TAG = "MyRecyclerViewAdapter";

    private static MyClickListener myClickListener;

    public static String namea;
    String sourcea;
    String destinationa;
    String durationa;
    String Pricea;
    String datea;
    String timea;
    String seatsa;
    String companya;

    static   List<TransportContact> dbList;
    static Context context;
    private CompanyDatabaseHelper helper;


    public MyRecyclerAdapter(Context context, List<TransportContact> dbList )
    {
        this.helper = new CompanyDatabaseHelper(context.getApplicationContext());
        this.dbList = new ArrayList<TransportContact>();
        this.context = context;
        this.dbList = dbList;

    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView name;
        TextView source;
        TextView destination;
        TextView duration;
        TextView price;
        TextView date;
        TextView time;
        TextView seats;
        TextView company;
        ImageView pic;


        public DataObjectHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.textView9);
            source = (TextView) itemView.findViewById(R.id.textView10);
            destination = (TextView) itemView.findViewById(R.id.textView11);
            duration = (TextView) itemView.findViewById(R.id.textView12);
            price = (TextView) itemView.findViewById(R.id.textView13);
            date = (TextView) itemView.findViewById(R.id.textView14);
            time = (TextView) itemView.findViewById(R.id.textView15);
            seats = (TextView) itemView.findViewById(R.id.textView16);
            company = (TextView) itemView.findViewById(R.id.textView18);
            pic = (ImageView) itemView.findViewById(R.id.imageView);

            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;

    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_transport, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);

        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        holder.name.setText(dbList.get(position).gettname());
        holder.source.setText(dbList.get(position).getsource());
        holder.destination.setText(dbList.get(position).getdestination());
        holder.duration.setText(dbList.get(position).getjtime());
        holder.price.setText(dbList.get(position).getcost());
        holder.date.setText(dbList.get(position).getdate());
        holder.time.setText(dbList.get(position).getdtime());
        holder.seats.setText(dbList.get(position).getseats());
        holder.company.setText(dbList.get(position).getcusername());

        String mode, user;
        user = dbList.get(position).getcusername();

        if( user != null)
        {
            mode = helper.searchMode(user);

            if(mode.equals(context.getResources().getString(R.string.mode_cab)))
            {
                holder.pic.setImageResource(R.drawable.taxi);
            }
            else if(mode.equals(context.getResources().getString(R.string.mode_bus)))
            {
                holder.pic.setImageResource(R.drawable.bus);
            }
            else if(mode.equals(context.getResources().getString(R.string.mode_train)))
            {
                holder.pic.setImageResource(R.drawable.train);
            }
            else if(mode.equals(context.getResources().getString(R.string.mode_airplane)))
            {
                holder.pic.setImageResource(R.drawable.plane);
            }
            else
            {
                holder.pic.setImageResource(R.drawable.strslogo);
            }
        }

    }

    public void addItem(TransportContact dataObj, int index) {
        dbList.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        dbList.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}