package com.example.androidlocation;

import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {

	TextView Locationinfo;

	private LocationManager locationManager;
	private static final long MIN_TIME = 400;
	private static final float MIN_DISTANCE = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Locationinfo = (TextView) findViewById(R.id.location);
		findLocation();

	}

	private void findLocation() {
		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
		boolean enabledGPS = service
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		boolean enabledWiFi = service
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		if (!enabledGPS) {
			new AlertDialog.Builder(this)
					.setMessage(
							"GPS and NETWORK is not available would you like to open Settings")
					.setCancelable(false)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									Intent intent = new Intent(
											android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
									startActivity(intent);
								}
							}).setNegativeButton("No", null).show();
		} else {
			// To Close the toast After 1 Second

			locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE,
					(LocationListener) this);
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE,
					this);
			// You can also use LocationManager.GPS_PROVIDER and
			// LocationManager.PASSIVE_PROVIDER
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLocationChanged(Location location) {

		try {
			LatLng latLng = new LatLng(location.getLatitude(),
					location.getLongitude());
			double latitude = location.getLatitude();
			// Getting longitude of the current location
			double longitude = location.getLongitude();

			Geocoder geo = new Geocoder(this.getApplicationContext(),
					Locale.getDefault());
			List<Address> addresses = geo.getFromLocation(latitude, longitude,
					1);
			if (addresses.isEmpty()) {
				Toast.makeText(getApplicationContext(), "Address not found",
						Toast.LENGTH_LONG).show();
			} else {
				if (addresses.size() > 0) {
					Toast.makeText(
							getApplicationContext(),
							"Your Location is: "
									+ addresses.get(0).getFeatureName() + ", "
									+ addresses.get(0).getLocality() + ", "
									+ addresses.get(0).getAdminArea() + ", "
									+ addresses.get(0).getCountryName() + "",
							Toast.LENGTH_LONG).show();
					// Toast.makeText(getApplicationContext(), "Address:- " +
					// addresses.get(0).getFeatureName() +
					// addresses.get(0).getAdminArea() +
					// addresses.get(0).getLocality(),
					// Toast.LENGTH_LONG).show();
					String Location = "lat: "+latitude+"\n"+"long: "+longitude+"\n"+ addresses.get(0).getFeatureName() + ", "
							+ addresses.get(0).getLocality() + ", "
							+ addresses.get(0).getAdminArea() + ", "
							+ addresses.get(0).getCountryName() + "";
					Locationinfo.setText(Location);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // getFromLocation() may sometimes fail
		}
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}
}
