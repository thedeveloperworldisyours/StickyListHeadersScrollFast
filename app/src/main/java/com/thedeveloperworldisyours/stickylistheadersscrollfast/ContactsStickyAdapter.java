package com.thedeveloperworldisyours.stickylistheadersscrollfast;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Set;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by javierg on 13/02/2017.
 */

public class ContactsStickyAdapter extends BaseAdapter implements StickyListHeadersAdapter , SectionIndexer {

    HashMap<String, Integer> mapIndex;
    private String[] countries;
    private LayoutInflater inflater;

    String[] sections;

    public ContactsStickyAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        countries = context.getResources().getStringArray(R.array.countries_array);
        mapIndex = new LinkedHashMap<>();

        for (int x = 0; x < countries.length; x++) {
            String fruit = countries[x];
            String ch = fruit.substring(0, 1);
            ch = ch.toUpperCase(Locale.US);

            // HashMap will prevent duplicates
            mapIndex.put(ch, x);
        }

        Set<String> sectionLetters = mapIndex.keySet();

        // create a list from the set to sort
        ArrayList<String> sectionList = new ArrayList<>(sectionLetters);

        Log.d("sectionList", sectionList.toString());
        Collections.sort(sectionList);

        sections = new String[sectionList.size()];

        sectionList.toArray(sections);
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.header_contacts, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.header_text);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header_contacts text as first char in name
        String headerText = "" + countries[position].subSequence(0, 1).charAt(0);
        holder.text.setText(headerText);

        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon
        return countries[position].subSequence(0, 1).charAt(0);
    }

    @Override
    public int getCount() {
        return countries.length;
    }

    @Override
    public Object getItem(int position) {
        return countries[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_contacts, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.list_item_contacts_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(countries[position]);

        return convertView;
    }

    @Override
    public Object[] getSections() {
        return sections;
    }

    @Override
    public int getPositionForSection(int i) {
        return mapIndex.get(sections[i]);
    }

    @Override
    public int getSectionForPosition(int i) {
        return 0;
    }


    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
    }
}
