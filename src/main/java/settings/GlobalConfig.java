package settings;

import enums.ResultType;
import logger.MineLogger;
import lombok.*;
import util.FileUtil;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.rmi.AccessException;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GlobalConfig {

    private String baseHost;

    private String searchUrl;

    private String key;

    private String separator;

    private String remote;

    private ResultType resultType;

    private String outPath;

    private String dataBasePath;

    private String user;

    private String password;

    private String excelFileName;

    private boolean htmlSave;


    public static String baseHost_default = "https://www.amazon.com.au";

    public static String outPath_default = "/tmp/";

    public static String excelFileName_default = "result.xlsx";

    private static String pageEnd = "&page=";

    public String getBaseHost() {
        return this.baseHost == null ? baseHost_default : this.baseHost;
    }

    public void setBaseHost(String baseHost) {
        this.baseHost = baseHost;
    }

    public String getSearchUrl() {
        return getBaseHost() + "/s?k=%s&lo=grid&__mk_zh_CN=亚马逊网站";
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }


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

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public String getOutPath() {
        String defaultPath = FileUtil.getProjectPath() + File.separator + key + ".xlsx";
        return outPath == null ?  defaultPath: outPath;
    }

    public void setOutPath(String outPath) throws AccessDeniedException {
        try {
            File file = new File(outPath);
            if (!file.canRead() || !file.canWrite()) {
                MineLogger.log("this outPath has no access");
                throw new AccessDeniedException("this outPath has no access");
            }
            System.out.println(file);
        } catch (Exception var) {
            MineLogger.log("this outPath is unreachable");
            throw new AccessDeniedException("this outPath is unreachable");
        }

        this.outPath = outPath;
    }

    public String getDataBasePath() {
        return dataBasePath;
    }

    public void setDataBasePath(String dataBasePath) {
        this.dataBasePath = dataBasePath;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExcelFileName() {
        return excelFileName == null ? excelFileName_default : excelFileName;
    }

    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }

    public String getRequestUrl(String key, int page) {
        key = URLEncoder.encode(key, StandardCharsets.UTF_8);
        String format = String.format(getSearchUrl(), key);
        if (page > 1) {
            format += pageEnd + page;
        }

        return format;
    }

    public boolean isHtmlSave() {
        return htmlSave;
    }

    public void setHtmlSave(boolean htmlSave) {
        this.htmlSave = htmlSave;
    }

    public String getRequestUrl(String key) {
        return getRequestUrl(key, 1);
    }

}
