package dev.ash.youtubeplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class StandAloneActivity extends Activity implements View.OnClickListener {

	public static final String GOOGLE_API_KEY = "AIzaSyAW_icQTkRB0ZQLAlZrclhfYJ4MQsnY6QQ";
	public static final String YOUTUBE_VIDEO_ID = "cO7e0HOlgKQ";
	public static final String YOUTUBE_PLAYLIST_ID = "onJJ3BVjZW7LFiBUpg5jo7CmzfVktV-o";
	
	private Button btnPlay;
	private Button btnPlaylist;
	
	@Override
	protected void onCreate(Bundle savedinstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedinstanceState);
		setContentView(R.layout.stand_alone);
		btnPlay = (Button) findViewById(R.id.btnStart);
		btnPlaylist = (Button) findViewById(R.id.btnPlaylist);
		btnPlay.setOnClickListener(this); 
		btnPlaylist.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		if(v==btnPlay){
			//play single video
			intent = YouTubeStandalonePlayer.createVideoIntent(StandAloneActivity.this, GOOGLE_API_KEY, YOUTUBE_VIDEO_ID);
		}else if(v == btnPlaylist){
			//play the list of videos
			intent = YouTubeStandalonePlayer.createPlaylistIntent(StandAloneActivity.this, GOOGLE_API_KEY, YOUTUBE_PLAYLIST_ID);
		}
		if (intent != null){
			startActivity(intent);
		}
	}
	
}