package team.cake.theredalliance;

import android.widget.LinearLayout;

public interface Askable {
    //Generate LinearLayout object
    Integer makeView(LinearLayout layout);
    void saveViewData();
    void loadViewData(String data);
}
