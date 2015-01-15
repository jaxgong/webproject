package nagaseiori.commons.utils;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlGenUtil {
	
	private static Logger logger = LoggerFactory.getLogger(SqlGenUtil.class);
	final static int FLAG_100 = 100;
	final static int FLAG_1000 = 1000;
	
	final static String outPutFileRootPath = "D://";
	final static String readFilePath = "D://create_table.sql";
	
	public static void generateCreateTableSql(String tableName, int flag){
		File file = new File(readFilePath);
		if(file.exists()){
			logger.warn("template file is not exists");
		}
		
	}
}
