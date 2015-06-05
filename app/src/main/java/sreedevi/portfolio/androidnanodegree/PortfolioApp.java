package sreedevi.portfolio.androidnanodegree;

/**
 * Created by Sreedevi.Jagannath on 05-06-2015.
 */
public class PortfolioApp {
    private String mAppName;
    private String mAppIntent;
    private boolean mIsComplete;

    public PortfolioApp(String appName, String intent, boolean complete){
        this.mAppName = appName;
        this.mAppIntent = intent;
        this.mIsComplete = complete;
    }

    public String getAppName() {
        return mAppName;
    }

    public boolean isComplete() {
        return mIsComplete;
    }

    public String getAppIntent(){
        return mAppIntent;
    }
}
