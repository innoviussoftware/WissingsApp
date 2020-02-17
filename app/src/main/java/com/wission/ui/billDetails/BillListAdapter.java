package com.wission.ui.billDetails;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wission.Model.BillDetailsRoom;
import com.wission.Model.UserDetails_Room;
import com.wission.R;
import com.wission.ui.addUserDetails.UserListAdapter;

import java.util.List;

public class BillListAdapter  extends RecyclerView.Adapter<BillListAdapter.BillViewHolder> {



    private Context mCtx;
    private List<BillDetailsRoom> billList;

    public BillListAdapter(Context mCtx, List<BillDetailsRoom> billList) {
        this.mCtx = mCtx;
        this.billList = billList;
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.row_user_details_room, parent, false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        try {
            BillDetailsRoom t = billList.get(position);
            holder.tvCName.setText(t.getCustomer_name());
            holder.tvCPhone.setText(t.getCustomer_phone());
            holder.tvCEmail.setText(t.getCustomer_email());

            if (t.getCustomer_medicinlist().size()>0)
            holder.tvCMedicineName.setText(t.getCustomer_medicinlist().get(0).getMedicine_name());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }

    class BillViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvCName, tvCPhone, tvCEmail, tvCMedicineName;

        public BillViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCName = itemView.findViewById(R.id.textViewStatus);
            tvCPhone = itemView.findViewById(R.id.textViewTask);
            tvCEmail = itemView.findViewById(R.id.textViewDesc);
            tvCMedicineName = itemView.findViewById(R.id.textViewFinishBy);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            BillDetailsRoom task = billList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, AddBillDetailsActivity.class);
            intent.putExtra("billDetails", task);
            intent.putExtra("class","update");
            mCtx.startActivity(intent);
        }
    }
}
