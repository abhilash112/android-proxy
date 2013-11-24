package com.lechucksoftware.proxy.proxysettings.adapters;

import android.content.Context;
import android.preference.CheckBoxPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.lechucksoftware.proxy.proxysettings.R;
import com.lechucksoftware.proxy.proxysettings.components.TagModel;
import com.lechucksoftware.proxy.proxysettings.components.TagsView;
import com.lechucksoftware.proxy.proxysettings.db.DBProxy;
import com.lechucksoftware.proxy.proxysettings.db.DBTag;

import java.util.List;

public class TagsListAdapter extends ArrayAdapter<TagModel>
{
    private final LayoutInflater vi;
    private Context ctx;

    public TagsListAdapter(Context context)
    {
        super(context, R.layout.tags_dialog_list_item);
        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ctx = context;
    }

    static class ApViewHolder
    {
        CheckBox checkBox;
    }

    public void setData(List<TagModel> confList)
    {
        clear();
        if (confList != null)
        {
            for (TagModel conf : confList)
            {
                add(conf);
            }
        }
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ApViewHolder viewHolder;

        if (convertView == null)
        {
            convertView = vi.inflate(R.layout.tags_dialog_list_item, null);

            viewHolder = new ApViewHolder();
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.li_tag_checkbox);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ApViewHolder) convertView.getTag();
        }

        final TagModel listItem = getItem(position);

        if (listItem != null)
        {
            viewHolder.checkBox.setText(listItem.tag.tag);
            viewHolder.checkBox.setChecked(listItem.isSelected);
            viewHolder.checkBox.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    CheckBox checkBox = (CheckBox) view;
                    if (checkBox.isChecked())
                    {
                        listItem.isSelected = true;
                    }
                    else
                    {
                        listItem.isSelected = false;
                    }
                }
            });
        }

        return convertView;
    }
}
