package com.global.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PropertiesMapper {

  private final String fileName = "C:\\Users\\alstj\\Desktop\\GitHub\\eBrainSoft_Assignments\\eb-study-templates-1week\\properties.json";
  private String myUrl;
  private String myId;
  private String myPw;

  public PropertiesMapper() {
    try {
      JSONParser parser = new JSONParser();

      Reader reader = new FileReader(fileName);
      JSONObject jsonObject = (JSONObject) parser.parse(reader);

      myUrl = (String) jsonObject.get("myUrl");
      myId = (String) jsonObject.get("myId");
      myPw = (String) jsonObject.get("myPw");
      System.out.println(myUrl);
    } catch (Exception e) {
      System.out.println("properties.Josn 파일이 정상적이지 않습니다.");
    }
  }

  public String getMyUrl() {
    return myUrl;
  }

  public String getMyId() {
    return myId;
  }

  public String getMyPw() {
    return myPw;
  }
}
