package epeyk.mobile.erunappgdx;

public class Config extends epeyk.mobile.eaf.ConfigBase {
	// TODO mustchange
	public static final CharSequence DefaultUserName = "";
	public static final CharSequence DefaultPassword = "";
	/* زمان انتظار اسپلش */
	public static final int SPLASH_SCREAN_TIME = 1000;
	/* نمایش لاگ در خروجی */
	public static final boolean ShowLogs = true;
	public static boolean isDebug = false;
	public static final boolean hasSensor = false;
	public static final boolean disableSystemBar = false;

	
	public static final boolean UseProxy = false;

	public static final int DefaultProfile = 0;
	public static final String DefaultFilePath = "/";
	/*
	 * public static final String RootUrl = "http://x.com/"; public static final
	 * String WebServiceRootUrl = "http://vasnistore.mashhad24.com/android/";
	 * 
	 * // for OAuth 2.0 public static final String AuthType = "token"; public
	 * static final String WebServiceAuthUrl =
	 * "http://vasnistore.mashhad24.com/oauth2/authorize";
	 */
	// end of for OAuth 2.0
	public static final int DefaultPageGroupId = 1;

	public static String DefaultMenuImagesFilePath() {
		return DefaultFilePath + "menuImages/";
	}

	public static String DefaultAvatarFilePath() {
		return DefaultFilePath + "Avatar/";
	}

	public static String DefaultProfileRootFilePath() {
		return DefaultFilePath + "Profile/";
	}

	public static String DefaultProfileFilePath() {
		return DefaultProfileRootFilePath() + DefaultProfile + "/";
	}

	public static String DefaultProfilePostFilePath() {
		return DefaultProfileFilePath() + "Post/";
	}

	public static String DefaultProfileGalleryFilePath() {
		return DefaultProfileFilePath() + "Gallery/";
	}

	public static String FontFaceTitle = "fonts/mj_tunisia_bd.ttf";
	public static String FontFaceForm = "fonts/mj_tunisia.ttf";
	public static String app_id="getFromServer";

}
