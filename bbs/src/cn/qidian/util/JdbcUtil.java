package cn.qidian.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {
      private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
      public static ComboPooledDataSource getDataSource(){
    	  return dataSource;
      }
}
