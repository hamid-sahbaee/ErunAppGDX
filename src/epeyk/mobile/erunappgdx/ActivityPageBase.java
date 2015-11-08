package epeyk.mobile.erunappgdx;

import org.json.JSONException;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import epeyk.mobile.erunbase.component.core.page.InterfacesHolderBase.INotificationView;
import epeyk.mobile.erunbase.component.core.page.InterfacesHolderBase.IStoreView;
import epeyk.mobile.erunbase.component.core.page.PageBaseGDX;
import epeyk.mobile.erunbase.component.core.page.PageInfo;
import epeyk.mobile.erunbase.component.store.BlockInfo;
import epeyk.mobile.erunbase.component.store.ProductInfo;

public class ActivityPageBase extends PageBaseGDX {
	public static StringBuffer SESSIONID;
	private Class<?> activityClass;
	private int scrollWidth = 20000;
	protected static boolean runpattern = false;
	final int REQUEST_FOR_CHILD_ID = 50;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onReadPageInfo(PageInfo pageInfo) {
		super.onReadPageInfo(pageInfo);
		// TODO Scroll width for parallax
		interfaces.setStore(getIStoreView());
		interfaces.setINotification(getINotificationView());
	}

	private INotificationView getINotificationView() {
		return new INotificationView() {

			@Override
			public int getRowItemLayoutID() {
				return -1;
			}

			@Override
			public int getDetailLayoutID() {
				return -1;
			}
		};
	}

	private IStoreView getIStoreView() {
		return new IStoreView() {

			@Override
			public View initialSectionSocialButtons(ProductInfo product) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public View initialSectionRelatedProducts(ProductInfo product) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public View initialSectionProducerProducts(ProductInfo product) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public View initialSectionProducerInfo(ProductInfo product) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public View initialSectionGallery(ProductInfo product) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public View initialSectionDescription(ProductInfo product) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public View initialSectionComments(ProductInfo product) {
				// TODO Auto-generated method stub
				return null;
			}

			public View initialSectionBasicInfo(ProductInfo product) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public View initialSectionBadges(ProductInfo product) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public View createBlockProduct(BlockInfo block, ViewGroup parent, ProductInfo product) {
				return null;
			}

			@Override
			public ViewGroup createBlockContainer(ViewGroup parent, BlockInfo block, int orientation) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getRowItemCount() {

				return 2;
			}

			class Holder {
				ImageView rl_pi_ivImage;
				TextView rl_pi_tvPrice;
				TextView rl_pi_tvTitle;
				RatingBar rl_pi_rbRate;
				TextView rl_pi_tvTags;
				LinearLayout layoutPicturs;
			}

			@Override
			public View initialSectionBasicInfo(ProductInfo product, android.view.View.OnClickListener onAddToBasketClickListener) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	@Override
	protected void initialCurrentPage(Class<?> activityClass, Bundle extras) throws JSONException {
		this.activityClass = activityClass;
		// TODO close button

		super.initialCurrentPage(activityClass, extras);
		/*
		 * ImageButton ibtnExit = (ImageButton) findViewById(R.id.main_imgExit);
		 * ibtnExit.bringToFront();
		 * 
		 * if (isMainPage) { ibtnExit.setVisibility(View.GONE); checkUpdate(); }
		 * else { ibtnExit.setVisibility(View.VISIBLE);
		 * ibtnExit.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // if (!isMainPage &&
		 * isLastActivity()) // goToPageByPageId(getDefaultPageGroupId(), 0, //
		 * ActivityMain.class); finish(); // onBackPressed(); } }); }
		 */
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	/*
	 * protected void exit() { ActivityPageBase.this.finish(); Intent intent =
	 * new Intent(Intent.ACTION_MAIN); intent.addCategory(Intent.CATEGORY_HOME);
	 * intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); startActivity(intent); }
	 */

	@Override
	public void onBackPressed() {
		if (!isMainPage)
			// showConfirmBox(getString(R.string.exit), getString(R.string.yes),
			// new
			// OnClickListener() {
			//
			// @Override
			// public void onClick(DialogInterface dialog, int which) {
			// Controller.closeDatabase();
			// ActivityPageBase.super.onBackPressed();
			// }
			// }, getString(R.string.no));
			// else
			// {
			// if (isLastActivity()) {
			// goToPageByPageId(getDefaultPageGroupId(), 0, ActivityMain.class);
			// } else {
			// Controller.closeDatabase();
			// goToPageByPageId(getPageGroupId(), 0, ActivityMain.class);
			ActivityPageBase.super.onBackPressed();
		// }
		// }
	}

	@Override
	public boolean onKeyDown(int keycode, KeyEvent e) {
		switch (keycode) {
		case KeyEvent.KEYCODE_MENU:
			return false;
		}
		return super.onKeyDown(keycode, e);
	}

	@Override
	public int getDefaultPageGroupId() {
		return Config.DefaultPageGroupId;
	}
}
