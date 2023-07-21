//package com.example.prm_project.views.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.prm_project.R;
//import com.example.prm_project.data.dao.models.Item;
//import com.example.prm_project.views.ItemList;
//import com.example.prm_project.views.ViewHolder.ItemVH;
//
//import java.util.List;
//
//public class ItemAdapter extends RecyclerView.Adapter<ItemVH>{
//    private List<Item> itemlist;
//    private Context context;
//    private LayoutInflater inflater;
//
//    public ItemAdapter(List<Item> mylist, Context context) {
//        this.itemlist = mylist;
//        this.context = context;
//        inflater = LayoutInflater.from(context);
//    }
//
//
//    @NonNull
//    @Override
//    public ItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.item, parent, false);
//        return new ItemVH(view, context);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ItemVH holder, int position) {
//        Item i = itemlist.get(position);
//        holder.setItem(i);
//    }
//
//    @Override
//    public int getItemCount() {
//        return itemlist.size();
//    }
//}
