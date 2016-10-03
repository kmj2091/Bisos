package com.kim.bisos.Map;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kim.bisos.R;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapCompassManager;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.nmapmodel.NMapPlacemark;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;

public class NaverMyLocation extends NMapActivity implements NMapView.OnMapStateChangeListener {

    // API-KEY
    public static final String API_KEY = "uRUsKwqletoSfPmk6v3L";
    // 네이버 맵 객체
    NMapView mMapView = null;
    // 맵 컨트롤러
    NMapController mMapController = null;
    // 맵을 추가할 레이아웃
    LinearLayout MapContainer;


    // 오버레이 관리자
    private NMapOverlayManager mOverlayManager;

    private NMapMyLocationOverlay mMyLocationOverlay;
    private NMapLocationManager mMapLocationManager;
    private NMapCompassManager mMapCompassManager;

    private NMapViewerResourceProvider mMapViewerResourceProvider;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);

            // 네이버 지도를 넣기 위한 LinearLayout 컴포넌트
            MapContainer = (LinearLayout) findViewById(R.id.MapContainer);

            // 네이버 지도 객체 생성
            mMapView = new NMapView(this);

            // 지도 객체로부터 컨트롤러 추출
            mMapController = mMapView.getMapController();

            // 네이버 지도 객체에 APIKEY 지정
            mMapView.setApiKey(API_KEY);

            // 생성된 네이버 지도 객체를 LinearLayout에 추가시킨다.
            MapContainer.addView(mMapView);

            // 지도를 터치할 수 있도록 옵션 활성화
            mMapView.setClickable(true);

            // 확대/축소를 위한 줌 컨트롤러 표시 옵션 활성화
            mMapView.setBuiltInZoomControls(true, null);

            // set data provider listener
            super.setMapDataProviderListener(onDataProviderListener);

            // 지도에 대한 상태 변경 이벤트 연결
            mMapView.setOnMapStateChangeListener(this);

            // create resource provider
            mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
            mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);

            // location manager
            mMapLocationManager = new NMapLocationManager(this);
            mMapLocationManager.setOnLocationChangeListener(onMyLocationChangeListener);

            // compass manager
            mMapCompassManager = new NMapCompassManager(this);

            // create my location overlay
            mMyLocationOverlay = mOverlayManager.createMyLocationOverlay(mMapLocationManager, mMapCompassManager);

            startMyLocation();
        }

        @Override
        public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {
            if (errorInfo == null) { // success
                startMyLocation();//현재위치로 이동
                mMapController.setMapCenter(new NGeoPoint(126.978371, 37.5666091), 14);
            } else { // fail
                android.util.Log.e("NMAP", "onMapInitHandler: error=" + errorInfo.toString());
            }
        }

        @Override
        public void onZoomLevelChange(NMapView mapView, int level) {
        }


        @Override
        public void onMapCenterChange(NMapView mapView, NGeoPoint center) {
        }

        @Override
        public void onAnimationStateChange(NMapView arg0, int animType, int animState) {
        }

        @Override
        public void onMapCenterChangeFine(NMapView arg0) {
        }



        private void startMyLocation() {
                 boolean isMyLocationEnabled = mMapLocationManager.enableMyLocation(true);
            if (!isMyLocationEnabled) {
                Toast.makeText(
                        NaverMyLocation.this,
                        "시스템에서 GPS설정을 활성화 하세요.", Toast.LENGTH_LONG).show();

                Intent goToSettings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(goToSettings);
                finish();
            } else {



            }
        }

        private void stopMyLocation() {
            if (mMyLocationOverlay != null) {
                mMapLocationManager.disableMyLocation();

                if (mMapView.isAutoRotateEnabled()) {
                    mMyLocationOverlay.setCompassHeadingVisible(false);

                    mMapView.setAutoRotateEnabled(false, false);

                    MapContainer.requestLayout();
                }
                onPause();
            }
        }

        private final NMapActivity.OnDataProviderListener onDataProviderListener = new NMapActivity.OnDataProviderListener() {

            @Override
            public void onReverseGeocoderResponse(NMapPlacemark placeMark, NMapError errInfo) {

                if (errInfo != null) {
                    Log.e("myLog", "Failed to findPlacemarkAtLocation: error=" + errInfo.toString());
                    Toast.makeText(NaverMyLocation.this, errInfo.toString(), Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(NaverMyLocation.this, placeMark.toString(), Toast.LENGTH_LONG).show();

                }
            }
        };


        private final NMapLocationManager.OnLocationChangeListener onMyLocationChangeListener= new NMapLocationManager.OnLocationChangeListener() {

            @Override
            public boolean onLocationChanged(NMapLocationManager locationManager, NGeoPoint myLocation) {

                if (mMapController != null) {
                    mMapController.animateTo(myLocation);
                }
                Log.d("myLog", "myLocation  lat " + myLocation.getLatitude());
                Log.d("myLog", "myLocation  lng " + myLocation.getLongitude());


                findPlacemarkAtLocation(myLocation.getLongitude(), myLocation.getLatitude());
                //위도경도를 주소로 변환
                return true;
            }

            @Override
            public void onLocationUpdateTimeout(NMapLocationManager locationManager) {

                Toast.makeText(NaverMyLocation.this,"현재위치를 검색 할 수 없습니다.",
                        Toast.LENGTH_LONG).show();

            }
            @Override
            public void onLocationUnavailableArea(NMapLocationManager locationManager, NGeoPoint myLocation) {

                Toast.makeText(NaverMyLocation.this,
                        "현재위치는 지도상에 표시할 수 없는 지역입니다.", Toast.LENGTH_LONG).show();
                stopMyLocation();
            }
        };
    }

















