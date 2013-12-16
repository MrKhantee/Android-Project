package giraffine.dimmer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Prefs {

	private static Context mContext = null;
	private static String PREFER = "settings";
	private static String AUTOMODE = "automode";
	private static String FAVORMASKVALUE = "favormaskvalue";
	private static String PROXIMITYMAX = "proximitymax";
	private static String PROXIMITYMIN = "proximitymin";
	
	public static String PREF_AUTOMODE = "pref_automode";
	public static String PREF_WIDGETMODE = "pref_widgetmode";
	public static String PREF_ABOUT = "pref_about";

	private static SharedPreferences mPrefer = null;

	public static void init(Context context)
	{
		if(mContext != null)
			return;
		mContext = context;
		mPrefer = PreferenceManager.getDefaultSharedPreferences(mContext);

		// backward compatible
		SharedPreferences prefer = mContext.getSharedPreferences(PREFER, mContext.MODE_WORLD_READABLE);
		if(prefer.contains(AUTOMODE))
			mPrefer.edit().putBoolean(AUTOMODE, prefer.getBoolean(AUTOMODE, false)).commit();
		if(prefer.contains(FAVORMASKVALUE))
			mPrefer.edit().putInt(FAVORMASKVALUE, prefer.getInt(FAVORMASKVALUE, 250)).commit();
		if(prefer.contains(PROXIMITYMAX))
			mPrefer.edit().putFloat(PROXIMITYMAX, prefer.getFloat(PROXIMITYMAX, ProximitySensor.DEFAULT_DISTANCE)).commit();
		if(prefer.contains(PROXIMITYMIN))
			mPrefer.edit().putFloat(PROXIMITYMIN, prefer.getFloat(PROXIMITYMIN, ProximitySensor.DEFAULT_DISTANCE)).commit();
	}
	
	public static boolean isAutoMode()
	{
		return mPrefer.getBoolean(PREF_AUTOMODE, false);
	}
	public static void setAutoMode(boolean isON)
	{
		mPrefer.edit().putBoolean(PREF_AUTOMODE, isON).commit();
	}
	public static boolean getWidgetMode()
	{
		return mPrefer.getBoolean(PREF_WIDGETMODE, false);
	}
	public static int getFavorMaskValue()
	{
		return mPrefer.getInt(FAVORMASKVALUE, 250);
	}
	public static void setFavorMaskValue(int value)
	{
		mPrefer.edit().putInt(FAVORMASKVALUE, value).commit();
	}
	public static float getProximity(boolean isMax)
	{
		return mPrefer.getFloat(isMax ? PROXIMITYMAX : PROXIMITYMIN, ProximitySensor.DEFAULT_DISTANCE);
	}
	public static void setProximity(boolean isMax, float value)
	{
		mPrefer.edit().putFloat(isMax ? PROXIMITYMAX : PROXIMITYMIN, value).commit();
	}
}
