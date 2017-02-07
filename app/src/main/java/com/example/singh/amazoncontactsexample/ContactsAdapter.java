package com.example.singh.amazoncontactsexample;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.singh.amazoncontactsexample.pojo.Contact;

import java.util.List;

/**
 * Created by singh on 07-Feb-17.
 */

public class ContactsAdapter extends ArrayAdapter<Contact>{
    private List<Contact> contactList;

    public ContactsAdapter(Context context, int resource, List<Contact> objects) {
        super(context, resource, objects);
        this.contactList = objects;
    }

    public static class ViewHolder {
        public TextView contactName,contactNumber;
        public ImageView contactSmallImage;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Contact contact = contactList.get(position);

        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
                vi = inflater.inflate(R.layout.contact_list, null);
                holder = new ViewHolder();

                holder.contactName = (TextView) vi.findViewById(R.id.ContactName);
                holder.contactNumber = (TextView) vi.findViewById(R.id.ContactNumber);
                holder.contactSmallImage = (ImageView) vi.findViewById(R.id.ContactSmallImage);


                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }


            Glide.with(vi.getContext()).load(contact.getSmallImageURL()).into(holder.contactSmallImage);

            holder.contactName.setText(contactList.get(position).getName());
            holder.contactNumber.setText(contactList.get(position).getPhone().getWork());

            vi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),ContactActivity.class);
                    intent.putExtra("contact", contact);
                    v.getContext().startActivity(intent);

                }
            });


        } catch (Exception e) {


        }
        return vi;
    }

}
