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
//  xml���� �о���� �±׸� ������ enum  �� ������ ������ �������� �ʰ� ������ ���� ����� ���
  private enum TagType { NONE, COMPANY, URL, FORM , SUMMARY  };     

  //    parsing ����� tag�� ����� ����
  private final static String ITEM_TAG = "dhsOpenEmpHireInfo";
  private final static String COMPANY_NAME_TAG = "coNm";
  private final static String URL_TAG = "homepg";
  private final static String FORM_TAG = "coClcdNm";
  private final static String SUMMARY_TAG = "coIntroSummaryCont";

  private XmlPullParser parser;

  public CompanyXmlParser() {
//      xml �ļ� ���� �������� �ʿ信 ���� ��������� ���� �� �����ڿ��� �ʱ�ȭ
//      �ļ� �غ�
      XmlPullParserFactory factory = null;

//      �ļ� ����
      try {
          factory = XmlPullParserFactory.newInstance();
          parser = factory.newPullParser();
//       ���� ó��
      } catch (XmlPullParserException e) {
          e.printStackTrace();
      }
  }

// ȸ�� ����Ʈ from worknet api (�ִ� 100������ ȣ�� ����)
  public List<Company> companyListFromWorknet() throws IOException {
	  Company com = null;
	  List<Company> comList = new ArrayList<Company>();
      TagType tagType = TagType.NONE;     //  �±׸� �����ϱ� ���� enum ���� �ʱ�ȭ

      try {
          URL url;
          url = new URL("http://openapi.work.go.kr/opi/opi/opia/dhsOpenEmpHireInfoAPI.do?authKey=WNKG8R5KJBCBJU79NY0XX2VR1HK"
          		+ "&callTp=L"
          		+ "&returnType=XML"
          		+ "&startPage=1"
          		+ "&display=100");
                    
          HttpURLConnection urlConn= (HttpURLConnection) url.openConnection(); //openConnection �ش� ��û�� ���ؼ� �� �� �ִ� connection ��ü 
          urlConn.setRequestMethod("GET");
          BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
          
          String data="";
          String msg;
          while((msg = br.readLine())!=null){
              data += msg;
          }
          parser.setInput(new StringReader(data));

          // �±� ���� ���� ���� �غ�
          int eventType = parser.getEventType();
    	  String text = ""; //parser.getText()

          while (eventType!=XmlPullParser.END_DOCUMENT){
              switch(eventType){
                  case XmlPullParser.START_DOCUMENT:
                      break;
                      case XmlPullParser.START_TAG:
                      String tag = parser.getName();
                      if (tag.equals(ITEM_TAG)){ //���ο� �׸��� ǥ���ϴ� �±׸� ������ ��� dto ��ü �غ�
                    	  com = new Company();
                      } else if (tag.equals(COMPANY_NAME_TAG)){
//                     �����ִ� �±׸� �о��� ���, �±� Ÿ���� �����ص�
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
//                      �������� �������� ��Ÿ���Ƿ� �� �±׸� ������ list�� dto ����
                	  if (parser.getName().equals(ITEM_TAG)){
                    	  comList.add(com);
                      }
                      break;
                  case XmlPullParser.TEXT:
//                      START TAG�� �������� ����ص� tagType���� �±� ����
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
//                �����ִ� Tag�� �ƴϸ� or ó���� �� ������ NONE���� �ʱ�ȭ
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
      TagType tagType = TagType.NONE;     //  �±׸� �����ϱ� ���� enum ���� �ʱ�ȭ

      try {
          URL url;
          url = new URL("http://openapi.work.go.kr/opi/opi/opia/dhsOpenEmpHireInfoAPI.do?authKey=WNKG8R5KJBCBJU79NY0XX2VR1HK"
          		+ "&callTp=L"
          		+ "&returnType=XML"
          		+ "&startPage=1"
          		+ "&coNm="
          		+ URLDecoder.decode(comName, "UTF-8"));
          
          System.out.println(url);
                    
          HttpURLConnection urlConn= (HttpURLConnection) url.openConnection(); //openConnection �ش� ��û�� ���ؼ� �� �� �ִ� connection ��ü 
          urlConn.setRequestMethod("GET");
          BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
          
          String data="";
          String msg;
          while((msg = br.readLine())!=null){
              data += msg;
          }
          parser.setInput(new StringReader(data));

          // �±� ���� ���� ���� �غ�
          int eventType = parser.getEventType();
    	  String text = ""; //parser.getText()

          while (eventType!=XmlPullParser.END_DOCUMENT){
              switch(eventType){
                  case XmlPullParser.START_DOCUMENT:
                      break;
                      case XmlPullParser.START_TAG:
                      String tag = parser.getName();
                      if (tag.equals(ITEM_TAG)){ //���ο� �׸��� ǥ���ϴ� �±׸� ������ ��� dto ��ü �غ�
                    	  com = new Company();
                      } else if (tag.equals(COMPANY_NAME_TAG)){
//                     �����ִ� �±׸� �о��� ���, �±� Ÿ���� �����ص�
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
//                      �������� �������� ��Ÿ���Ƿ� �� �±׸� ������ list�� dto ����
                	  if (parser.getName().equals(ITEM_TAG)){
                    	  comList.add(com);
                      }
                      break;
                  case XmlPullParser.TEXT:
//                      START TAG�� �������� ����ص� tagType���� �±� ����
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
//                �����ִ� Tag�� �ƴϸ� or ó���� �� ������ NONE���� �ʱ�ȭ
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
