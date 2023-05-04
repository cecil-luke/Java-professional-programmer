package com.etoak.rst.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author Lushisan
 * @Date 2023/4/25 15:28
 * @Title: ETConfig
 * @Description:
 * @Package com.etoak.rst.config
 */
@Configuration
@ComponentScan(basePackages = "com.etoak.rst")
@PropertySource("classpath:db.properties")
@MapperScan(basePackages = "com.etoak.rst.mapper")
@EnableTransactionManagement
@Import(ETMVCConfig.class)
public class ETConfig {
    @Bean
    public SqlSessionFactoryBean factoryBean() throws IOException {
        SqlSessionFactoryBean f = new SqlSessionFactoryBean();
        f.setDataSource(this.ds()); //数据库
        f.setTypeAliasesPackage("com.etoak.rst.pojo");    //别名
        //解析通配符 *CustomerMapper.xml
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] rs = resolver.getResources("classpath:mappers/*mapper.xml");
        f.setMapperLocations(rs);   //SQL映射文件

        /*分页 相当于拦截器*/
        PageInterceptor inter = new PageInterceptor();
        Properties props = new Properties();
        props.setProperty("helperDialect","mysql");
        inter.setProperties(props);
        f.setPlugins(inter);

        return f;
    }
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        //每个文件上传最大限制2M
        resolver.setMaxInMemorySize(2097152);
        return resolver;
    }
    @Bean
    public TransactionManager tm(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(this.ds());
        return dataSourceTransactionManager;
    }
    @Bean
    public DataSource ds(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(this.driver);
        ds.setUrl(this.url);
        ds.setUsername(this.name);
        ds.setPassword(this.pwd);
        ds.setMaxActive(this.maxActive);
        ds.setMaxWait(this.maxWait);
        return ds;
    }
    @Value("${maxActive}")
    private int maxActive;
    @Value("${maxWait}")
    private Long maxWait;
    @Value("${m.driver}")
    private String driver;
    @Value("${m.url}")
    private String url;
    @Value("${m.user}")
    private String name;
    @Value("${m.pwd}")
    private String pwd;
}
