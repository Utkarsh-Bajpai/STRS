package com.example.android.strs.Tab_Layout;

/**
 * Created by Utkarsh._.Bajpai on 29-Apr-17.
 */


        import android.content.Context;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.android.strs.R;
        import com.example.android.strs.data_company.CompanyDatabaseHelper;
        import com.example.android.strs.data_transport.TransportContact;

        import java.util.ArrayList;
        import java.util.List;

public class MyRecyclerAdapter extends RecyclerView
        .Adapter<MyRecyclerAdapter
        .DataObjectHolder>{
    private static String LOG_TAG = "MyRecyclerViewAdapter";

    private ArrayList<TransportContact> mDataset;
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
            //Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;

    }

    public MyRecyclerAdapter(ArrayList<TransportContact> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_transport, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);

        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        holder.name.setText(mDataset.get(position).gettname());
        holder.source.setText(mDataset.get(position).getsource());
        holder.destination.setText(mDataset.get(position).getdestination());
        holder.duration.setText(mDataset.get(position).getjtime());
        holder.price.setText(mDataset.get(position).getcost());
        holder.date.setText(mDataset.get(position).getdate());
        holder.time.setText(mDataset.get(position).getdtime());
        holder.seats.setText(mDataset.get(position).getseats());
        holder.company.setText(mDataset.get(position).getcusername());

        String mode, user;
        user = mDataset.get(position).getcusername();

       /* if( user != null)
        {
            mode = helper.searchMode(user);

            if(mode.equals(R.string.mode_cab))
            {
                holder.pic.setImageResource(R.drawable.taxi);
            }
            else if(mode.equals(R.string.mode_bus))
            {
                holder.pic.setImageResource(R.drawable.bus);
            }
            else if(mode.equals(R.string.mode_train))
            {
                holder.pic.setImageResource(R.drawable.train);
            }
            else if(mode.equals(R.string.mode_airplane))
            {
                holder.pic.setImageResource(R.drawable.plane);
            }
            else
            {
                holder.pic.setImageResource(R.drawable.strslogo);
            }
        }*/

    }

    public void addItem(TransportContact dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}