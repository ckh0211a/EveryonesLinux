package com.teamsquare.everyoneslinux.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamsquare.everyoneslinux.R;
import com.teamsquare.everyoneslinux.item.CommandData;

import java.util.ArrayList;

public class CommandAdapter extends RecyclerView.Adapter<CommandAdapter.ItemViewHolder> implements Filterable {

// adapter에 들어갈 list 입니다.
//  private ArrayList<CommandData> listData = new ArrayList<>();

    private ArrayList<CommandData> mArrayList;
    private ArrayList<CommandData> mFilteredList;

    public CommandAdapter(ArrayList<CommandData> arrayList) {
        mArrayList = arrayList;
        mFilteredList = arrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.honey_tip_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(mFilteredList.get(position));

    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return mFilteredList.size();
    }

    public void addItem(CommandData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        mArrayList.add(data);
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    mFilteredList = mArrayList;
                } else {

                    ArrayList<CommandData> filteredList = new ArrayList<>();
                    for (CommandData commandData : mArrayList) {
                        if (commandData.getTitle().toLowerCase().contains(charString) ||
                                commandData.getContent().toLowerCase().contains(charString)) {

                            filteredList.add(commandData);
                        }
                    }
                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<CommandData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;
        private TextView textView2;
        private ImageView imageView;

        ItemViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
        }

        void onBind(CommandData data) {
            textView1.setText(data.getTitle());
            textView2.setText(data.getContent());
            imageView.setImageResource(data.getResId());
        }
    }
}