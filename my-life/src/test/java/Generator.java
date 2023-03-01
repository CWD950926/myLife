import com.baomidou.mybatisplus.core.toolkit.StringPool;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author maqh
 * @date 2019/9/6 16:41
 */
public class Generator {

    public static void main(String[] args) {

        //模块名称
        String packageName = "person";

        //数据库表名称
        String tables = "person_fitness";


        boolean isGenAll = true;//true:dto、controller、service、mapper层也生成；false:只生成po和xml

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("generator");
        gc.setOpen(false);
        //gc.setIdType(IdType.ID_WORKER);
        gc.setFileOverride(true);//是否覆盖文件
        gc.setBaseResultMap(true); // xml resultmap
        gc.setBaseColumnList(true); // xml columlist
        gc.setSwagger2(true);  //实体属性 Swagger2 注解

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/cwd?&characterEncoding=utf8&useSSL=false&serverTimezone=UTC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if (fieldType.toLowerCase().contains("datetime")) {
                    return DbColumnType.DATE;
                }
                return super.processTypeConvert(globalConfig, fieldType);
            }

        });
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();

        pc.setParent("com.module");
        pc.setController(packageName + ".controller");
        pc.setEntity(packageName + ".po");
        pc.setService(packageName + ".service");
        pc.setServiceImpl(packageName + ".service" + ".impl");
        pc.setMapper(packageName + ".dao");
        pc.setXml(packageName + ".dao");
        mpg.setPackageInfo(pc);


        final String[] poName = {""};

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }

            @Override
            public void initTableMap(TableInfo tableInfo)  {
                Map<String, Object> map = new HashMap<>();
                map.put("module", packageName);


                String entityName=tableInfo.getEntityName();
                char[] charArray = entityName.toCharArray();
                charArray[0] += 32;
                poName[0] = String.valueOf(charArray);
                map.put("poName", poName[0]);
                this.setMap(map);
            }
        };


        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        // 自定义配置会被优先输出
        if (isGenAll) {

            focList.add(new FileOutConfig("/templates/poReq.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    setCommone(tableInfo);
                    return projectPath + "/src/main/java/com/module/" + packageName + "/dto/" + tableInfo.getEntityName().toLowerCase() + "/" + tableInfo.getEntityName() + "Req"
                            + StringPool.DOT_JAVA;
                }
            });

            focList.add(new FileOutConfig("/templates/poRsp.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    setCommone(tableInfo);
                    return projectPath + "/src/main/java/com/module/" + packageName + "/dto/" + tableInfo.getEntityName().toLowerCase() + "/" + tableInfo.getEntityName() + "Rsp"
                            + StringPool.DOT_JAVA;
                }
            });

            focList.add(new FileOutConfig("/templates/addReq.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    setCommone(tableInfo);
                    return projectPath + "/src/main/java/com/module/" + packageName + "/dto/" + tableInfo.getEntityName().toLowerCase() + "/Add" + tableInfo.getEntityName() + "Req"
                            + StringPool.DOT_JAVA;
                }
            });

            focList.add(new FileOutConfig("/templates/updateReq.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    setCommone(tableInfo);
                    return projectPath + "/src/main/java/com/module/" + packageName + "/dto/" + tableInfo.getEntityName().toLowerCase() + "/Update" + tableInfo.getEntityName() + "Req"
                            + StringPool.DOT_JAVA;
                }
            });


        }

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        if (!isGenAll) {
            templateConfig.disable(TemplateType.CONTROLLER, TemplateType.SERVICE, TemplateType.MAPPER);

        }
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(false);
        //strategy.setEntityTableFieldAnnotationEnable(true);
        // 公共父类
        // 写于父类中的公共字段

        strategy.setInclude(tables.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("t_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


    public static void setCommone(TableInfo tableInfo) {

        String tableComment = tableInfo.getComment();

        if (org.apache.commons.lang3.StringUtils.isNotBlank(tableComment)) {
            int nIndex = tableComment.indexOf("\r\n");
            if (nIndex < 0) {
                nIndex = tableComment.indexOf("\n");

            }
            if (nIndex > 0) {
                tableComment = tableComment.substring(0, nIndex);
            }
        }
        tableInfo.setComment(tableComment);
    }


}
