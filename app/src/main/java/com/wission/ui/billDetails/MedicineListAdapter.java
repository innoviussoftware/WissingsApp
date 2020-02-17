package com.wission.ui.billDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wission.Model.BillDetailsRoom;
import com.wission.Model.Customer_Medicinlist;
import com.wission.R;

import java.util.List;

public class MedicineListAdapter extends RecyclerView.Adapter<MedicineListAdapter.BillViewHolder> {

    private Context mCtx;
    private List<Customer_Medicinlist> billList;

    public MedicineListAdapter(Context mCtx, List<Customer_Medicinlist> billList) {
        this.mCtx = mCtx;
        this.billList = billList;
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.row_bill_details_room, parent, false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        Customer_Medicinlist t = billList.get(position);
        holder.tvMName.setText(t.getMedicine_name());
        holder.tvMPrice.setText(t.getMedicine_price());
        holder.tvMQTY.setText(t.getMedicine_qty());


    }

    @Override
    public int getItemCount() {
        return billList.size();
    }

    class BillViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvMName, tvMPrice, tvMQTY, tvCMedicineName;

        public BillViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMName = itemView.findViewById(R.id.textViewStatus);
            tvMPrice = itemView.findViewById(R.id.textViewTask);
            tvMQTY = itemView.findViewById(R.id.textViewDesc);
            tvCMedicineName = itemView.findViewById(R.id.textViewFinishBy);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Customer_Medicinlist task = billList.get(getAdapterPosition());

         /*   Intent intent = new Intent(mCtx, UpdateTaskActivity.class);
            intent.putExtra("userDetails", task);
            mCtx.startActivity(intent);*/
        }
    }
}
