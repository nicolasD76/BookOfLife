package ndfv.bookoflifev0.widgetools;

import ndfv.bookoflifev0.bookoflifev0.R;
import ndfv.bookoflifev0.entity.CounterEntity;
import ndfv.bookoflifev0.entity.ModeleCounters;
import ndfv.bookoflifev0.exception.MiteException;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class BookOfLifeWidget extends AppWidgetProvider {

	public static final String ACTION_SHOW_NOTIFICATION = "ndfv.bookoflifev0.widgetoolsSHOW_NOTIFICATION";

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

	public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

		// Prepare widget views
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bookoflife_widget_layout);

		// Prepare intent to launch on widget click
		Intent intent = new Intent(context, BookOfLifeWidget.class);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		intent.setAction(ACTION_SHOW_NOTIFICATION);

		// Launch intent on widget click
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
		views.setOnClickPendingIntent(R.id.bookoflife_widget, pendingIntent);

		appWidgetManager.updateAppWidget(appWidgetId, views);
	}

	/**
	 * Handle new messages
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);

		if (ACTION_SHOW_NOTIFICATION.equals(intent.getAction())) {
			CounterEntity counterToUpdate = null;
			counterToUpdate = ModeleCounters.getInstance(context).getCounterActivatedWidget();
			counterToUpdate.setValue(counterToUpdate.getValue() + 1);
			ModeleCounters.getInstance(context).updateCounter(counterToUpdate);
			CharSequence message = ModeleCounters.getInstance(context).getCounterActivatedWidget().getName()+ ": " + ModeleCounters.getInstance(context).getCounterActivatedWidget().getValue();
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, message, duration);
			toast.show();
		}
	}

}