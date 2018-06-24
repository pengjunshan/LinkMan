package pjs.com.linkman;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinkManActivity extends AppCompatActivity {

    Context context;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private PersonAdapter adapter;
    private List<Map<String, String>> mapList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_man);
        ButterKnife.bind(this);

        context = this;
        //1.获取系统自带的联系人uri
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        //2.用一个数组来装要获取的信息
        String[] contentArr = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

        //3.获取内容提供者解析类,ContentResolver
        ContentResolver resolver = this.getContentResolver();

        //4.调用provider提供的方法进行数据库CRUD操作
        Cursor cursor = resolver.query(uri, contentArr, null, null, null);

        //5.通过游标遍历获取数据,并添加到集合中
        Map<String,String> map = null;
        while (cursor.moveToNext()){
            String name = cursor.getString(0);
            String number = cursor.getString(1);
            map = new HashMap<>();
            map.put("phoneName",name);
            map.put("phoneNumber",number);
            mapList.add(map);
        }
        cursor.close();

        if(mapList!=null&&mapList.size()>0) {
            adapter = new PersonAdapter(context,mapList);
            recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            recyclerView.addItemDecoration(new DividerItemDecoration(this,
                    DividerItemDecoration.VERTICAL_LIST));
            recyclerView.setAdapter(adapter);

            /**
             * 此处点击回调给MainActivity
             */
            adapter.setOnClickItemPosion(new PersonAdapter.OnClickItemPosion() {
                @Override
                public void onClickItem(String number) {
                    Intent intent = new Intent();
                    intent.putExtra("number",number);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }
    }
}
