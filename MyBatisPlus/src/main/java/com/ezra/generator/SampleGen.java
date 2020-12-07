package com.ezra.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成
 * @author sonic.liu on 2019/02/26
 */
public class SampleGen {
    /**  项目输出包路径 */
    private static final String PARENT = "com.xyedu.gradjob.openplatform.provider.recruit";
    /**  项目输出路径 */
    private static final String OUTPUT_DIR = "/gradjob-openplatform/gradjob-openplatform-provider/gradjob-openplatform-recruit-service/src/main/java";


    /**  需求生成代码对应的数据库表 */
    private static  String[] INCLUDE_TABLE;
    static {
        String table = "code_career_type";
        INCLUDE_TABLE = table.split(", ");
    }
    /**  作者 */
    private static final String AUTHOR = "yan sai";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        String projectPath = System.getProperty("user.dir");
        // 全局配置
        GlobalConfig gc = configGlobal(projectPath);
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = configDb();
        mpg.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(MOUDLE_NAME);
        pc.setParent(PARENT);
//        pc.setXml("../../../../../../../resources/mapper/");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = configInjectionConfig(projectPath, pc.getModuleName());
        mpg.setCfg(cfg);
//        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = configStrategy(pc.getModuleName());
        mpg.setStrategy(strategy);
        // 选择 veloctity 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

    private static List<String> getTables() {
        List<String> tableNameList = new ArrayList<>(0);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.1.205:3306/gdrecruit?useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai", "root", "123456");
            con.setAutoCommit(false);

            String sel_sql= "select TABLE_NAME as tableName from information_schema.`TABLES` where TABLE_SCHEMA = 'gdrecruit' and  TABLE_NAME like 'code_' ";

            PreparedStatement ps = con.prepareStatement(sel_sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                tableNameList.add(rs.getString("tableName"));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tableNameList;
    }

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

    private static DataSourceConfig configDb(){
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.1.205:3306/gdrecruit?useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        return dsc;
    }



    private static GlobalConfig configGlobal(String projectPath){
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + OUTPUT_DIR);
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        // 是否覆盖文件
        gc.setFileOverride(true);
        return gc;
    }

    private static StrategyConfig configStrategy(String moduleName){
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//            strategy.setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity");
        strategy.setEntityLombokModel(true);
//            strategy.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
        strategy.setInclude(INCLUDE_TABLE);
//        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(MOUDLE_NAME + "_");
        strategy.setRestControllerStyle(true);
        return  strategy;
    }

    private static InjectionConfig configInjectionConfig(String projectPath, String moduleName){
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/" + moduleName
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }



}
