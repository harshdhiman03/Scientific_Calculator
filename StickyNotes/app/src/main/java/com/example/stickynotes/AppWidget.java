package com.example.stickynotes;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class AppWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appWidgetId :appWidgetIds){
            Intent launchIntent = new Intent(context,MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivities(context,0, new Intent[]{launchIntent},0);
            RemoteViews remoteView =new RemoteViews(context.getPackageName(),R.layout.widget_layout);
            remoteView.setOnClickPendingIntent(R.id.idtvwidget,pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId,remoteView);

        }
    }
}
