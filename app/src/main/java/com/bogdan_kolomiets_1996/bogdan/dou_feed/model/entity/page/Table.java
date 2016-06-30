package com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.page;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 24.06.16
 */
public class Table extends PageElement{
    private List<List<String>> mTable;
    private List<String> mTableRow;

    public Table() {
        mTable = new ArrayList<>();
    }

    public int size() {
        return mTable.size();
    }

    public void addTableRow() {
        mTableRow = new ArrayList<>();
        mTable.add(mTableRow);
    }

    public List<String> getTableRow(int position) {
        return mTable.get(position);
    }

    public int rowSize(int position) {
        return mTable.get(position).size();
    }

    public void addRowCell(String cell) {
        mTableRow.add(cell);
    }

    public String getRowCell(int rowIndex, int position) {
        return getTableRow(rowIndex).get(position);
    }

    @Override
    public void display(LayoutInflater inflater, ViewGroup container) {
        HorizontalScrollView scrollView =
                (HorizontalScrollView) inflater.inflate(R.layout.article_table, null);
        TableLayout table = (TableLayout) scrollView.findViewById(R.id.articleTable);

        for (int i = 0; i < this.size(); i++) {
            TableRow tableRow = new TableRow(inflater.getContext());
            for (int j = 0; j < this.rowSize(i); j++) {
                TextView cellView = new TextView(inflater.getContext());
                cellView.setBackgroundDrawable(ContextCompat
                        .getDrawable(inflater.getContext(), R.drawable.table_cell_border));
                cellView.setPadding(20, 10, 20, 10);
                cellView.setTextColor(ContextCompat
                        .getColor(inflater.getContext(), R.color.black));
                cellView.setText(this.getRowCell(i, j));
                tableRow.addView(cellView);
            }

            table.addView(tableRow);
        }

        container.addView(scrollView);
    }
}
