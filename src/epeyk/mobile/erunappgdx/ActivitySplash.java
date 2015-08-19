package epeyk.mobile.erunappgdx;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.ProgressBar;
import epeyk.mobile.erunappgdx.R;
import epeyk.mobile.eaf.utility.FileManager;
import epeyk.mobile.eaf.utility.Utils;
import epeyk.mobile.eaf.utility.ZipManager;
import epeyk.mobile.eaf.utility.webservice.DownloadFileFromURL;
import epeyk.mobile.erunapi.services.notification.NotificationHandler;
import epeyk.mobile.erunappgdx.db.DatabaseHelper;
import epeyk.mobile.erunbase.component.builtins.packagemanager.PackageManager;
import epeyk.mobile.erunbase.component.core.page.CacheManager;
import epeyk.mobile.erunbase.component.core.page.PageController;
import epeyk.mobile.erunbase.enums.EnumPageGroupType;

/**
 * The Class ActivitySplash.
 */
public class ActivitySplash extends epeyk.mobile.eaf.formbase.ActivityBase {

	// /bootiyar
	NotificationHandler notificationHandler;
	boolean cacheFinished = false;
	ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Thread logoTimer = new Thread() {

			@Override
			public void run() {
				try {
					Time first = new Time();
					first.setToNow();

					initialDataBase("123");
					initialCache();

					Time second = new Time();
					second.setToNow();

					waiting(first, second);

					initialNotification();

					Intent intent = new Intent(ActivitySplash.this, ActivityMain.class);
					ActivitySplash.this.startActivity(intent);

				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					finish();
				}
			}

			private void initialCache() {
				CacheManager cacheManager = new CacheManager(getApplicationContext());
				if (!Config.isDebug) {
					progressBar = (ProgressBar) findViewById(R.id.progressBar);
					int width = Utils.getWidth(getApplicationContext());
					String packageFilePath = "cache";
					if (width == 1024)
						packageFilePath += "1024.zip";
					else
						packageFilePath += "1280.zip";
					if (!FileManager.fileExist(cacheManager.getCache() + packageFilePath)) {
						try {
							cacheManager.copyToCache(cacheManager.getCache() + packageFilePath, getAssets().open(packageFilePath));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						if (FileManager.fileExist(cacheManager.getCache() + packageFilePath)) {
							new ZipManager(cacheManager.getCache() + packageFilePath, cacheManager.getCache(), progressBar) {
								@Override
								protected void onPostExecute(Integer result) {
									super.onPostExecute(result);
									cacheFinished = true;
								}
							}.execute();
							while (!cacheFinished) {

							}
						}
					}
				} else {
					try {
						String p = cacheManager.getImagePath(EnumPageGroupType.Launcher, Config.DefaultPageGroupId, "back-rooz.png");
						if (!FileManager.fileExist(p))
							cacheManager.copyToCache();
						p = null;
						cacheManager = null;
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			protected void initialNotification() {
				// notificationHandler = new
				// NotificationHandler(getApplicationContext());
				// notificationHandler.Register(Config.app_id,
				// Authentication.getInstanse(getApplicationContext()).getAuthToken());
			}

			protected void waiting(Time first, Time second) throws InterruptedException {
				long diff = TimeUnit.MILLISECONDS.toMillis(second.toMillis(true) - first.toMillis(true));

				long _long = (Config.SPLASH_SCREAN_TIME - diff);
				if (_long > 0) {
					int logoTimer = 0;
					while (logoTimer < _long) {
						sleep(100);
						logoTimer = logoTimer + 100;
					}
					;
				}
			}
		};
		logoTimer.start();
	}

	private void initialDataBase(String pass) {
		try {
			// ConfigBase.isDebug = Config.isDebug;
			DatabaseHelper db = new DatabaseHelper(getApplicationContext(), getPackageName(), pass, getPackageManager().getPackageInfo(getPackageName(), 0).versionCode, false);
			db.createDataBase();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return;
		}
		if (PageController.getInstance(this).getPageCount(Config.DefaultPageGroupId) == 0) {
			// check defaultPackage is ready to install
			// .........................................
			String packagePath = getExternalCacheDir() + "defaultPackage.pkg";
			if (FileManager.fileExist(packagePath)) {
				// install default package
				// .......................................................
				ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
				new PackageManager(getApplicationContext(), progressBar).installPackage(EnumPageGroupType.Launcher, Config.DefaultPageGroupId, packagePath);
			} else {
				// download default package
				// ......................................................
				DownloadFileFromURL dfu = new DownloadFileFromURL(getApplicationContext(), packagePath) {
					@Override
					protected void onPostExecute(String file_url) {
						super.onPostExecute(file_url);
						ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
						new PackageManager(getApplicationContext(), progressBar).installPackage(EnumPageGroupType.Launcher, Config.DefaultPageGroupId, file_url);
					}
				};
				dfu.execute(epeyk.mobile.erunapi.Config.RootUrl + "store/apk/download?pgi=" + Config.DefaultPageGroupId);
			}
		}
	}

}
