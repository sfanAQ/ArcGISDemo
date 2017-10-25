package com.sfan.arcgisdemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.location.LocationDataSource;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;

public class LocationActivity extends AppCompatActivity {

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_2d);
        mMapView = (MapView) findViewById(R.id.mapView);
        setupMap();
    }

    @Override
    protected void onPause() {
        mMapView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.resume();
    }

    private void setupMap() {
        if (mMapView != null) {
            Basemap.Type basemapType = Basemap.Type.STREETS_VECTOR;
            double latitude = 34.05293;
            double longitude = -118.24368;
            int levelOfDetail = 11;
            ArcGISMap map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
            mMapView.setMap(map);
            LocationDisplay locationDisplay = mMapView.getLocationDisplay();
            locationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.RECENTER);
            locationDisplay.startAsync();
            locationDisplay.addLocationChangedListener(new LocationDisplay.LocationChangedListener() {
                @Override
                public void onLocationChanged(LocationDisplay.LocationChangedEvent locationChangedEvent) {
                    LocationDataSource.Location location = locationChangedEvent.getLocation();
                    Point point = location.getPosition();
                    Log.i("sss=", point.toString());
                }
            });
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    //位置信息变化时触发
                    if (location != null) {
                        Log.i("GPS==", String.valueOf(location.getLongitude()) + "," + String.valueOf(location.getLatitude()));
                    } else {
                        Log.i("GPS==", "空的");
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    //GPS状态变化时触发
                    Log.i("GPS==", "状态变化");
                }

                @Override
                public void onProviderEnabled(String provider) {
                    //GPS开启时触发
                    Log.i("GPS==", "开启");
                }

                @Override
                public void onProviderDisabled(String provider) {
                    //GPS禁用时触发
                    Log.i("GPS==", "禁用");
                }
            });
        }
    }

}
