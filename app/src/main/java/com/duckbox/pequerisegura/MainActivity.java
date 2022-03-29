package com.duckbox.pequerisegura;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String urlJsonArry = "http://.../cam_list.json";
    private String jsonResponse;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView mList;
    private ProgressDialog progressDialog;
    private List<Camera> cameraList;
    private RecyclerView.Adapter adapter;
    private DividerItemDecoration dividerItemDecoration;
    private String jsonLink;

    private TextView txt;
    ProgressBar pb;
    private static String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_main);
        cameraList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        adapter = new CameraAdapter(getApplicationContext(), cameraList);
        mList = (RecyclerView) findViewById(R.id.main_list);
        pb = (ProgressBar) findViewById(R.id.pb);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.VERTICAL,false);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(gridLayoutManager);
        mList.setAdapter(adapter);
        getData();

    }


    /**
     * Method to make json array request where response starts with [
     */
    private void getData() {
       showpDialog();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlJsonArry, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);

                        Camera camera = new Camera();
                       camera.setCamera(jsonObject.getString("nome"));
                        camera.setLink_(jsonObject.getString("link"));

                        cameraList.add(camera);
                    } catch (JSONException e) {
                        e.printStackTrace();
                       hidepDialog();
                    }
                }
                adapter.notifyDataSetChanged();
              hidepDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                hidepDialog();
            }
        });
        jsonArrayRequest.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    public void hidepDialog(){
progressDialog.dismiss();
        int resId = R.anim.anim_lay;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        mList.setLayoutAnimation(animation);
      //  pb.setVisibility(View.GONE);
    }

    public void showpDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando...");
        progressDialog.show();
        progressDialog.setCancelable(false);
//pb.setVisibility(View.VISIBLE);

    }

}
