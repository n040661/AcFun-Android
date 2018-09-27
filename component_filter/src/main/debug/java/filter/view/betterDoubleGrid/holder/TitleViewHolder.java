package filter.view.betterDoubleGrid.holder;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hubertyoung.component_filter.R;
import com.hubertyoung.component_filter.filter.util.UIUtil;

/**
 * auther: baiiu
 * time: 16/6/5 05 23:30
 * description:
 */
public class TitleViewHolder extends RecyclerView.ViewHolder {

    public TitleViewHolder( Context mContext, ViewGroup parent) {
        super( UIUtil.infalte(mContext, R.layout.holder_title, parent));
    }


    public void bind(String s) {
        ((TextView ) itemView).setText(s);
    }
}
