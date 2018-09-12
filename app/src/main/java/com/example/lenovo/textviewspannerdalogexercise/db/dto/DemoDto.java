package com.example.lenovo.textviewspannerdalogexercise.db.dto;

import com.example.lenovo.textviewspannerdalogexercise.db.DbConst;
import com.example.lenovo.textviewspannerdalogexercise.utils.Session;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = DbConst.DemoTable.TABLE_NAME)
public class DemoDto extends Session implements Serializable {

    @DatabaseField(columnName = DbConst.DemoTable.COLUMN_ID, generatedId = true, canBeNull = false)
    private int id;
    @DatabaseField(columnName = DbConst.DemoTable.COLUMN_COURSEID, canBeNull = true)
    private String courseID;
    @DatabaseField(columnName = DbConst.DemoTable.COLUMN_COURSETITLE, canBeNull = true)
    private String courseTitle;
    @DatabaseField(columnName = DbConst.DemoTable.COLUMN_DATE, canBeNull = true)
    private String date;

    public DemoDto() {
    }

    public DemoDto(String courseID, String courseTitle, String date) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}