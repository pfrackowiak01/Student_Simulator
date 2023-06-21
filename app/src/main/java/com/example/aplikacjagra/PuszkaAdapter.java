package com.example.aplikacjagra;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PuszkaAdapter extends ArrayAdapter<Puszka> {

    private Context pContext;
    private List<Puszka> puszkaList = new ArrayList<>();

    public PuszkaAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Puszka> list) {
        super(context, 0 , list);
        pContext = context;
        puszkaList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(pContext).inflate(R.layout.activity_list_view,parent,false);

        Puszka currentPuszka = puszkaList.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.miniaturkaImageView);
        image.setImageResource(currentPuszka.getSkinImageDrawable());

        TextView name = (TextView) listItem.findViewById(R.id.puszkaTextView);
        name.setText(currentPuszka.getName());

        TextView posiadane = (TextView) listItem.findViewById(R.id.puszkaTextView2);
        if (currentPuszka.getPosiadane() == true)
        {
            posiadane.setText("odblokowany");
        }
        else
        {
            posiadane.setText("od " + currentPuszka.getKoszt() + " pkt");
        }

        return listItem;
    }
}
