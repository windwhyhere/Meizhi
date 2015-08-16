package me.drakeet.meizhi.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.umeng.analytics.MobclickAgent;
import me.drakeet.meizhi.BuildConfig;
import me.drakeet.meizhi.R;
import me.drakeet.meizhi.face.OnShare;
import me.drakeet.meizhi.ui.base.BaseActivity;

/**
 * Created by drakeet on 15/8/15.
 */
public class AboutActivity extends BaseActivity implements OnShare {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.tv_version) TextView mVersionTextView;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        setUpVersionName();

        mCollapsingToolbarLayout.setTitle(getString(R.string.app_name));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> AboutActivity.this.onBackPressed());
    }

    private void setUpVersionName() {
        mVersionTextView.setText("Version " + BuildConfig.VERSION_NAME);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.menu_share:
                onClickShare(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
