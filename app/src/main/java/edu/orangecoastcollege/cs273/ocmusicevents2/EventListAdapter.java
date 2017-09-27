package edu.orangecoastcollege.cs273.ocmusicevents2;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by jimburk on 9/26/17.
 */

public class EventListAdapter extends ArrayAdapter<MusicEvent> {

    private Context mContext;
    private int mResource;
    private List<MusicEvent> mAllEventsList;

    // context= Activity that uses the adapter
    public EventListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MusicEvent> allMusicEvents) {
        super(context, resource, allMusicEvents);

        mContext = context;
        mResource = resource;
        mAllEventsList = allMusicEvents;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View listItemView = inflater.inflate(mResource, null);

        ImageView listItemImageView = (ImageView) listItemView.findViewById(R.id.listItemImageView);
        TextView listItemTitleTextView = (TextView) listItemView.findViewById(R.id.eventTitleTextView);
        TextView listItemDateTextView = (TextView) listItemView.findViewById(R.id.eventDateTextView);
        TextView listItemDayTextView = (TextView) listItemView.findViewById(R.id.eventDayTextView);
        TextView listItemTimeTextView = (TextView) listItemView.findViewById(R.id.eventTimeTextView);
        TextView listItemLocationTextView = (TextView) listItemView.findViewById(R.id.eventLocationTextView);
        TextView listItemAddress1TextView = (TextView) listItemView.findViewById(R.id.eventAddress1TextView);
        TextView listItemAddress2TextView = (TextView) listItemView.findViewById(R.id.eventAddress2TextView);

        MusicEvent selectedEvent = mAllEventsList.get(position);

        listItemTitleTextView.setText(selectedEvent.getTitle());
        listItemDateTextView.setText(selectedEvent.getDate());
        listItemDayTextView.setText(selectedEvent.getDay());
        listItemTimeTextView.setText(selectedEvent.getTime());
        listItemLocationTextView.setText(selectedEvent.getLocation());
        listItemAddress1TextView.setText(selectedEvent.getAddress1());
        listItemAddress2TextView.setText(selectedEvent.getAddress2());

        AssetManager am = mContext.getAssets();

        try {
            InputStream stream = am.open(selectedEvent.getImageName());
            Drawable image = Drawable.createFromStream(stream, selectedEvent.getTitle());
            listItemImageView.setImageDrawable(image);
        } catch (IOException e) {
            Log.e("EventListAdapter", "Error finding image: ", e);
        }

        return listItemView;
    }
}
