package epeyk.mobile.erunappgdx;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import epeyk.mobile.erunbase.component.core.eventlog.EventLog;
import epeyk.mobile.erunbase.component.core.page.PageInfo;
import epeyk.mobile.erunbase.component.core.page.viewGDX.ViewBaseGDX;

public class ActivityMain extends ActivityPageBase {

	private ViewBaseGDX viewBaseGDX;
	private RelativeLayout rl_base;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		rl_base = (RelativeLayout) findViewById(R.id.activityMain_rlBase);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		// config.useWakelock = true;
		// config.useGL20 = true;
		viewBaseGDX = new ViewBaseGDX(this) {
			@Override
			public void initScrean() {
				try {
					initialCurrentPage(ActivityMain.class, ActivityMain.this.getIntent().getExtras());
				} catch (JSONException e) {
					EventLog.log(ActivityMain.this, "ActivityMain.initialCurrentPage", e.getMessage());
					e.printStackTrace();
				}
			}
		};
		View view = initializeForView(viewBaseGDX, config);
		rl_base.addView(view);
	}

	@Override
	protected void onLayerCreated(Table layer, int zIndex, int layerCount, JSONObject jsObject) {
		super.onLayerCreated(layer, zIndex, layerCount, jsObject);
		if (jsObject != null && jsObject.has("move") && jsObject.optBoolean("move")) {
			float speed = jsObject.has("speed") ? Float.valueOf(jsObject.optString("speed")) : 0;
			String dir = jsObject.has("dir") ? jsObject.optString("dir") : "";
		}
		viewBaseGDX.addLayer(layer);
	}

	@Override
	protected void onPageCreatedComplete(PageInfo pageInfo) {
		super.onPageCreatedComplete(pageInfo);
		viewBaseGDX.onPageCreated(pageInfo);
	}

	@Override
	protected void onAllPageCreated(int count) {
		super.onAllPageCreated(count);
		viewBaseGDX.onAllPageCreated(count);
	}
}
