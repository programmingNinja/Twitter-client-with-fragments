package com.codepath.apps.TwttrClient.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codepath.apps.TwttrClient.RestApplication;
import com.codepath.apps.TwttrClient.TwttrClient;
import com.codepath.apps.TwttrClient.models.TwttrModel;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserTimeLineFragment extends TweetListFragment {

    private TwttrClient client;
    private String screenName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = RestApplication.getRestClient();
        screenName = null;
        getData();
    }

    public static UserTimeLineFragment newInstance(String screenName) {
        UserTimeLineFragment fragmentDemo = new UserTimeLineFragment();
        Bundle args = new Bundle();
        args.putString("screen_name", screenName);
        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }

    public void getData()
    {
        screenName = getArguments().getString("screen_name");
        client.getUserTimeLine(100, screenName, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                Log.d("Rohan", "onSuccess: ");
                //max_id = jsonArray.getJSONObject(jsonArray.length() - 1).getString("id_str");

                //adapter.notifyDataSetChanged();
                //swipeRefreshLayout.setRefreshing(false);
                addAll(TwttrModel.fromJSONArr(jsonArray));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("Rohan", "Error: " + errorResponse.toString());
            }
        });

    }

}
