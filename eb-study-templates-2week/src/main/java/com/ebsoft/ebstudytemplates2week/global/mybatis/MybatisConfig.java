package com.ebsoft.ebstudytemplates2week.global.mybatis;

import java.io.IOException;
import java.io.InputStream;
import lombok.Getter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@Getter
public class MybatisConfig {

  String resource = "/mybatis-config.xml";

  SqlSession sqlSession = null;

  public MybatisConfig() {
    try {
      InputStream is = Resources.getResourceAsStream(resource);
      SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
      sqlSession = factory.openSession(false);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
