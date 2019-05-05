/*
 * User: satish
 * Created Date: Aug 23, 2001 9:27:44 AM
 * $Id: JDBCAppenderImpl.java,v 1.1 2008/03/19 12:12:24 gbs00324 Exp $
 * $version$
 */
package com.util.log4debug.impl;

import it.sella.sql.DataSourceFactory;
import it.sella.sql.DataSourceFactoryException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.log4j.jdbc.JDBCAppender;

/**
 * 
 * @author BSI18
 *
 * @deprecated This appender has been moved to it.sella.util.log4j_appender package of ExternalLib/log4j_appender.jar
 * Any appender extending this class cannot run in wls8.1 environment.
 */
public class JDBCAppenderImpl extends JDBCAppender {
    //private static final Log4Debug log4Debug = Log4DebugFactory.getLog4Debug(JDBCAppenderImpl.class);

    public JDBCAppenderImpl()
    {
        super();
    }

    protected Connection getConnection()
            throws SQLException
    {
        Connection connection = null;
        try
        {
            DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();
            String clientPool = Log4DebugProperties.getProperty("DataSourcePool");
            if (clientPool == null || "".equals(clientPool))
            {
                throw new RuntimeException("DataSourcePool property cannot ne null or empty");
            }
            DataSource dataSource = dataSourceFactory.getDataSource(clientPool);
            connection = dataSource.getConnection();
        }
        catch (DataSourceFactoryException dataSourceFactoryException)
        {
            dataSourceFactoryException.printStackTrace(System.err);
            throw new SQLException(dataSourceFactoryException.getMessage());
        }
        return connection;
    }

    protected void closeConnection(Connection con)
    {
        if (con != null)
        {
            try
            {
                con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace(System.err);
            }
        }
    }

    protected void closePreparedStatement(PreparedStatement preparedStatement)
    {
        if (preparedStatement != null)
        {
            try
            {
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace(System.err);
            }
        }
    }

    public boolean requiresLayout()
    {
        return true;
    }

    protected void execute(String sql) throws SQLException
    {
        Connection con = null;
        Statement stmt = null;
        PreparedStatement preparedStatement = null;
        if (sql != null)
        {
            try
            {
                con = getConnection();
               // log4Debug.debug(sql);
               // log4Debug.debug("<<<<<<<<<<<<<< Before Execution of Prepared statement >>>>>>>>>>>>>>>>>>");
                System.out.println(sql);
                System.out.println("<<<<<<<<<<<<<< Before Execution of Prepared statement >>>>>>>>>>>>>>>>>>");
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.executeUpdate();
               // log4Debug.debug("<<<<<<<<<<<<<< After Execution of Prepared statement >>>>>>>>>>>>>>>>>>");
                System.out.println("<<<<<<<<<<<<<< After Execution of Prepared statement >>>>>>>>>>>>>>>>>>");
                //stmt.executeUpdate(sql);
            }
            catch (SQLException e)
            {
                throw e;
            }
            finally
            {
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (stmt != null)
                {
                    stmt.close();
                }
                closeConnection(con);
            }
        }
    }
}

