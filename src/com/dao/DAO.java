package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;

public abstract class DAO
{
    protected Connection conn;
    protected SimpleDateFormat dateFormatComplete = new SimpleDateFormat("dd-MM-yyyy");

    public DAO(Connection conn)
    {
        this.conn = conn;
    }

    protected String formatCompleteDate(Date date)
    {
        Long resultDate = date.getTime();
        java.util.Date rawDate = new java.util.Date(resultDate);
        return this.dateFormatComplete.format(rawDate);
    }
}
