package com.wyz.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成器
 * @author 周兆东
 */
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        final String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("wuyanzhong");
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
        	 @Override
        	    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
        	        final String t = fieldType.toLowerCase();
        	        if (t.contains("char")) {
        	            return DbColumnType.STRING;
        	        } else if (t.contains("bigint")) {
        	            return DbColumnType.LONG;
        	        } else if (t.contains("tinyint(1)")) {
        	            return DbColumnType.BOOLEAN;
        	        } else if (t.contains("int")) {
        	            return DbColumnType.INTEGER;
        	        } else if (t.contains("text")) {
        	            return DbColumnType.STRING;
        	        } else if (t.contains("bit")) {
        	            return DbColumnType.BOOLEAN;
        	        } else if (t.contains("decimal")) {
        	            return DbColumnType.BIG_DECIMAL;
        	        } else if (t.contains("clob")) {
        	            return DbColumnType.CLOB;
        	        } else if (t.contains("blob")) {
        	            return DbColumnType.BLOB;
        	        } else if (t.contains("binary")) {
        	            return DbColumnType.BYTE_ARRAY;
        	        } else if (t.contains("float")) {
        	            return DbColumnType.FLOAT;
        	        } else if (t.contains("double")) {
        	            return DbColumnType.DOUBLE;
        	        } else if (t.contains("json") || t.contains("enum")) {
        	            return DbColumnType.STRING;
        	        } else if (t.contains("date") || t.contains("time") || t.contains("year")) {
        	        	return DbColumnType.DATE;//默认返回 java.util.date
        	            /*switch (globalConfig.getDateType()) {
        	                case ONLY_DATE:
        	                    return DbColumnType.DATE;
        	                case SQL_PACK:
        	                    switch (t) {
        	                        case "date":
        	                            return DbColumnType.DATE_SQL;
        	                        case "time":
        	                            return DbColumnType.TIME;
        	                        case "year":
        	                            return DbColumnType.DATE_SQL;
        	                        default:
        	                            return DbColumnType.TIMESTAMP;
        	                    }
        	                case TIME_PACK:
        	                    switch (t) {
        	                        case "date":
        	                            return DbColumnType.LOCAL_DATE;
        	                        case "time":
        	                            return DbColumnType.LOCAL_TIME;
        	                        case "year":
        	                            return DbColumnType.YEAR;
        	                        default:
        	                            return DbColumnType.LOCAL_DATE_TIME;
        	                    }
        	            }*/
        	        }
        	        return DbColumnType.STRING;
        	    
        	 }
        });
        
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("mshtAPP");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/db1?characterEncoding=utf8");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //这里有个模块名的配置，可以注释掉不用。
//        pc.setModuleName(scanner("模块名"));
//        pc.setParent("com.zhouxiaoxi.www");
        pc.setParent(scanner("模块地址"));
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
//         String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
//                        +  + pc.getModuleName() + 如果放开上面的模块名，这里就有一个模块名了
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //数据库表映射到实体的明明策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //自定义继承的Entity类全称，带包名
//        strategy.setSuperEntityClass("***");
        strategy.setEntityLombokModel(false);
        strategy.setRestControllerStyle(true);
        //自定义继承的Controller类全称，带包名
//        strategy.setSuperControllerClass("***");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //自定义基础的Entity类，公共字段（可添加更多）
//        strategy.setSuperEntityColumns("id");
        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //表前缀
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
