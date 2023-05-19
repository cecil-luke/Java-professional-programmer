package com.etoak;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

public class MPGenerator {

  // @Test
  public void generate() {
    AutoGenerator generator = new AutoGenerator();

    GlobalConfig globalConfig = new GlobalConfig();
    globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
    globalConfig.setAuthor("et2301");
    globalConfig.setOpen(false);
    // 重新生成时文件是否覆盖
    globalConfig.setFileOverride(true);
    // 去掉Service接口的首字母I
    globalConfig.setServiceName("%sService");
    // 主键策略
    globalConfig.setIdType(IdType.AUTO);
    // 定义生成的实体类中日期类型
    globalConfig.setDateType(DateType.ONLY_DATE);
    // mapper映射文件中生成 ResultMap
    globalConfig.setBaseResultMap(true);
    // mapper映射文件中生成 基本字段
    globalConfig.setBaseColumnList(true);
    generator.setGlobalConfig(globalConfig);

    // 数据源配置
    DataSourceConfig dataSourceConfig = new DataSourceConfig();
    dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
    dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/et2301?serverTimezone=UTC");
    dataSourceConfig.setUsername("root");
    dataSourceConfig.setPassword("etoak");
    dataSourceConfig.setDbType(DbType.MYSQL);
    generator.setDataSource(dataSourceConfig);

    // 包配置
    PackageConfig packageConfig = new PackageConfig();
    packageConfig.setParent("com.etoak.system");
    packageConfig.setController("controller");
    packageConfig.setEntity("entity");
    packageConfig.setService("service");
    packageConfig.setMapper("mapper");
    // Mapper映射文件
    packageConfig.setXml("mapper");
    generator.setPackageInfo(packageConfig);

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    // 数据库表映射到实体的命名
    strategy.setNaming(NamingStrategy.underline_to_camel);
    // 设置表前缀不生成
    strategy.setTablePrefix("t_");
    // 数据库表字段映射
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    // restful api风格控制器
    strategy.setRestControllerStyle(true);
    // url中驼峰转连字符
    strategy.setControllerMappingHyphenStyle(false);
    // "t_%"
    strategy.setLikeTable(new LikeTable("t_", SqlLike.RIGHT));
    //
    strategy.setInclude("t_car");

    strategy.setEntityLombokModel(true);
    generator.setStrategy(strategy);
    generator.execute();
  }

}
