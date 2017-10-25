package com.sfan.arcgisdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn2D, R.id.btn3D, R.id.btnWeb, R.id.btnCreate, R.id.btnPrivate, R.id.btnSearch, R.id.btnOffline, R.id.btnLocation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn2D:
                startActivity(new Intent(MainActivity.this, Map2DActivity.class));
                break;
            case R.id.btn3D:
                startActivity(new Intent(MainActivity.this, Map3DActivity.class));
                break;
            case R.id.btnWeb:
                startActivity(new Intent(MainActivity.this, MapWebActivity.class));
                break;
            case R.id.btnCreate:
                startActivity(new Intent(MainActivity.this, GraphicsActivity.class));
                break;
            case R.id.btnPrivate:
                startActivity(new Intent(MainActivity.this, PrivateActivity.class));
                break;
            case R.id.btnSearch:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                break;
            case R.id.btnOffline:
                startActivity(new Intent(MainActivity.this, OfflineActivity.class));
                break;
            case R.id.btnLocation:
                startActivity(new Intent(MainActivity.this, LocationActivity.class));
                break;
        }
    }
}
