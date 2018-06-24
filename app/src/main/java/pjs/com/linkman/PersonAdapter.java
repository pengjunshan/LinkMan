package pjs.com.linkman;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * 创建： PengJunShan on 2018-06-23  16:06
 * 描述：
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder>{

    private Context context;
    private List<Map<String, String>> mapList;

    public PersonAdapter(Context context, List<Map<String, String>> mapList) {
        this.context = context;
        this.mapList = mapList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_person,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Map<String, String> map = mapList.get(position);
        holder.name.setText(map.get("phoneName"));
        holder.number.setText(map.get("phoneNumber"));
    }

    @Override
    public int getItemCount() {
        if(mapList!=null&&mapList.size()>0) {
            return mapList.size();
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView name,number;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onClickItemPosion!=null) {
                        onClickItemPosion.onClickItem(mapList.get(getLayoutPosition()).get("phoneNumber"));
                    }
                }
            });

        }
    }


    private OnClickItemPosion onClickItemPosion;

    public void setOnClickItemPosion(OnClickItemPosion onClickItemPosion) {
        this.onClickItemPosion = onClickItemPosion;
    }

    public interface OnClickItemPosion{
        void onClickItem(String number);
    }
}
