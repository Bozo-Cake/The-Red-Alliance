package team.cake.theredalliance;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.lang.ref.WeakReference;

public interface Askable {
    public void setActivity(WeakReference<Activity> activity);
    public void makeView(ViewGroup layout);
    public void makeView(ViewGroup layout, String data);
    public String saveViewData();
}
