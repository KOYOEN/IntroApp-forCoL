package com.example.jihwa.project_sw;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by koyo on 20/11/2018.
 */

public class QnAChildViewHolder extends ChildViewHolder {
    private TextView mTextView;
    public QnAChildViewHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.qnaTv);
    }

    public void bind(QnAChild qna){
        mTextView.setText(qna.name);
    }
}
