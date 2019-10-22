package cn.newtcl.utils;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.NestedIOException;

import java.io.IOException;

public class BeanFactory extends SqlSessionFactoryBean {

    @Override
    protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
        try {
            return super.buildSqlSessionFactory();
        } catch (NestedIOException e) {
            e.printStackTrace();
            throw new NestedIOException("Failed to parse mapping resource:", e.getCause());
        }
    }
}
