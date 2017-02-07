package com.example.singh.amazoncontactsexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.singh.amazoncontactsexample.pojo.Contact;

import org.w3c.dom.Text;

public class ContactActivity extends AppCompatActivity {

    private TextView contactName, contactCompany,contactHome, contactWork, contactMobile, contactAddress, contactBirthday, contactEmail, contactWebsite;
    private ImageView contactImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        contactName = (TextView) findViewById(R.id.ContactName);
        contactCompany = (TextView) findViewById(R.id.ContactCompany);
        contactHome = (TextView) findViewById(R.id.ContactHome);
        contactWork = (TextView) findViewById(R.id.ContactWork);
        contactMobile = (TextView) findViewById(R.id.ContactMobile);
        contactAddress = (TextView) findViewById(R.id.ContactAddress);
        contactBirthday = (TextView) findViewById(R.id.ContactBirthday);
        contactEmail = (TextView) findViewById(R.id.ContactEmail);
        contactImage = (ImageView) findViewById(R.id.contactImage);
        contactWebsite = (TextView) findViewById(R.id.ContactWebsite);


        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("contact");

        Glide.with(this).load(contact.getLargeImageURL()).into(contactImage);

        contactName.setText(contact.getName());
        contactCompany.setText(contact.getCompany());
        contactHome.setText(contact.getPhone().getHome());
        contactWork.setText(contact.getPhone().getWork());
        contactMobile.setText(contact.getPhone().getMobile());

        StringBuilder address = new StringBuilder();
        address.append(contact.getAddress().getStreet());
        address.append("\n");
        address.append(contact.getAddress().getCity());
        address.append("\n");
        address.append(contact.getAddress().getState());
        address.append(" ");
        address.append(contact.getAddress().getZip());


        contactAddress.setText(address.toString());
        contactBirthday.setText("December 4 , 1980");
        contactEmail.setText(contact.getEmail());
        contactWebsite.setText(contact.getWebsite());





    }
}
