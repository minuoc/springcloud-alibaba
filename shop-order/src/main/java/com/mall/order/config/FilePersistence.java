package com.mall.order.config;

import com.alibaba.csp.sentinel.command.handler.ModifyParamFlowRulesCommandHandler;
import com.alibaba.csp.sentinel.datasource.*;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author chenlufeng
 * @date 2022/2/26
 */
public class FilePersistence implements InitFunc {

    @Value("spring.application:name")
    private String applicationName;

    @Override
    public void init() throws Exception {
        String ruleDir = System.getProperty("user.home") + "/sentinel-rules/" + applicationName;
        String flowRulePath = ruleDir + "/flow-rule.json";
        String degradeRulePath = ruleDir + "/degrade-rule.json";
        String systemRulePath = ruleDir + "/system-rule.json";
        String authorityRulePath = ruleDir + "/authority-rule.json";
        String paramFlowRulePath = ruleDir + "/param-flow-rule.json";
        this.mkDirIfNotExist(ruleDir);
        this.createFileIfNotExist(flowRulePath);
        this.createFileIfNotExist(degradeRulePath);
        this.createFileIfNotExist(systemRulePath);
        this.createFileIfNotExist(authorityRulePath);
        this.createFileIfNotExist(paramFlowRulePath);

        //流控规则
        ReadableDataSource<String, List<FlowRule>> flowRuleRDS = new FileRefreshableDataSource<List<FlowRule>>(
                flowRulePath,
                flowRuleListParser);

        FlowRuleManager.register2Property(flowRuleRDS.getProperty());
        WritableDataSource<List<FlowRule>> flowRuleWDS = new FileWritableDataSource<List<FlowRule>>(
                flowRulePath,
                this::encodeJson
        );
        WritableDataSourceRegistry.registerFlowDataSource(flowRuleWDS);

        //降级规则
        ReadableDataSource<String,List<DegradeRule>> degradeRuleRDS = new FileRefreshableDataSource<List<DegradeRule>>(
                degradeRulePath,
                degradeRuleListParser
        );

        DegradeRuleManager.register2Property(degradeRuleRDS.getProperty());
        WritableDataSource<List<DegradeRule>> degradeRuleWDS = new FileWritableDataSource<List<DegradeRule>>(
                degradeRulePath,
                this::encodeJson
        );
        WritableDataSourceRegistry.registerDegradeDataSource(degradeRuleWDS);


        // 系统规则
        ReadableDataSource<String, List<SystemRule>> systemRuleRDS = new FileRefreshableDataSource<>(
                systemRulePath,
                systemRuleListParser
        );
        SystemRuleManager.register2Property(systemRuleRDS.getProperty());
        WritableDataSource<List<SystemRule>> systemRuleWDS = new FileWritableDataSource<>(
                systemRulePath,
                this::encodeJson
        );
        WritableDataSourceRegistry.registerSystemDataSource(systemRuleWDS);

        // 授权规则
        ReadableDataSource<String, List<AuthorityRule>> authorityRuleRDS = new FileRefreshableDataSource<>(
                authorityRulePath,
                authorityRuleListParser
        );
        AuthorityRuleManager.register2Property(authorityRuleRDS.getProperty());
        WritableDataSource<List<AuthorityRule>> authorityRuleWDS = new FileWritableDataSource<>(
                authorityRulePath,
                this::encodeJson
        );
        WritableDataSourceRegistry.registerAuthorityDataSource(authorityRuleWDS);

        // 热点参数规则
        ReadableDataSource<String, List<ParamFlowRule>> paramFlowRuleRDS = new FileRefreshableDataSource<>(
                paramFlowRulePath,
                paramFlowRuleListParser
        );
        ParamFlowRuleManager.register2Property(paramFlowRuleRDS.getProperty());
        WritableDataSource<List<ParamFlowRule>> paramFlowRuleWDS = new FileWritableDataSource<>(
                paramFlowRulePath,
                this::encodeJson
        );
        ModifyParamFlowRulesCommandHandler.setWritableDataSource(paramFlowRuleWDS);



    }


    private Converter<String, List<FlowRule>> flowRuleListParser = source -> JSON.parseObject(
            source,
            new TypeReference<List<FlowRule>>() {
            }
    );
    private Converter<String, List<DegradeRule>> degradeRuleListParser = source -> JSON.parseObject(
            source,
            new TypeReference<List<DegradeRule>>() {
            }
    );
    private Converter<String, List<SystemRule>> systemRuleListParser = source -> JSON.parseObject(
            source,
            new TypeReference<List<SystemRule>>() {
            }
    );

    private Converter<String, List<AuthorityRule>> authorityRuleListParser = source -> JSON.parseObject(
            source,
            new TypeReference<List<AuthorityRule>>() {
            }
    );

    private Converter<String, List<ParamFlowRule>> paramFlowRuleListParser = source -> JSON.parseObject(
            source,
            new TypeReference<List<ParamFlowRule>>() {
            }
    );

    private void mkDirIfNotExist(String ruleDir) {
        File file = new File(ruleDir);
        if (!file.exists()){
            file.mkdirs();
        }
    }

    public void createFileIfNotExist(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()){
            file.createNewFile();
        }
    }

    private <T> String encodeJson(T t) {
        return JSON.toJSONString(t);
    }



}
