package com.ds.dp.strategy.example1;

/**
 * @Author ds
 * @Date 2021/3/31 10:53
 * @Description 日志记录的上下文
 */
public class LogContext {

   public void log(String log){

      LogStrategy strategy = new DbLog();
      try {
         strategy.write(log);
      } catch (Exception e) {
         strategy = new FileLog();
         strategy.write(log);
      }

   }

}
