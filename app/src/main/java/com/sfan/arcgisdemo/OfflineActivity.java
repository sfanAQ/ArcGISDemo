package com.sfan.arcgisdemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.MobileMapPackage;
import com.esri.arcgisruntime.mapping.view.MapView;

import java.io.File;

public class OfflineActivity extends AppCompatActivity {

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_2d);
        mMapView = (MapView) findViewById(R.id.mapView);
        setupMobileMap();
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

    private void setupMobileMap() {
        if (mMapView != null) {
            File mmpkFile = new File(Environment.getExternalStorageDirectory(), "devlabs-package.mmpk");
            final MobileMapPackage mapPackage = new MobileMapPackage(mmpkFile.getAbsolutePath());
            mapPackage.addDoneLoadingListener(new Runnable() {
                @Override
                public void run() {
                    // Verify the file loaded and there is at least one map
                    if (mapPackage.getLoadStatus() == LoadStatus.LOADED && mapPackage.getMaps().size() > 0) {
                        mMapView.setMap(mapPackage.getMaps().get(0));
                    } else {
                        // Error if the mobile map package fails to load or there are no maps included in the package
                        setupMap();
                    }
                }
            });
            mapPackage.loadAsync();
        }
    }

    private void setupMap() {
        if (mMapView != null) {
            Basemap.Type basemapType = Basemap.Type.STREETS_VECTOR;
            double latitude = 34.05293;
            double longitude = -118.24368;
            int levelOfDetail = 11;
            ArcGISMap map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
            mMapView.setMap(map);
        }
    }

}
