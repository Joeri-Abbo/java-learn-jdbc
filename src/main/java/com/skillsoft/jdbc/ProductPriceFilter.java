package com.skillsoft.jdbc;

import java.sql.SQLException;
import javax.sql.RowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.Predicate;

public class ProductPriceFilter implements Predicate {
    private double lowPrice;
    private double highPrice;

    private String colName = null;
    private int colNumber = -1;

    public ProductPriceFilter(double lowPrice, double highPrice, int colNumber) {
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.colNumber = colNumber;
    }

    public ProductPriceFilter(double lowPrice, double highPrice, String colName) {
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.colName = colName;
    }

    @Override
    public boolean evaluate(RowSet rs) {
        if (rs == null) {
            return false;
        }

        FilteredRowSet frs = (FilteredRowSet) rs;
        boolean evaluation = false;

        try {
            double columnValue = frs.getDouble(this.colNumber);

            if ((columnValue >= this.lowPrice) && (columnValue <= this.highPrice)) {
                evaluation = true;
            }
        } catch (SQLException e) {
            return false;
        }

        return evaluation;
    }

    @Override
    public boolean evaluate(Object value, int columnNumber) throws SQLException {
        boolean evaluation = true;

        if (colNumber == columnNumber) {
            double columnValue = ((Double) value).doubleValue();
            if ((columnValue >= this.lowPrice) && (columnValue <= this.highPrice)) {
                evaluation = true;
            } else {
                evaluation = false;
            }
        }
        return evaluation;
    }

    @Override
    public boolean evaluate(Object value, String columnName) throws SQLException {
        boolean evaluation = false;
        if (columnName.equalsIgnoreCase(this.colName)) {
            double columnValue = ((Double) value).doubleValue();
            if ((columnValue >= this.lowPrice) && (columnValue <= this.highPrice)) {
                evaluation = true;
            } else {
                evaluation = false;
            }
        }

        return evaluation;
    }
}
