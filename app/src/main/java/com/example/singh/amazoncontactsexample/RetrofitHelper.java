package com.example.singh.amazoncontactsexample;

import com.example.singh.amazoncontactsexample.pojo.Contact;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by singh on 07-Feb-17.
 */

public class RetrofitHelper {
    public static final String BASE_URL = "https://s3.amazonaws.com";

    public static class Factory{

        public static Retrofit create(){
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();

        }

        public static Observable<List<Contact>> createObservable(){
            Retrofit retrofit = create();
            AmazonContacts contacts = retrofit.create(AmazonContacts.class);
            return contacts.getContacts();
        }


    }


    public interface AmazonContacts{

        @GET("/technical-challenge/Contacts.json")
        rx.Observable<List<Contact>> getContacts();
    }
}
