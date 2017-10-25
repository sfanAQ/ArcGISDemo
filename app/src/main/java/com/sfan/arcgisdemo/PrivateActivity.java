package com.sfan.arcgisdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.esri.arcgisruntime.layers.ArcGISMapImageLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.security.AuthenticationManager;
import com.esri.arcgisruntime.security.DefaultAuthenticationChallengeHandler;
import com.esri.arcgisruntime.security.OAuthConfiguration;

import java.net.MalformedURLException;

/**
 *
 */
public class PrivateActivity extends AppCompatActivity {

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_2d);
        mMapView = (MapView) findViewById(R.id.mapView);
        setupMap();
        setOAuthConfiguration();
        addPrivateLayer();
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

        }
    }

    private void setOAuthConfiguration() {
        // ADD OAuth Configuration OAuth配置
        String clientId = getResources().getString(R.string.client_id);
        String redirectUri = getResources().getString(R.string.redirect_uri);
        try {
            // configuration for OAuth access information against arcgis.com portal
            OAuthConfiguration oAuthConfiguration = new OAuthConfiguration("https://www.arcgis.com", clientId, redirectUri);
            // challenge handler using this activity to launch browser OAuth login
            DefaultAuthenticationChallengeHandler authenticationChallengeHandler = new DefaultAuthenticationChallengeHandler(this);
            // Authentication Manager manages authentication for OAuth configuration
            AuthenticationManager.setAuthenticationChallengeHandler(authenticationChallengeHandler);
            AuthenticationManager.addOAuthConfiguration(oAuthConfiguration);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void addPrivateLayer() {
        // ADD Private Layer
        ArcGISMapImageLayer traffic = new ArcGISMapImageLayer("https://traffic.arcgis.com/arcgis/rest/services/World/Traffic/MapServer");
        mMapView.getMap().getOperationalLayers().add(traffic);
    }

}
