package com.sfan.arcgisdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polygon;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

/**
 *
 */
public class GraphicsActivity extends AppCompatActivity {

    private MapView mMapView;
    private GraphicsOverlay mGraphicsOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_2d);
        mMapView = (MapView) findViewById(R.id.mapView);
        setupMap();
        addPoint();
        addPolyline();
        addPolygon();
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
            //添加图形叠加层
            //Create graphics overlay and add it to map view
            mGraphicsOverlay = new GraphicsOverlay();
            mMapView.getGraphicsOverlays().add(mGraphicsOverlay);
        }
    }

    /**
     * 添加点图形
     */
    private void addPoint() {
        //Create a point geometry
        Point point = new Point(-118.29507, 34.13501, SpatialReferences.getWgs84());
        //Create point symbol with outline
        SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.rgb(226, 119, 40), 10.0f);
        pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 2.0f));
        //Create point graphic with geometry & symbol
        Graphic pointGraphic = new Graphic(point, pointSymbol);
        //Add point graphic to graphic overlay
        mGraphicsOverlay.getGraphics().add(pointGraphic);
    }

    /**
     * 添加一条线图形
     */
    private void addPolyline() {
        //Create polyline geometry
        PointCollection polylinePoints = new PointCollection(SpatialReferences.getWgs84());
        polylinePoints.add(new Point(-118.29026, 34.1816));
        polylinePoints.add(new Point(-118.26451, 34.09664));
        Polyline polyline = new Polyline(polylinePoints);
        //Create symbol for polyline
        SimpleLineSymbol polylineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 3.0f);
        //Create a polyline graphic with geometry and symbol
        Graphic polylineGraphic = new Graphic(polyline, polylineSymbol);
        //Add polyline to graphics overlay
        mGraphicsOverlay.getGraphics().add(polylineGraphic);
    }

    /**
     * 添加多边形图形
     */
    private void addPolygon() {
        //Create polygon geometry
        PointCollection polygonPoints = new PointCollection(SpatialReferences.getWgs84());
        polygonPoints.add(new Point(-118.27653, 34.15121));
        polygonPoints.add(new Point(-118.24460, 34.15462));
        polygonPoints.add(new Point(-118.22915, 34.14439));
        polygonPoints.add(new Point(-118.23327, 34.12279));
        polygonPoints.add(new Point(-118.25318, 34.10972));
        polygonPoints.add(new Point(-118.26486, 34.11625));
        polygonPoints.add(new Point(-118.27653, 34.15121));
        Polygon polygon = new Polygon(polygonPoints);
        //Create symbol for polygon with outline
        SimpleFillSymbol polygonSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, Color.rgb(226, 119, 40), new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 2.0f));
        //Create polygon graphic with geometry and symbol
        Graphic polygonGraphic = new Graphic(polygon, polygonSymbol);
        //Add polygon graphic to graphics overlay
        mGraphicsOverlay.getGraphics().add(polygonGraphic);
    }

}
