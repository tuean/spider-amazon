package enums;

import org.apache.commons.lang.StringUtils;

public enum ResultType {

    db,

    excel

    ;


    public static ResultType getByString(String type) {
        if (StringUtils.isBlank(type)) return ResultType.excel;

        for (ResultType resultType : values()) {
            if (type.trim().equals(resultType.toString())) {
                return resultType;
            }
        }

        return null;
    }

}
