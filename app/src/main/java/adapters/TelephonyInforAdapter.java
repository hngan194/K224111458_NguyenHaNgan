package adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenhangan.k22411csampleproject.R;
import com.nguyenhangan.k22411csampleproject.SendSmsActivity;
import com.nguyenhangan.k22411csampleproject.TelephonyActivity;
import com.nguyenhangan.k22411csampleproject.models.TelephonyInfor;

import java.util.ArrayList;
import java.util.List;

public class TelephonyInforAdapter extends ArrayAdapter<TelephonyInfor> {
    private final Activity context;
    private final int resource;
    private List<TelephonyInfor> items;

    public TelephonyInforAdapter(@NonNull Activity context, int resource, @NonNull List<TelephonyInfor> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.items = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        ViewHolder holder;

        if (item == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            item = inflater.inflate(this.resource, parent, false);

            holder = new ViewHolder();
            holder.txtTelephonyName = item.findViewById(R.id.txtTelephonyName);
            holder.txtTelephonyPhoneNumber = item.findViewById(R.id.txtTelephonyPhoneNumber);
            holder.imgDirectCall = item.findViewById(R.id.imgDirectCall);
            holder.imgDialUp = item.findViewById(R.id.imgDialUp);
            holder.imgSendSms = item.findViewById(R.id.imgSendSms);

            item.setTag(holder);
        } else {
            holder = (ViewHolder) item.getTag();
        }

        TelephonyInfor ti = getItem(position);
        if (ti != null) {
            holder.txtTelephonyName.setText(ti.getName());
            holder.txtTelephonyPhoneNumber.setText(ti.getPhone());

            holder.imgDirectCall.setOnClickListener(v ->
                    ((TelephonyActivity) context).directCall(ti));

            holder.imgDialUp.setOnClickListener(v ->
                    ((TelephonyActivity) context).dialUp(ti));

            holder.imgSendSms.setOnClickListener(v -> {
                Intent intent = new Intent(context, SendSmsActivity.class);
                intent.putExtra("TI", ti);
                context.startActivity(intent);
            });
        }

        return item;
    }

    public void updateList(List<TelephonyInfor> newList) {
        this.items.clear();
        this.items.addAll(newList);
        this.clear();
        this.addAll(newList);
        notifyDataSetChanged();
    }

    public List<TelephonyInfor> getItems() {
        return new ArrayList<>(items);
    }

    private static class ViewHolder {
        TextView txtTelephonyName;
        TextView txtTelephonyPhoneNumber;
        ImageView imgDirectCall;
        ImageView imgDialUp;
        ImageView imgSendSms;
    }
}
