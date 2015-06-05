package sreedevi.portfolio.androidnanodegree;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class AppHubActivity extends ListActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private static final String TAG = AppHubActivity.class.getSimpleName();

    private static final String ACTION_FOOTBALL_SCORES = "sreedevi.portfolio.androidnanodegree.football_scores";
    private static final String ACTION_LIBRARY_APP = "sreedevi.portfolio.androidnanodegree.library_app";

    private final int[] APP_NAMES = new int[]{
            R.string.spotify_streamer,
            R.string.super_duo,
            R.string.build_it_bigger,
            R.string.xyz_reader,
            R.string.capstone
    };

    private final boolean[] APP_STATE = new boolean[]{false, false, false, false, false};

    private final String[] APP_INTENT_ACTION = new String[]{
            "sreedevi.portfolio.androidnanodegree.spotify_streamer",
            "sreedevi.portfolio.androidnanodegree.super_duo",
            "sreedevi.portfolio.androidnanodegree.build_it_bigger",
            "sreedevi.portfolio.androidnanodegree.xyz_reader",
            "sreedevi.portfolio.androidnanodegree.capstone"};

    private PortfolioApp[] mPortfolioApps;
    private AppListAdapter mAppListAdapter;
    private ToggleButton mFilterCompletedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_hub);
        initAppPortfolios();
        initViews();
    }

    private void initViews() {
        mAppListAdapter = new AppListAdapter(this, mPortfolioApps);
        mAppListAdapter.setShowsOnlyComplete(false);
        setListAdapter(mAppListAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ListView listView = getListView();
        View view = inflater.inflate(R.layout.listview_header, null, false);
        listView.addHeaderView(view);
        listView.setOnItemClickListener(this);
        mFilterCompletedButton = (ToggleButton) view.findViewById(R.id.buttonToggleCompletedApps);
        mFilterCompletedButton.setOnClickListener(this);
    }

    private void initAppPortfolios() {
        int size = APP_NAMES.length;
        mPortfolioApps = new PortfolioApp[size];
        for (int i = 0; i < size; i++) {
            PortfolioApp app = new PortfolioApp(getString(APP_NAMES[i]), APP_INTENT_ACTION[i], APP_STATE[i]);
            mPortfolioApps[i] = app;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "position selected: " + position);
        int actual = position - 1;//to account for header being 0
        int resId = APP_NAMES[actual];
        if (!APP_STATE[actual]) {
            showToast(resId);
            return;
        }
        if (resId == R.string.super_duo) {
            showPopup(view);
            return;
        }

        startActivity(APP_INTENT_ACTION[actual]);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v, Gravity.CENTER);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_super_duo, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(mOnMenuItemClickListener);
    }

    private void showToast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    PopupMenu.OnMenuItemClickListener mOnMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_football:
                    showToast(R.string.football_scores);
                    startActivity(ACTION_FOOTBALL_SCORES);
                    return true;

                case R.id.action_lib_app:
                    showToast(R.string.library_app);
                    startActivity(ACTION_LIBRARY_APP);
                    return true;

            }
            return false;
        }

    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonToggleCompletedApps:
                mAppListAdapter.setShowsOnlyComplete(mFilterCompletedButton.isChecked());
                break;
        }
    }

    private void startActivity(String intent) {
        Log.d(TAG, "Start activity for intent:" + intent);
    }
}
