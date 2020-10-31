package cwd.shiinfo.tablayoutwithjsondata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    public String URL = "https://www.simplifiedcoding.net/demos/view-flipper/heroes.php";
    ArrayList<Hero> tabtitties;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabtitties=new ArrayList<>();
        loadJson();

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.frameLayout);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void loadJson() {
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("heroes");
                            for (int i = 0; i <= jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                Hero hero = new Hero(jsonObject1.getString("name"),jsonObject1.getString("imageurl"));
                                tabtitties.add(hero);
                                tabLayout.addTab(tabLayout.newTab().setCustomView(createTabItemView(tabtitties.get(i).getName(),tabtitties.get(i).getImageurl())));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                    }
                }
        ) {

        };
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
    private View createTabItemView(String imgUri, String name) {
        View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_icon,null,false);
        ImageView imageView = (ImageView)view.findViewById(R.id.title_image);
        TextView textView=(TextView)view.findViewById(R.id.title) ;
        TabLayout.LayoutParams params = new TabLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setText(name);
        Picasso.with(getApplicationContext()).load(imgUri).into(imageView);
        return view;
    }


    }
