package jdebu.github.io.camerainteraction;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
	private Camera camera = null;
	private CameraView cameraView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try{
			camera = Camera.open();
		}catch (Exception e){
			Log.e(null,"failed to get cameras: "+ e.getMessage());
		}
		if (camera !=null){
			cameraView = new CameraView(this,camera);
			FrameLayout camera_view = (FrameLayout)findViewById(R.id.camera_view);
			camera_view.addView(cameraView);
		}

		//btn to close the application
		ImageButton imgClose = (ImageButton)findViewById(R.id.imgClose);
		imgClose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				System.exit(0);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
