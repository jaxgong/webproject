package nagaseiori.commons.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlGenUtil {
	
	private static Logger logger = LoggerFactory.getLogger(SqlGenUtil.class);
	final static int FLAG_100 = 100;
	final static int FLAG_1000 = 1000;
	
	final static String outPutFileRootPath = "D://createTable.sql";
	final static String readFilePath = "D://create_table.sql";
	
	public static void generateCreateTableSql(String tableName, int flag) throws IOException{
		File file = new File(readFilePath);
		if(!file.exists()){
			logger.warn("template file is not exists");
		}
		File outPutFile = new File(outPutFileRootPath);
		if(!outPutFile.exists()){
			outPutFile.createNewFile();
		}
		String tmpTableName = null;
		OutputStream os = new FileOutputStream(outPutFile);
		for(int i=0; i < flag; i++){
			if(flag == FLAG_100 && i< 10){
				tmpTableName = tableName + "_0" + i;
			}else if(flag == FLAG_1000 && i < 10){
				tmpTableName = tableName + "_00" + i;
			}else if(flag == FLAG_1000 && i < 100){
				tmpTableName = tableName + "_0" + i;
			}else{
				tmpTableName = tableName + "_" + i;
			}
			String createTable = "create table " + tmpTableName + " like " + tableName + ";\n";
			IOUtils.write(createTable, os);
		}
		os.flush();
		os.close();
	}
	
	
	public static void main(String[] args) throws IOException {
		generateCreateTableSql("moments_msg", FLAG_1000);
	}
}
