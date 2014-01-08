package net.fillyradio.fillydelphiaradio;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DelphiaRequestSystem extends Activity {
	
	protected void onCreate(Bundle delphiaRedirect) {
		super.onCreate(delphiaRedirect);
		setContentView(R.layout.delphia_request_system);
		//Make 10 objects (buttons/text)
		//If there's less than 10 don't blit the ones that don't exist
		//
		//MainActivity.nowPlaying();
	}

	
	//Do we need this?
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}
}
