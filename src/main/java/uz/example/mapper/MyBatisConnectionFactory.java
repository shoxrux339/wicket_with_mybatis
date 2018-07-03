package uz.example.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Logger;

/**
 * Class {@code MyBatisConnectionFactory} is common class with one task - create Connection.
 *
 * @since 1.0.1
 */

public class MyBatisConnectionFactory {
 
    private static SqlSessionFactory sqlSessionFactory;
    private static Logger log = Logger.getLogger(MyBatisConnectionFactory.class.getName());

    private MyBatisConnectionFactory(){}
 
    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
 
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            }
        }
        catch (FileNotFoundException fileNotFoundException) {
            log.warning("fileNotFoundException");
        }
        catch (IOException iOException) {
            log.warning("cannot open connection");
        }
    }
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}

