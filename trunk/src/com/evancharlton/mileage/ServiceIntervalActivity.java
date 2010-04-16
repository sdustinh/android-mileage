package com.evancharlton.mileage;

import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;

import com.evancharlton.mileage.dao.Dao;
import com.evancharlton.mileage.dao.ServiceInterval;
import com.evancharlton.mileage.provider.FillUpsProvider;
import com.evancharlton.mileage.provider.tables.ServiceIntervalsTable;
import com.evancharlton.mileage.views.CursorSpinner;

public class ServiceIntervalActivity extends BaseFormActivity {

	private final ServiceInterval mInterval = new ServiceInterval(new ContentValues());
	private CursorSpinner mVehicles;
	private CursorSpinner mVehicleTypes;
	private EditText mTitle;
	private EditText mDescription;
	private EditText mDistance;
	private EditText mDuration;
	private EditText mOdometer;
	private EditText mDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.service_interval);
	}

	@Override
	protected Dao getDao() {
		return mInterval;
	}

	@Override
	protected String[] getProjectionArray() {
		return ServiceIntervalsTable.getFullProjectionArray();
	}

	@Override
	protected Uri getUri(long id) {
		return ContentUris.withAppendedId(Uri.withAppendedPath(FillUpsProvider.BASE_URI, ServiceIntervalsTable.SERVICE_INTERVAL_URI), id);
	}

	@Override
	protected void initUI() {
		mVehicles = (CursorSpinner) findViewById(R.id.vehicles);
		mVehicleTypes = (CursorSpinner) findViewById(R.id.types);
		mTitle = (EditText) findViewById(R.id.title);
		mDescription = (EditText) findViewById(R.id.description);
		mDistance = (EditText) findViewById(R.id.distance);
		mDuration = (EditText) findViewById(R.id.duration);
		mOdometer = (EditText) findViewById(R.id.odometer);
		mDate = (EditText) findViewById(R.id.date);
	}

	@Override
	protected void populateUI() {
		mTitle.setText(mInterval.getTitle());
		mDescription.setText(mInterval.getDescription());
		mDistance.setText(String.valueOf(mInterval.getDistance()));
		mDuration.setText(String.valueOf(mInterval.getDuration()));
		mOdometer.setText(String.valueOf(mInterval.getStartOdometer()));
		mDate.setText(String.valueOf(mInterval.getStartDate()));
	}

	@Override
	protected void setFields() {
		// TODO: Error checking
		mInterval.setTitle(mTitle.getText().toString());
		mInterval.setDescription(mDescription.getText().toString());
	}

}
