package course.labs.todomanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.CompoundButton.OnCheckedChangeListener;

import java.util.ArrayList;
import java.util.List;

public class ToDoListAdapter extends BaseAdapter {

    private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
    private final Context mContext;
    private static final String TAG = "Lab-UserInterface";

    public ToDoListAdapter(Context context) {
        mContext = context;
    }

    // Add a ToDoItem to the adapter
    // Notify observers that the data set has changed
    public void add(ToDoItem item) {
        mItems.add(item);
        notifyDataSetChanged();
    }

    // Clears the list adapter of all items.
    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    // Returns the number of ToDoItems
    @Override
    public int getCount() {
        return mItems.size();
    }

    // Retrieve the number of ToDoItems
    @Override
    public Object getItem(int pos) {
        return mItems.get(pos);
    }

    // Get the ID for the ToDoItem
    // In this case it's just the position
    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.todo_item, parent, false);
            holder.statusView = (CheckBox) convertView.findViewById(R.id.statusCheckBox);
            holder.titleView = (TextView) convertView.findViewById(R.id.titleView);
            holder.priorityView = (TextView) convertView.findViewById(R.id.priorityView);
            holder.dateView = (TextView) convertView.findViewById(R.id.dateView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // TODO - Get the current ToDoItem
        final ToDoItem toDoItem = mItems.get(position);
        // TODO - Display Title in TextView
        holder.titleView.setText(toDoItem.getTitle());
        // TODO - Set up Status CheckBox
        holder.statusView.setChecked(toDoItem.getStatus() == ToDoItem.Status.DONE);
        // TODO - Must also set up an OnCheckedChangeListener,
        holder.statusView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    toDoItem.setStatus(ToDoItem.Status.DONE);
                } else {
                    toDoItem.setStatus(ToDoItem.Status.NOTDONE);
                }
            }
        });
        // TODO - Display Priority in a TextView
        holder.priorityView.setText(toDoItem.getPriority().toString());
        // TODO - Display Time and Date.
        holder.dateView.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));
        return convertView;

    }
}
