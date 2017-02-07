package com.example.singh.amazoncontactsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.singh.amazoncontactsexample.pojo.Contact;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG";
    public static final String BASE_URL = "https://s3.amazonaws.com/technical-challenge/Contacts.json";
    List<Contact> contactList = new ArrayList<>();
    ContactsAdapter adapter;

    ListView listViewContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewContacts = (ListView) findViewById(R.id.listviewContacts);
        adapter = new ContactsAdapter(MainActivity.this, R.layout.contact_list, contactList);
        listViewContacts.setAdapter(adapter);






        Observable<List<Contact>> contactsObservable = RetrofitHelper.Factory.createObservable();
        contactsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Contact>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Contact> contacts) {

                        Log.d(TAG, "onNext: ");
                        for(Contact c:contacts){
                            contactList.add(c);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });




    }
}
