package com.example.bogdan.dou_feed.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public class ArticleEntity {
    private String mAuthor;
    private String mDate;
    private String mTitle;

    private TableElement mTableElement;
    private List<Element> mArticleElements;

    public ArticleEntity() {
        mArticleElements = new ArrayList<>();
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getDate() {
        return mDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public int size() {
        return mArticleElements.size();
    }

    public void addElement(Type type, String content) {
        Element element = new Element(type, content);
        mArticleElements.add(element);
    }

    public void addTable() {
        mTableElement = new TableElement();
        mArticleElements.add(mTableElement);
    }

    public void addRowCell(String cell) {
        mTableElement.addCell(cell);
    }

    public void addTableRow() {
        mTableElement.addRow();
    }

    private void getTableElement(int position) {
        mTableElement = (TableElement)getElement(position);
    }

    public int tableSize() {
        return mTableElement.size();
    }

    public int tableRowSize(int index) {
        return mTableElement.rowSize(index);
    }

    private List<String> getTableRow(int index) {
        return mTableElement.getTableRow(index);
    }

    public String getRowCell(int rowIndex, int position) {
        return mTableElement.getTableRow(rowIndex).get(position);
    }

    public Element getElement(int position) {
        return mArticleElements.get(position);
    }

    public Type getType(int position) {
        return mArticleElements.get(position).getType();
    }

    public String getContent(int position) {
        return mArticleElements.get(position).getContent();
    }


    private class Element {
        private Type mType;
        private String mContent;

        private Element(Type type, String content) {
            mType = type;
            mContent = content;
        }

        public Type getType() {
            return mType;
        }

        public String getContent() {
            return mContent;
        }
    }

    private class TableElement extends Element {
        private List<List<String>> mTable;
        private List<String> mTableRow;

        private TableElement() {
            super(Type.TABLE, "");
            mTable = new ArrayList<>();
        }

        public void addCell(String cell) {
            mTableRow.add(cell);
        }

        public void addRow() {
            mTableRow = new ArrayList<>();
            mTable.add(mTableRow);
        }

        public int size() {
            return mTable.size();
        }

        public int rowSize(int position) {
            return mTable.get(position).size();
        }

        public List<String> getTableRow(int position) {
            return mTable.get(position);
        }
    }

    public enum Type {
        CONTENT,
        IMAGE,
        CONTENT_HEADING,
        CONTENT_CODE,
        LINK,
        BLOCKQUOTE,
        TABLE
    }


}
