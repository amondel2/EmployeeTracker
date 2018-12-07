String pass = System.getProperty("DB_PASSWORD")?.toString() ?: System.getenv("DB_PASSWORD")?.toString()
String user = System.getProperty("DB_USER")?.toString()  ?: System.getenv("DB_USER")?.toString()
String dbString = System.getenv("JDBC_CONNECTION_STRING_ET")?.toString() ?: System.getProperty("JDBC_CONNECTION_STRING_ET")?.toString()
dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "com.mysql.jdbc.Driver"
    dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
}

environments {
    development {
        dataSource {
            password = pass
            dbCreate = "create"
            username = user
            url= dbString
            dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
        }
    }
    test {
        dataSource {
            username = "sa"
            password = ''
            dbCreate = "create-drop"
            driverClassName = "org.h2.Driver"
            dialect =  "org.hibernate.dialect.H2Dialect"
//			url = "jdbc:h2:file:myDevDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
        }
    }
    production{
        dataSource {
            dbCreate = "none"
            username = user
            password = pass
            dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
            url= "jdbc:mysql://localhost:3306/emptrack?useUnicode=yes&characterEncoding=UTF-8&autoReconnect=true&useSSL=false"
            properties {
                jmxEnabled = true
                initialSize = 5
                maxActive = 50
                minIdle = 5
                maxIdle = 25
                maxWait = 10000
                maxAge = 600000
                timeBetweenEvictionRunsMillis = 5000
                minEvictableIdleTimeMillis = 60000
                validationQuery = "SELECT 1"
                validationQueryTimeout = 3
                validationInterval = 15000
                testOnBorrow = true
                testWhileIdle= true
                testOnReturn = false
                jdbcInterceptors = "ConnectionState"
                defaultTransactionIsolation= 2 // TRANSACTION_READ_COMMITTED
            }
        }
    }
}