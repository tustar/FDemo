package com.tustar.fc.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tustar on 8/12/15.
 */
public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    private ActivityCollector() {

    }

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }
}
