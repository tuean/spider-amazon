package entity;

import enums.ResultType;
import settings.GlobalConfig;

import java.util.List;

public class SearchContainer {

    private String key;

    private String separator;

    private ResultType resultType;

    private List<GlobalConfig> configList;

    private boolean initFlag;

    public SearchContainer() {}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public List<GlobalConfig> getConfigList() {
        return configList;
    }

    public void setConfigList(List<GlobalConfig> configList) {
        this.configList = configList;
    }

    public boolean isInitFlag() {
        return initFlag;
    }

    public void setInitFlag(boolean initFlag) {
        this.initFlag = initFlag;
    }
}
