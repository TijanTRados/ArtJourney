package com.example.domi.ppij;

import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.jni.Vector3d;
import com.metaio.tools.io.AssetsManager;


public class showPictureAR extends ARViewActivity{
    private String mTrackingFile;

    private IGeometry mMan;

    @Override
    protected int getGUILayout() {
        return R.layout.activity_show_picture_ar;
    }

    @Override
    protected IMetaioSDKCallback getMetaioSDKCallbackHandler() {
        return null;
    }

    @Override
    protected void loadContents() {
        /*mTrackingFile = AssetsManager.getAssetPath(this,"metaio/TrackingData_MarkerlessFast.xml");

        boolean result = metaioSDK.setTrackingConfiguration(mTrackingFile);
        MetaioDebug.log("Tracking data loaded " + result );*/
        mTrackingFile = AssetsManager.getAssetPath(this,"metaio/TrackingData_Marker.xml");

        boolean result = metaioSDK.setTrackingConfiguration(mTrackingFile);
        MetaioDebug.log("Tracking data loaded " + result );

        String modelPath = AssetsManager.getAssetPath(this, "metaio/mlisa.obj");
        //String modelPath = AssetsManager.getAssetPath(this, "metaio/metaioman.md2");
        if(modelPath != null){
            mMan = metaioSDK.createGeometry(modelPath);
            if(mMan != null){
                mMan.setScale(new Vector3d(1.f, 1.0f, 1.f));
                mMan.setVisible(true);
                MetaioDebug.log("LoadedGeometry" + modelPath);
            }

        }
    }

    @Override
    protected void onGeometryTouched(IGeometry geometry) {

    }
}
