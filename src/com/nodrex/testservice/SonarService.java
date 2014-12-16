package com.nodrex.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

public class SonarService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new MyAsyncTask().execute();
		return START_STICKY;
	}

	class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			int i = 11;
			while (i > 0) {
				i--;
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				publishProgress(i);
			}
			// stopSelf();
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			Toast.makeText(SonarService.this, " Service Started and is going: "+ values[0], Toast.LENGTH_SHORT).show();
			super.onProgressUpdate(values);
		}
	}
	
	@Override
	public void onDestroy() {
		Toast.makeText(SonarService.this, " Service finished", Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}

}