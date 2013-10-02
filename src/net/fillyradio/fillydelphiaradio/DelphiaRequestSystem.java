package net.fillyradio.fillydelphiaradio;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView; //TESTING

public class DelphiaRequestSystem extends Activity {

	WebView webView;
	
	
//NOTE EVERYTHING IN HERE IS A TEST
	//Everything seemed successful. Problems: we don't want to open the browser
	
	
	protected void onCreate(Bundle delphiaRedirect) {
		super.onCreate(delphiaRedirect);
		setContentView(R.layout.delphia_request_system);
		
		webView = (WebView) findViewById(R.id.delphia_r_s);
		webView.loadUrl("http://fillyradio.com/request/");
	}

	
	//Do we need this?
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}
}
