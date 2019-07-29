package com.example.jihwa.project_sw;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by koyo on 20/11/2018.
 */

public class QnAGroup extends ExpandableGroup<QnAChild> {
    public QnAGroup(String title, List<QnAChild> items) {
        super(title, items);
    }
}
