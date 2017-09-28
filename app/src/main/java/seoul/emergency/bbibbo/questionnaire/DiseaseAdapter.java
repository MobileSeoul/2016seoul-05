package seoul.emergency.bbibbo.questionnaire;

/**
 * Created by Min-Soo on 2016-08-24.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import seoul.emergency.bbibbo.R;

public class DiseaseAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<DiseaseItem> data;
    private int layout;


    public DiseaseAdapter(Context context, int layout, ArrayList<DiseaseItem> data) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }

        Log.d("@@@",""+position);

        TextView nameTextView = (TextView) convertView.findViewById(R.id.diseaseNameView);
        TextView statusTextView = (TextView) convertView.findViewById(R.id.diseaseStatusView);

        DiseaseItem tempItem = new DiseaseItem(data.get(position).getName(),data.get(position).getStatus());
        Log.d("@@@",""+tempItem.getName());
        Log.d("@@@",""+tempItem.getStatus());

        nameTextView.setText(tempItem.getName());
        statusTextView.setText(tempItem.getStatus());

        return convertView;
    }
}
