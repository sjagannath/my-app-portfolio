package sreedevi.portfolio.androidnanodegree;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Sreedevi.Jagannath on 05-06-2015.
 */
public class AppListAdapter extends ArrayAdapter<PortfolioApp> {
    private Context mContext;
    private boolean mShowOnlyComplete;

    public AppListAdapter(Context context, PortfolioApp[] objects) {
        super(context, R.layout.list_item_view, objects);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView listItemTextView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listItemTextView = (TextView) inflater.inflate(R.layout.list_item_view, parent, false);
        } else {
            listItemTextView = (TextView) convertView;
        }
        PortfolioApp item = getItem(position);
        final String text = item.getAppName();
        if (showOnlyComplete() && !item.isComplete()) {
            listItemTextView.setVisibility(View.GONE);
        } else {
            listItemTextView.setText(text);
            listItemTextView.setVisibility(View.VISIBLE);
            Resources resources = mContext.getResources();
            if(item.getAppName().equals(resources.getString(R.string.capstone))){
                listItemTextView.setBackgroundResource(R.drawable.blue_button);
            }
        }

        return listItemTextView;
    }

    private boolean showOnlyComplete() {
        return mShowOnlyComplete;
    }

    public void setShowsOnlyComplete(boolean value) {
        mShowOnlyComplete = value;
        notifyDataSetChanged();
    }
}
