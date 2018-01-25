package com.shq.myapplication.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shq.myapplication.R;


public class LoadingDialog extends Dialog {
	private TextView mTip;
	private ProgressBar mBar;

	public LoadingDialog(Context context) {
		super(context);
		init();
	}

	public LoadingDialog(Context context, int theme) {
		super(context, theme);
		init();
	}
	public void setTipText(String tip) {
		mTip.setText(tip);
	}
	private void init() {
		View view = View.inflate(getContext(), R.layout.dialog_loading,
				null);
		mTip = (TextView) view
				.findViewById(R.id.dialog_custom_loading_txt_progress_msg);
		mBar = (ProgressBar) view
				.findViewById(R.id.dialog_custom_loading_pb_progress);
		mBar.setIndeterminateDrawable(getContext().getResources().getDrawable(
				R.drawable.rotate_progress_white));
		this.setContentView(view);

	}

}
