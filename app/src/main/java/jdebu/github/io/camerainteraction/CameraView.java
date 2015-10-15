package jdebu.github.io.camerainteraction;

import android.content.Context;

import android.content.res.Configuration;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by Glup on 15/10/15.
 */
public class CameraView extends SurfaceView implements
		SurfaceHolder.Callback{
	private SurfaceHolder holder;
	private Camera camera;
	public CameraView(Context context, Camera camera) {
		super(context);
		this.camera=camera;
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
			this.camera.setDisplayOrientation(90);

		}else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
			this.camera.setDisplayOrientation(180);
		}
		this.holder = getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try{
			//when the surface is created, we can set the camera to draw images in this surfaceholder
			camera.setPreviewDisplay(holder);
			camera.startPreview();
		}catch (IOException e){
			Log.e("ERROR","al crear sufacecreated "+e.getMessage());
		}

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		//before changing the application orientation, you need to stop the preview, rotate and then start it again
		if(this.holder.getSurface() == null)//check if the surface is ready to receive camera data
			return;

		try{
			camera.stopPreview();
		} catch (Exception e){
			//this will happen when you are trying the camera if it's not running
			Log.e(null,"cunado la camara no esta en memoria corriendo");
		}

		//now, recreate the camera preview
		try {

			camera.setPreviewDisplay(this.holder);
			camera.startPreview();
		}catch (IOException e){
			Log.e("Error","carmera error on surfaceChanged "+e.getMessage());
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//our app has only one screen, so we'll destroy the camera in the surface
		//if you are unsing with more screens, please move this code your activity
		camera.stopPreview();
		camera.release();
	}
}
