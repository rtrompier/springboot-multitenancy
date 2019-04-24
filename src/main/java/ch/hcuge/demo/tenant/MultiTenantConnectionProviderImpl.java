package ch.hcuge.demo.tenant;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@Component
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider {

    @Autowired
    private DataSource dataSource;

    public MultiTenantConnectionProviderImpl() {
        System.out.println("hello");
//        this.dataSource = this.initDataSource();
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifie) throws SQLException {
        String tenantIdentifier = ThreadLocalStorage.getTenantName();
        final Connection connection = getAnyConnection();
//        try {
//            connection.createStatement().execute("USE " + tenantIdentifier);
//        } catch ( SQLException e ) {
//            throw new HibernateException("Problem setting schema to " + tenantIdentifier, e);
//        }
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        try {
            connection.createStatement().execute( "USE " + tenantIdentifier );
        } catch ( SQLException e ) {
            throw new HibernateException("Problem setting schema to " + tenantIdentifier, e );
        }
        connection.close();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return null;
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return true;
    }




//    public DataSource initDataSource() {
//        AbstractRoutingDataSource dataSource = new TenantAwareRoutingSource();
//
//        Map<Object,Object> targetDataSources = new HashMap<>();
//
//        targetDataSources.put("concerto", concerto());
//        targetDataSources.put("greco", greco());
//
//        dataSource.setTargetDataSources(targetDataSources);
//        dataSource.afterPropertiesSet();
//
//        return dataSource;
//    }
//
//    public DataSource concerto() {
//
//        HikariDataSource dataSource = new HikariDataSource();
//
//        dataSource.setInitializationFailTimeout(0);
//        dataSource.setMaximumPoolSize(5);
//        dataSource.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
//        dataSource.addDataSourceProperty("url", "jdbc:postgresql://localhost:5432/concerto");
//        dataSource.addDataSourceProperty("user", "concerto");
//        dataSource.addDataSourceProperty("password", "concerto");
//
//        return dataSource;
//    }
//
//    public DataSource greco() {
//
//        HikariDataSource dataSource = new HikariDataSource();
//
//        dataSource.setInitializationFailTimeout(0);
//        dataSource.setMaximumPoolSize(5);
//        dataSource.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
//        dataSource.addDataSourceProperty("url", "jdbc:postgresql://localhost:5433/greco");
//        dataSource.addDataSourceProperty("user", "greco");
//        dataSource.addDataSourceProperty("password", "greco");
//
//        return dataSource;
//    }

}
