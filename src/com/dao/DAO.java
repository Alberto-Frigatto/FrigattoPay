package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;

public abstract class DAO
{
    protected Connection conn;
    protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public DAO(Connection conn)
    {
        this.conn = conn;
    }

    protected String formatDate(Date date)
    {
        Long resultDate = date.getTime();
        java.util.Date rawDate = new java.util.Date(resultDate);
        return this.dateFormat.format(rawDate);
    }
}
