package com.wission.ui.billDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wission.Dao.Bill_roomDao;
import com.wission.Database.DatabaseClient;
import com.wission.Model.BillDetailsRoom;
import com.wission.Model.UserDetails_Room;
import com.wission.R;
import com.wission.ui.addUserDetails.UserListAdapter;
import com.wission.ui.addUserDetails.UserList_RoomActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillListActivity extends AppCompatActivity {


    @BindView(R.id.imgVwAddCustomerBillABL)
    ImageView imgVwAddCustomerBillABL;

    @BindView(R.id.rVwBillCustomerListAM)
    RecyclerView rVwBillCustomerListAM;

    @BindView(R.id.tvBillDataNotFound)
    TextView tvBillDataNotFound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_list);
        ButterKnife.bind(this);

        rVwBillCustomerListAM.setLayoutManager(new LinearLayoutManager(this));

        imgVwAddCustomerBillABL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillListActivity.this, AddBillDetailsActivity.class);
                intent.putExtra("class","add");
                startActivity(intent);
            }
        });

        getBillListTaskData();
    }

    private void getBillListTaskData() {
        //Todo: Get All Bill List Data using asyncTask

        try {
            class GetTasks extends AsyncTask<Void, Void, List<BillDetailsRoom>> {

                @Override
                protected List<BillDetailsRoom> doInBackground(Void... voids) {
                    //Todo: Get all details using
                    List<BillDetailsRoom> taskList = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().billDao().getAll();
                    return taskList;
                }

                @Override
                protected void onPostExecute(List<BillDetailsRoom> tasks) {
                    super.onPostExecute(tasks);
                    if (tasks.size()>0) {
                        BillListAdapter adapter = new BillListAdapter(BillListActivity.this, tasks);
                        rVwBillCustomerListAM.setAdapter(adapter);
                    }else {
                        Toast.makeText(BillListActivity.this, "No Data Found.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            GetTasks gt = new GetTasks();
            gt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
