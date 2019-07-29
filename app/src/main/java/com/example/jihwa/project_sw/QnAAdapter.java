package com.example.jihwa.project_sw;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by koyo on 20/11/2018.
 */

public class QnAAdapter extends ExpandableRecyclerViewAdapter<QnAGroupViewHolder, QnAChildViewHolder> {

    public QnAAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public QnAGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_qna_group, parent, false);
        return new QnAGroupViewHolder(v);

    }

    @Override
    public QnAChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_qna, parent, false);
        return new QnAChildViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(QnAChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final QnAChild qna = (QnAChild) group.getItems().get(childIndex);
        holder.bind(qna);
    }

    @Override
    public void onBindGroupViewHolder(QnAGroupViewHolder holder, int flatPosition, ExpandableGroup group) {
        final QnAGroup qnaGroup = (QnAGroup) group;
        holder.bind(qnaGroup);
    }
}
