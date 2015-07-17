package com.example.des.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;

import java.util.Map;

import baseEngine.CastleModel;

public class CustomExpandableAdapter extends BaseExpandableListAdapter implements ExpandableListView.OnChildClickListener {

    private Context myContext;

    private CastlePickModel[] set;
    private int groupCount;
    private LayoutInflater inflater;

    public CustomExpandableAdapter(Context context, Map<Integer, CastleModel> data) {
        myContext = context;

        set = new CastlePickModel[data.size()];
        int counter = 0;
        for (CastleModel model : data.values()) {
            set[counter] = new CastlePickModel(model);
            counter++;
        }
        groupCount = counter;
        inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return groupCount;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.d(AppConsts.LOG_TAG, String.format("Custom adapter: getChildrenCount called with position = %d", groupPosition));
        return set[groupPosition].count();
    }

    @Override
    public Object getGroup(int groupPosition) {
        Log.d(AppConsts.LOG_TAG, String.format("Custom adapter: getGroup called with position = %d", groupPosition));
        return set[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Log.d(AppConsts.LOG_TAG, String.format("Custom adapter: getChild called with groupPosition = %d and childPosition = %d", groupPosition, childPosition));
        return findChildModel(groupPosition, childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        Log.d(AppConsts.LOG_TAG, String.format("Custom adapter: getGroupId called with position = %d", groupPosition));
        return set[groupPosition].id;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        Log.d(AppConsts.LOG_TAG, String.format("Custom adapter: getChildId called with groupPosition = %d and childPosition = %d", groupPosition, childPosition));
        return set[groupPosition].get(childPosition).id;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Log.d(AppConsts.LOG_TAG, String.format("Custom adapter: getGroupView called. groupPosition = %d", groupPosition));
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expandable_group_view, null);
        }

        set[groupPosition].bind(convertView);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Log.d(AppConsts.LOG_TAG, String.format("Custom adapter: getChildView called. groupPosition = %d", groupPosition));
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expandable_single_view, null);
        }
        CheckedTextView tv = (CheckedTextView) convertView.findViewById(R.id.heroTitle);

        findChildModel(groupPosition, childPosition).bind(tv);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private HeroPickModel findChildModel(int groupPosition, int childPosition) {
        return set[groupPosition].get(childPosition);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Log.d(AppConsts.LOG_TAG,
                String.format("Custom adapter: onChildClick called. groupPosition = %d, childPosition = %d, id = %d",
                        groupPosition, childPosition, id));
        findChildModel(groupPosition, childPosition).toogle();
        set[groupPosition].updateBindings();

        return false;
    }

    public int getCheckedCount(){
        int c = 0;

        for (CastlePickModel cm : set){
            c += cm.getSelectedCount();
        }

        return c;
    }

    public HeroPickModel getChecked(Finder finder){

        for (CastlePickModel cm : set){
            finder = cm.getChecked(finder);
            if (finder.result != null){
                break;
            }
        }
        return finder.result;
    }
}
