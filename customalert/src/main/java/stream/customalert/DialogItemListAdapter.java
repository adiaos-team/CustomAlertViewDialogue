package stream.customalert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.core.content.ContextCompat;

public class DialogItemListAdapter extends BaseAdapter {
//    public class CustomActionsheetAdapter extends BaseAdapter {

    private List<DialogItemInfo> mDatas;
    private CustomAlertDialogue dialog;
    private OnItemClickListener itemClickListener;

    public DialogItemListAdapter(List<DialogItemInfo> datas, CustomAlertDialogue dialogue) {
        this.mDatas = datas;
        this.dialog = dialogue;
    }
    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener =itemClickListener;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final DialogItemInfo dialogItemInfo = mDatas.get(position);
        String data = dialogItemInfo.getName();
        Holder holder = null;
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.alert_button, null);
            holder = createHolder(view);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        holder.UpdateUI(parent.getContext(), data, position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener!=null){
                    itemClickListener.onItemClick(dialog, dialogItemInfo,dialogItemInfo.name);
                }
            }
        });
        return view;
    }

    public Holder createHolder(View view) {
        return new Holder(view);
    }

    class Holder {

        private View buttonDivider;
        private TextView buttonText;

        public Holder(View view) {
            buttonText = view.findViewById(R.id.alerttext);
            buttonDivider = view.findViewById(R.id.button_divider);
        }

        public void UpdateUI(Context context, String data, int position) {

            buttonText.setText(data);
            if (position == 0) {
                buttonDivider.setVisibility(View.GONE);
            } else {
                buttonDivider.setVisibility(View.VISIBLE);
            }
            buttonText.setTextColor(ContextCompat.getColor(context, R.color.positive));
        }
    }


}