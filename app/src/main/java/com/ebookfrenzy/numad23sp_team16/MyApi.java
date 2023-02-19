package com.ebookfrenzy.numad23sp_team16;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



public class MyApi extends AppCompatActivity {
    //to display search results in a recyclerview
    RecyclerView recyclerView;
    //1.save the result in each search, search Peru, search China,they all appear in a recyclerview

    //2. the list below can also be used to saveInstance state in rotation **!!!!!!!!!**core for save instance state
    List<Country> searchResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_api_recyclerview);
        //get saved state of fetched list of countries
        if(savedInstanceState!= null){
            searchResults = savedInstanceState.getParcelableArrayList("countryList");
            recyclerView = findViewById(R.id.recycler);
            CountryAdapter adapter = new CountryAdapter(searchResults);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            fetchCountry();
        }
    }

    private void fetchCountry(){
        String baseUrl = "https://restcountries.com/v2/";
        recyclerView = this.findViewById(R.id.recycler);
        CountryAdapter adapter = new CountryAdapter(searchResults);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EditText userInput = findViewById(R.id.edit);
        //Retrofit does the magic in the following part to get HTTP request for us
        // enqueue method is used to asynchronously execute the request and handle the response using a callback, create a new thread automatically
        //make a web service call
        Retrofit retrofit = RetrofitClient.getClient(baseUrl);
        RestCountriesService service = retrofit.create(RestCountriesService.class);
        //click button to start fetching
        Button searchButton = findViewById(R.id.search_btn);
        searchButton.setOnClickListener(v -> {
            //update the progress dialog message

            //get input country name
            String countryToSearch = userInput.getText().toString().trim();
            //send the get request behind the scene
            Call<List<Country>> call = service.getCountries(countryToSearch);
            call.enqueue(new Callback<List<Country>>() {
                @Override
                public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                    if (response.isSuccessful()) {
                        List<Country> countries = response.body();
                        assert countries != null;
                        searchResults.addAll(countries);
                        adapter.notifyDataSetChanged();
                    } else {
                        // handle error
                        Log.e("code", "Error code: " + response.code());
                    }
                }
                @Override
                public void onFailure(Call<List<Country>> call, Throwable t) {
                    // handle failure
                    Log.e("RestCountriesService", "Error: " + t.getMessage());
                }
            });
        });
    }
    //save a list object in outState by outState.putParcelableArrayList()
    //ArrayList<? extends Parcelable> , Country object should extends Parcelable, add this implementation in Country.java
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("countryList", new ArrayList<Country>(searchResults));
    }

}