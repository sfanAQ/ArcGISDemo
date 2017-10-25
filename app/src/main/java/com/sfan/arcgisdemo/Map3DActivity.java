package com.sfan.arcgisdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.mapping.ArcGISScene;
import com.esri.arcgisruntime.mapping.ArcGISTiledElevationSource;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.Camera;
import com.esri.arcgisruntime.mapping.view.SceneView;

/**
 *
 */
public class Map3DActivity extends AppCompatActivity {

    private SceneView mSceneView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_3d);
        mSceneView = (SceneView) findViewById(R.id.sceneView);
        setUpMap();
        addTrailsLayer();
        Camera camera = new Camera(33.950896, -118.525341, 16000.0, 0.0, 50.0, 0.0);
        mSceneView.setViewpointCamera(camera);
    }

    @Override
    protected void onPause() {
        mSceneView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSceneView.resume();
    }

    private void setUpMap() {
        if (mSceneView != null) {
            Basemap.Type basemapType = Basemap.Type.IMAGERY_WITH_LABELS;
            ArcGISScene scene = new ArcGISScene(basemapType);
            mSceneView.setScene(scene);
            /* ** ADD ** */
            addTrailsLayer();
            /* ** ADD ** */
            setElevationSource(scene);
        }
    }

    private void addTrailsLayer() {
        String url = "https://services3.arcgis.com/GVgbJbqm8hXASVYi/arcgis/rest/services/Trails/FeatureServer/0";
        ServiceFeatureTable serviceFeatureTable = new ServiceFeatureTable(url);
        FeatureLayer featureLayer = new FeatureLayer(serviceFeatureTable);
        mSceneView.getScene().getOperationalLayers().add(featureLayer);
    }

    private void setElevationSource(ArcGISScene scene) {
        ArcGISTiledElevationSource elevationSource = new ArcGISTiledElevationSource("http://elevation3d.arcgis.com/arcgis/rest/services/WorldElevation3D/Terrain3D/ImageServer");
        scene.getBaseSurface().getElevationSources().add(elevationSource);
    }

}
