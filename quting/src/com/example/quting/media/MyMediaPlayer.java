/**
 * 
 */
package com.example.quting.media;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.media.MediaPlayer;

/**
 * @author 播放音乐
 */
public class MyMediaPlayer {
	MediaPlayer player = null;
	List<String> fileList;
	int index = 0;
	private boolean palystatus =false;//true播放   false停止
	
	public MyMediaPlayer(List<String> fileList) {
		super();
		this.fileList = new ArrayList<String>();
		this.fileList.addAll(fileList);

	}
	
	public MyMediaPlayer(String file) {
		super();
		this.fileList = new ArrayList<String>();
		this.fileList.add(file);

	}
	
	public int getDuration(){
		return player.getDuration();
	}
	
	public int getCurrentPosition(){
		return player.getCurrentPosition();
	}
	
	public void start() throws IllegalStateException, IOException {
		if (this.fileList.size() < 1)
			return;

		if (player == null)
			player = new MediaPlayer();
		if (player.isPlaying())
			player.reset();
		player.setDataSource(this.fileList.get(index++));
		player.prepare();
		player.start();
		player.setOnCompletionListener(completionListener);
		player.setOnErrorListener(errorListener);
		setPalystatus(true);
	}

	public void stop() {
		if (player != null) {
			player.stop();
			player.release();
			player = null;
			setPalystatus(false);
		}
	}
	
	public void next(){
		if (index < fileList.size())
			try {
				start();

			} catch (Exception e) {
				player.release();
				player = null;
			}		
	}
	
	public void reset() {
		if (player != null) {
			player.reset();
		}
	}

	public void pause() {
		if (player != null) {
			if (player.isPlaying()){
				player.pause();
				setPalystatus(false);
			}
			else{
				player.start();
				setPalystatus(true);
			}
		}
	}

	MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
		@Override
		public void onCompletion(MediaPlayer mp) {
			if (index < fileList.size())
				try {
					start();
					setPalystatus(true);
				} catch (Exception e) {
					player.stop();
					player.release();
					player = null;
					setPalystatus(false);
				}
			else {
				player.stop();
				player.release();
				player = null;
				setPalystatus(false);
			}
		}
	};

	MediaPlayer.OnErrorListener errorListener = new MediaPlayer.OnErrorListener() {
		@Override
		public boolean onError(MediaPlayer mp, int what, int extra) {
			player.release();
			player = null;
			setPalystatus(false);
			return false;
		}
	};

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isPalystatus() {
		return palystatus;
	}

	public void setPalystatus(boolean palystatus) {
		this.palystatus = palystatus;
	}
}
