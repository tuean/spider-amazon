package parser;

import exception.BusinessException;

public interface ResultParser {

    Object parse(String content, String searchKey) throws BusinessException;

}
