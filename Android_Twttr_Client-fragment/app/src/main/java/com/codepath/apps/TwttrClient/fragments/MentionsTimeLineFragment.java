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

public class MentionsTimeLineFragment extends TweetListFragment {
    private TwttrClient client;
    private String max_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = RestApplication.getRestClient();
        max_id = null;
        getData();
    }

    public void getData() {
        client.getMentionsTimeline(100,  new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                Log.d("Rohan", "onSuccess: ");
                addAll(TwttrModel.fromJSONArr(jsonArray));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("Rohan", "Error: " + errorResponse.toString());
            }
        });

    }

}
