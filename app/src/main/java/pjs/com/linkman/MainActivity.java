package pjs.com.linkman;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.button)
    Button button;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        startActivityForResult(new Intent(context,LinkManActivity.class),101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==101) {
            String number = data.getStringExtra("number");
            content.setVisibility(View.VISIBLE);
            if(!TextUtils.isEmpty(number)) {
                content.setText("联系方式："+number);
            }else {
                content.setText("获取失败");
            }

        }
    }
}
