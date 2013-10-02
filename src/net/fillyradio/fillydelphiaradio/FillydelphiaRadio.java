package net.fillyradio.fillydelphiaradio;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class FillydelphiaRadio extends Activity {

	WebView fDR;
	@Override
	protected void onCreate(Bundle mainSiteRedirect) {
		super.onCreate(mainSiteRedirect);
		setContentView(R.layout.fillyradio);

		fDR = (WebView) findViewById(R.id.fdr_site);
		fDR.loadUrl("http://www.fillydelphiaradio.net");
	}

}
