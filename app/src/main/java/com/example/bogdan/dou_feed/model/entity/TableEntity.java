package com.example.bogdan.dou_feed.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 24.06.16
 */
public class TableEntity implements Element{
    private List<List<String>> mTable;
    private List<String> mTableRow;
    private Article.Type mType;

    public TableEntity() {
        mType = Article.Type.TABLE;
        mTable = new ArrayList<>();
    }

    void addCell(String cell) {
        mTableRow.add(cell);
    }

    void addRow() {
        mTableRow = new ArrayList<>();
        mTable.add(mTableRow);
    }

    int size() {
        return mTable.size();
    }

    int rowSize(int position) {
        return mTable.get(position).size();
    }

    List<String> getTableRow(int position) {
        return mTable.get(position);
    }

    public void addRowCell(String cell) {
        addCell(cell);
    }

    public void addTableRow() {
        addRow();
    }

    public int tableSize() {
        return size();
    }

    public int tableRowSize(int index) {
        return rowSize(index);
    }

    public String getRowCell(int rowIndex, int position) {
        return getTableRow(rowIndex).get(position);
    }

    public Article.Type getmType() {
        return mType;
    }
}
