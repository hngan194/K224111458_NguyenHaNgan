package adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenhangan.k22411csampleproject.R;
import com.nguyenhangan.k22411csampleproject.models.OrderDetailsViewer;

public class OrderDetailsAdapter extends ArrayAdapter<OrderDetailsViewer> {
    Activity context;
    int resource;
    public OrderDetailsAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);
        TextView txtDetailProductId = item.findViewById(R.id.txtDetailProductId);
        TextView txtDetailProductName = item.findViewById(R.id.txtDetailProductName);
        TextView txtQuantity = item.findViewById(R.id.txtQuantity);
        TextView txtPrice = item.findViewById(R.id.txtPrice);
        OrderDetailsViewer odv = getItem(position);
        txtDetailProductId.setText(String.valueOf(odv.getProductId()));
        txtDetailProductName.setText(odv.getProductName());
        txtQuantity.setText(String.valueOf(odv.getQuantity()));
        txtPrice.setText(String.valueOf(odv.getPrice()));
        return item;
    }
}
