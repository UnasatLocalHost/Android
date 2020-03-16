package sr.unasat.bproduct.fragments;



import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import sr.unasat.bproduct.API.Adapter;
import sr.unasat.bproduct.API.Service;
import sr.unasat.bproduct.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends Fragment {
    private RecyclerView mRecyclerView;
    private Adapter adapter;
    private ArrayList<Service>mServiceList;
    private RequestQueue mRequestQueue;

    public FragmentThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_fragment_three, container, false);

        mRecyclerView = fragmentView.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new Adapter(getActivity(),mServiceList);

        mRecyclerView.setAdapter(adapter);

        mServiceList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(getActivity());
        parseJSON();
        return fragmentView;

    }
    private void parseJSON() {
        String url = "https://pixabay.com/api/?key=15541797-4a52799549debcb5b4de61031&q=muscle+cars&image_type=photo";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");

                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                //getting the json values
                                String CreatorName = hit.getString("user");
                                String imageUrl = hit.getString("webformatURL");
                                int likeCount = hit.getInt("likes");


                                mServiceList.add(new Service(imageUrl,CreatorName,likeCount));

                            }
                              adapter = new Adapter(getActivity(),mServiceList);
                              mRecyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        mRequestQueue.add(request);

    }
}