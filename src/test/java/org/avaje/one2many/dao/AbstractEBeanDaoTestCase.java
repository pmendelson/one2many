package org.avaje.one2many.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import com.avaje.ebean.EbeanServer;

@ContextConfiguration(locations = { "/applicationContext.xml", "/applicationContext-default_config.xml" })
public abstract class AbstractEBeanDaoTestCase extends AbstractJUnit4SpringContextTests {
    @Autowired
    protected EbeanServer ebeanServer;

    @Autowired
    protected DataSource dataSource;

    protected AbstractEBeanDaoTestCase simpleJdbcTemplate;

    public AbstractEBeanDaoTestCase() {
        simpleJdbcTemplate = this;
    }
    protected int update(String sql) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            int r = connection.createStatement().executeUpdate(sql);
            return r;
        } catch (SQLException e) {
            Assert.fail(e.getMessage());
        } finally {
            close(connection);
        }
        return 0;
    }

    /**
     * Count the rows in the given table.
     * 
     * @param tableName
     *            table name to count rows in
     * @return the number of rows in the table
     */
    protected int countRowsInTable(String tableName) {
        Connection connection = null;
        ResultSet r = null;
        try {
            connection = dataSource.getConnection();
            r = connection.createStatement().executeQuery("select count(0) from " + tableName);
            r.next();
            return r.getInt(1);
        } catch (SQLException e) {
            Assert.fail(e.getMessage());
        } finally {
            close(r);
            close(connection);
        }
        return Integer.MIN_VALUE;
    }

    protected void close(Connection resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }
    }
    protected void close(ResultSet resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }
    }
}
