package com.example.dam.testfragment;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity implements HeadlinesFragment.onHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            HeadlinesFragment headlinesFragment = new HeadlinesFragment();

            headlinesFragment.setArguments(getIntent().getExtras());

            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, headlinesFragment)
                    .commit();
        }
    }

    @Override
    public void onArticleSelected(int position) {

        ArticleFragment articleFragment = (ArticleFragment) getFragmentManager().findFragmentById(R.id.article_fragment);

        if (articleFragment != null) {
            articleFragment.updateArticleView(position);
        } else {
            articleFragment = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION, position);
            articleFragment.setArguments(args);

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, articleFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
