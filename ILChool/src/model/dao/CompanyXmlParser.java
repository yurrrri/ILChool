package model.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import model.Company;

public class CompanyXmlParser {
//  xml에서 읽어들일 태그를 구분한 enum  → 정수값 등으로 구분하지 않고 가독성 높은 방식을 사용
  private enum TagType { NONE, COMPANY, URL, FORM , SUMMARY  };     

  //    parsing 대상인 tag를 상수로 선언
  private final static String ITEM_TAG = "dhsOpenEmpHireInfo";
  private final static String COMPANY_NAME_TAG = "coNm";
  private final static String URL_TAG = "homepg";
  private final static String FORM_TAG = "coClcdNm";
  private final static String SUMMARY_TAG = "coIntroSummaryCont";

  private XmlPullParser parser;

  public CompanyXmlParser() {
//      xml 파서 관련 변수들은 필요에 따라 멤버변수로 선언 후 생성자에서 초기화
//      파서 준비
      XmlPullParserFactory factory = null;

//      파서 생성
      try {
          factory = XmlPullParserFactory.newInstance();
          parser = factory.newPullParser();
//       예외 처리
      } catch (XmlPullParserException e) {
          e.printStackTrace();
      }
  }

// 회사 리스트 from worknet api (최대 100개까지 호출 가능)
  public List<Company> companyListFromWorknet() throws IOException {
	  Company com = null;
	  List<Company> comList = new ArrayList<Company>();
      TagType tagType = TagType.NONE;     //  태그를 구분하기 위한 enum 변수 초기화

      try {
          URL url;
          url = new URL("http://openapi.work.go.kr/opi/opi/opia/dhsOpenEmpHireInfoAPI.do?authKey=WNKG8R5KJBCBJU79NY0XX2VR1HK"
          		+ "&callTp=L"
          		+ "&returnType=XML"
          		+ "&startPage=1"
          		+ "&display=100");
                    
          HttpURLConnection urlConn= (HttpURLConnection) url.openConnection(); //openConnection 해당 요청에 대해서 쓸 수 있는 connection 객체 
          urlConn.setRequestMethod("GET");
          BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
          
          String data="";
          String msg;
          while((msg = br.readLine())!=null){
              data += msg;
          }
          parser.setInput(new StringReader(data));

          // 태그 유형 구분 변수 준비
          int eventType = parser.getEventType();
    	  String text = ""; //parser.getText()

          while (eventType!=XmlPullParser.END_DOCUMENT){
              switch(eventType){
                  case XmlPullParser.START_DOCUMENT:
                      break;
                      case XmlPullParser.START_TAG:
                      String tag = parser.getName();
                      if (tag.equals(ITEM_TAG)){ //새로운 항목을 표현하는 태그를 만났을 경우 dto 객체 준비
                    	  com = new Company();
                      } else if (tag.equals(COMPANY_NAME_TAG)){
//                     관심있는 태그를 읽었을 경우, 태그 타입을 저장해둠
                    	  tagType = TagType.COMPANY;
                      } else if (tag.equals(URL_TAG)){
                          tagType = TagType.URL;
                      } else if (tag.equals(FORM_TAG)){
                    	  tagType=TagType.FORM;
                      } else if (tag.equals(SUMMARY_TAG)){
                          tagType=TagType.SUMMARY;
                      } else {
                    	  tagType=TagType.NONE;
                      }
                      break;
                  case XmlPullParser.END_TAG:
//                      아이템의 마지막을 나타내므로 이 태그를 만나면 list에 dto 저장
                	  if (parser.getName().equals(ITEM_TAG)){
                    	  comList.add(com);
                      }
                      break;
                  case XmlPullParser.TEXT:
//                      START TAG를 만났을때 기로해둔 tagType으로 태그 구분
                      switch(tagType){
                          case COMPANY:
                        	  text = parser.getText();
                        	  com.setName(text);
                              break;
                          case URL:
                        	  text = parser.getText();
                        	  com.setUrl(text);
                              break;
                          case FORM:
                        	  text = parser.getText();
                        	  com.setForm(text);
                              break;
                          case SUMMARY:
                        	  text = parser.getText();
                              com.setSummary(text);
                              break;
                      }
//                관심있는 Tag가 아니면 or 처리가 다 끝나면 NONE으로 초기화
                      tagType = TagType.NONE;
                      break;
              }
              eventType = parser.next();
          }

      } catch (Exception e) {
          e.printStackTrace();
      }
      return comList;
  }
  public List<Company> findCompany(String comName) {
	  Company com = null;
	  List<Company> comList = new ArrayList<Company>();
      TagType tagType = TagType.NONE;     //  태그를 구분하기 위한 enum 변수 초기화

      try {
          URL url;
          url = new URL("http://openapi.work.go.kr/opi/opi/opia/dhsOpenEmpHireInfoAPI.do?authKey=WNKG8R5KJBCBJU79NY0XX2VR1HK"
          		+ "&callTp=L"
          		+ "&returnType=XML"
          		+ "&startPage=1"
          		+ "&coNm="
          		+ URLDecoder.decode(comName, "UTF-8"));
          
          System.out.println(url);
                    
          HttpURLConnection urlConn= (HttpURLConnection) url.openConnection(); //openConnection 해당 요청에 대해서 쓸 수 있는 connection 객체 
          urlConn.setRequestMethod("GET");
          BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
          
          String data="";
          String msg;
          while((msg = br.readLine())!=null){
              data += msg;
          }
          parser.setInput(new StringReader(data));

          // 태그 유형 구분 변수 준비
          int eventType = parser.getEventType();
    	  String text = ""; //parser.getText()

          while (eventType!=XmlPullParser.END_DOCUMENT){
              switch(eventType){
                  case XmlPullParser.START_DOCUMENT:
                      break;
                      case XmlPullParser.START_TAG:
                      String tag = parser.getName();
                      if (tag.equals(ITEM_TAG)){ //새로운 항목을 표현하는 태그를 만났을 경우 dto 객체 준비
                    	  com = new Company();
                      } else if (tag.equals(COMPANY_NAME_TAG)){
//                     관심있는 태그를 읽었을 경우, 태그 타입을 저장해둠
                    	  tagType = TagType.COMPANY;
                      } else if (tag.equals(URL_TAG)){
                          tagType = TagType.URL;
                      } else if (tag.equals(FORM_TAG)){
                    	  tagType=TagType.FORM;
                      } else if (tag.equals(SUMMARY_TAG)){
                          tagType=TagType.SUMMARY;
                      } else {
                    	  tagType=TagType.NONE;
                      }
                      break;
                  case XmlPullParser.END_TAG:
//                      아이템의 마지막을 나타내므로 이 태그를 만나면 list에 dto 저장
                	  if (parser.getName().equals(ITEM_TAG)){
                    	  comList.add(com);
                      }
                      break;
                  case XmlPullParser.TEXT:
//                      START TAG를 만났을때 기로해둔 tagType으로 태그 구분
                      switch(tagType){
                          case COMPANY:
                        	  text = parser.getText();
                        	  com.setName(text);
                              break;
                          case URL:
                        	  text = parser.getText();
                        	  com.setUrl(text);
                              break;
                          case FORM:
                        	  text = parser.getText();
                        	  com.setForm(text);
                              break;
                          case SUMMARY:
                        	  text = parser.getText();
                              com.setSummary(text);
                              break;
                      }
//                관심있는 Tag가 아니면 or 처리가 다 끝나면 NONE으로 초기화
                      tagType = TagType.NONE;
                      break;
              }
              eventType = parser.next();
          }

      } catch (Exception e) {
          e.printStackTrace();
      }
      return comList;
  }
}
