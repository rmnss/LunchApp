package com.hfda.LunchApp.helper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hfda.LunchApp.R;
import com.hfda.LunchApp.objectClass.Menu;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private List<Menu> menuList;

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView price;
        TextView manufacturer;
        TextView category;





        MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.type);
            manufacturer = (TextView) view.findViewById(R.id.manufacturer);
            price = (TextView) view.findViewById(R.id.price);
            category = (TextView) view.findViewById(R.id.category);
        }
    }


    public MenuAdapter(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Menu menu = menuList.get(position);
        holder.name.setText(menu.getName());
        holder.manufacturer.setText(menu.getManufacturer());
        holder.price.setText(menu.getPrice() + ",-");
        holder.category.setText(menu.getCategory());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}
