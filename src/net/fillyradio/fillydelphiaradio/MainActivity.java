package net.fillyradio.fillydelphiaradio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

//Meh listen: http://www.youtube.com/watch?v=S0mab1VZsy4

public class MainActivity extends Activity {

	Button streamOn, streamOff, refresh;
	ImageView logo, delphia;
	TextView nowPlaying;
	Uri radio;
	MediaPlayer liveStream;
	String song = "";
	int counter = 0;
	URLConnection npc;
	BufferedReader codeCheck;
	final int ALARM = Menu.FIRST;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Making all the clickables
		streamOn = (Button) findViewById(R.id.play);
		streamOff = (Button) findViewById(R.id.stop);
		nowPlaying = (TextView) findViewById(R.id.np);
		logo = (ImageView) findViewById(R.id.banner);
		delphia = (ImageView) findViewById(R.id.mascot);
		radio = Uri.parse("http://listen.fillyradio.com:8000/");
		liveStream = MediaPlayer.create(getApplicationContext(), radio);

		// Making the clickables clickable
		
		// On button
		streamOn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Plays the stream
				if (!liveStream.isPlaying())
					liveStream.start();
				
				//JS-PHP
			}

		});
		// Off button (to be replaced with a dynamic Play/Pause button
		streamOff.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				nowPlaying.setText("Aww don't leave.");
				if (liveStream.isPlaying()) {
					liveStream.stop();
					liveStream.reset();
					liveStream = MediaPlayer.create(getApplicationContext(),
							radio);
					try {
						liveStream.prepare();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		// Banner will redirect you to Fillydelphia Radio
		logo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent goToFDR = new Intent(
						"net.fillyradio.fillydelphiaradio.FILLYDELPHIARADIO");
				startActivity(goToFDR);
			}
		});
		
		// Delphia will redirect you to the Delphia request system...maybe or
		delphia.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//NEW IDEA: Make a bot to parse in requests via IRC
				//Blit the response of Delphia to the screen 1-10 objects
				//If you click on one of them send that one to Delphia
				Intent goToDelphia = new Intent(
						"net.fillyradio.fillydelphiaradio.DELPHIAREQUESTSYSTEM");
				startActivity(goToDelphia);
			}
		});

		//Pop-up for the alarm

		
		// Thread for song name
	}

	private void almpop() {
		//Makes an alarm clock
		//Do you want to turn the clock on?
		//What time do you want it set for?
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, ALARM, 0, "Alarm");
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case ALARM:
	            almpop();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	public static String[] nowPlaying() throws IOException{
		String[] s= new String[10];
		
		URL u = new URL("http://fillyradio.com:8000/played.html");
		URLConnection c = u.openConnection();
		InputStream r = c.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(r));
		for(String line; (line = reader.readLine()) != null;) System.out.println(line);
		//cut it up
//		s[0] = ;
		return s;
	}
}
