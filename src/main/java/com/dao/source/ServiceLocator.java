package com.dao.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ServiceLocator {
    private static final Logger logger = LoggerFactory.getLogger(ServiceLocator.class);

    public static DataSource getDataSource(String name) {
        Context context = null;
        DataSource dataSource = null;

        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup(name);
        } catch (NamingException e) {
            logger.info("ServiceLocator fails", e);
            throw new RuntimeException(e);
        }
        return dataSource;
    }
}
