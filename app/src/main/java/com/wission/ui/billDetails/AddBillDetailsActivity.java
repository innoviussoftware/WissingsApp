package com.wission.ui.billDetails;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wission.Database.DatabaseClient;
import com.wission.Model.BillDetailsRoom;
import com.wission.Model.Customer_Medicinlist;
import com.wission.Model.UserDetails_Room;
import com.wission.R;
import com.wission.appUtils.KeyboardUtility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddBillDetailsActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.tvNoDataFoundAABL)
    TextView tvNoDataFoundAABL;

    @BindView(R.id.rcVwMedicineData)
    RecyclerView rcVwMedicineData;

    @BindView(R.id.tvSave)
    TextView tvSave;


    @BindView(R.id.edtTvCName)
    EditText edtTvCName;

    @BindView(R.id.edtTvCEmail)
    EditText edtTvCEmail;

    @BindView(R.id.edtTvCPhone)
    EditText edtTvCPhone;

    @BindView(R.id.edtTvMName)
    EditText edtTvMName;

    @BindView(R.id.edtTvMQTY)
    EditText edtTvMQTY;

    @BindView(R.id.edtTvMTotalPrice)
    EditText edtTvMTotalPrice;

    @BindView(R.id.button_add)
    Button button_add;

    String className="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill_list);
        ButterKnife.bind(this);

        className = getIntent().getStringExtra("class");

        if (className.equals("add")){

        }else{
            final BillDetailsRoom billDetails = (BillDetailsRoom) getIntent().getSerializableExtra("billDetails");

            edtTvCPhone.setText(billDetails.getCustomer_phone());
            edtTvCEmail.setText(billDetails.getCustomer_email());
            edtTvCName.setText(billDetails.getCustomer_name());

            setCustomerMedicineListData(billDetails);
        }

        setListners();
    }

    private void setListners() {
        try {
            tvSave.setOnClickListener(this);
            button_add.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSave:
                KeyboardUtility.hideKeyboard(AddBillDetailsActivity.this, button_add);
                saveTask();
                break;

            case R.id.button_add:
                KeyboardUtility.hideKeyboard(AddBillDetailsActivity.this, button_add);
                if (isValidation()) {
                    setMedicineData();
                }
                break;
        }
    }

    //Todo: Add medicine list data
    private ArrayList<Customer_Medicinlist> allCustomerMedicineList = new ArrayList<>();

    private void setMedicineData() {
        try {

            Customer_Medicinlist medicinData = new Customer_Medicinlist();
            medicinData.setMedicine_name(mName);
            medicinData.setMedicine_price(mQty);
            medicinData.setMedicine_qty(mPrice);

            allCustomerMedicineList.add(medicinData);

            edtTvMName.setText("");
            edtTvMQTY.setText("");
            edtTvMTotalPrice.setText("");

            if (allCustomerMedicineList.size() > 0) {
                rcVwMedicineData.setVisibility(View.VISIBLE);
                tvNoDataFoundAABL.setVisibility(View.GONE);
                rcVwMedicineData.setLayoutManager(new LinearLayoutManager(this));
                MedicineListAdapter adapter = new MedicineListAdapter(AddBillDetailsActivity.this, allCustomerMedicineList);
                rcVwMedicineData.setAdapter(adapter);
            } else {
                rcVwMedicineData.setVisibility(View.GONE);
                tvNoDataFoundAABL.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCustomerMedicineListData(BillDetailsRoom billDetails) {
        try {

          /*  Customer_Medicinlist medicinData = new Customer_Medicinlist();
            medicinData.setMedicine_name(mName);
            medicinData.setMedicine_price(mQty);
            medicinData.setMedicine_qty(mPrice);

            allCustomerMedicineList.add(medicinData);*/


            allCustomerMedicineList = billDetails.getCustomer_medicinlist();
            edtTvMName.setText("");
            edtTvMQTY.setText("");
            edtTvMTotalPrice.setText("");

            if (allCustomerMedicineList.size() > 0) {
                rcVwMedicineData.setVisibility(View.VISIBLE);
                tvNoDataFoundAABL.setVisibility(View.GONE);
                rcVwMedicineData.setLayoutManager(new LinearLayoutManager(this));
                MedicineListAdapter adapter = new MedicineListAdapter(AddBillDetailsActivity.this, allCustomerMedicineList);
                rcVwMedicineData.setAdapter(adapter);
            } else {
                rcVwMedicineData.setVisibility(View.GONE);
                tvNoDataFoundAABL.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String mName, mQty, mPrice;

    private boolean isValidation() {
        mName = edtTvMName.getText().toString();
        mQty = edtTvMQTY.getText().toString();
        mPrice = edtTvMTotalPrice.getText().toString();

        if (mName.isEmpty()) {
            Toast.makeText(this, "Please enter Medicine Name", Toast.LENGTH_SHORT).show();
            edtTvMName.requestFocus();
            return false;
        } else if (mQty.isEmpty()) {
            Toast.makeText(this, "Please enter Medicine QTY", Toast.LENGTH_SHORT).show();
            edtTvMName.requestFocus();
            return false;
        } else if (mPrice.isEmpty()) {
            Toast.makeText(this, "Please enter Medicine Price", Toast.LENGTH_SHORT).show();
            edtTvMName.requestFocus();
            return false;
        }
        return true;
    }

    //Todo: Save Customer with medicine list Data
    private void saveTask() {
        final String sName = edtTvCName.getText().toString().trim();
        final String sPhone = edtTvCEmail.getText().toString().trim();
        final String sEmail = edtTvCPhone.getText().toString().trim();

        if (sName.isEmpty()) {
            edtTvCName.setError("Name required");
            edtTvCName.requestFocus();
            return;
        }

        if (sEmail.isEmpty()) {
            edtTvCEmail.setError("Email required");
            edtTvCEmail.requestFocus();
            return;
        }

        if (sPhone.isEmpty()) {
            edtTvCPhone.setError("Phone required");
            edtTvCPhone.requestFocus();
            return;
        }


        //Todo: Add user details in database
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a user details add in model class
                BillDetailsRoom task = new BillDetailsRoom();
                task.setCustomer_name(sName);
                task.setCustomer_email(sEmail);
                task.setCustomer_phone(sPhone);
                task.setCustomer_medicinlist(allCustomerMedicineList);

                //Todo: Insert to database using model class
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().billDao().insertBillAll(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), BillListActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }


}
