package team.cake.theredalliance;

import android.widget.LinearLayout;

public interface Askable {
    //Generate LinearLayout object
    //ToDo: return [null] if object was not properly configured from the user's config.csv file.
    Integer makeView(LinearLayout layout);
    void saveViewData();
    void loadViewData(String data);
}
