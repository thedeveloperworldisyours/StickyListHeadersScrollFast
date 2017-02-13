package com.thedeveloperworldisyours.stickylistheadersscrollfast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        StickyListHeadersListView stickyList = (StickyListHeadersListView) findViewById(R.id.main_activity_list_view);

        stickyList.setFastScrollEnabled(true);
        ContactsStickyAdapter adapter = new ContactsStickyAdapter(this);
        stickyList.setAdapter(adapter);
        stickyList.setDivider(null);
    }
}
