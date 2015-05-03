package ndfv.bookoflifev0.widgetools;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import ndfv.bookoflifev0.bookoflifev0.R;

public class BookOfLifeWidget extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		final int N = appWidgetIds.length;

		// Perform this loop procedure for each App Widget that belongs to this
		// provider
		for (int i = 0; i < N; i++) {
			int appWidgetId = appWidgetIds[i];
			updateAppWidget(context, appWidgetManager, appWidgetId);
		}
	}

	/**
	 * Update the widget
	 * 
	 * @param context
	 * @param appWidgetManager
	 * @param appWidgetId
	 */
	public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

		// Prepare widget views
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bookoflife_widget_layout);
		// views.setTextViewText(R.id.nap_time, "Erase me");
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}
}