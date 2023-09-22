package com.ebsoft.ebstudytemplates2week.global.mybatis;

import java.io.IOException;
import java.io.InputStream;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@Getter
@Slf4j
public class MybatisConfig {

  private static SqlSessionFactory factory = null;
  static {
    try {
      if(factory==null) {
        InputStream is = Resources.getResourceAsStream("/mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(is);
      }

    } catch (IOException e) {
      log.info(e.getClass() + ": 마이바티스 설정에 문제가 발생했습니다");
      e.printStackTrace();
    }
  }
  public static SqlSession getSqlSession(){
    return factory.openSession();
  }
}
