package com.example.jihwa.project_sw;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by koyo on 20/11/2018.
 */

public class QnAGroupViewHolder extends GroupViewHolder {
    private TextView mTextView;
    private ImageView arrow;
    public QnAGroupViewHolder(View itemView) {
        super(itemView);

        mTextView = itemView.findViewById(R.id.qnaTv);
        arrow = itemView.findViewById(R.id.groupArrow);
    }
    public void bind(QnAGroup qnaGroup){
        mTextView.setText(qnaGroup.getTitle());
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}
