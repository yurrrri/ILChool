package model.dao;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Recruit;

public class RecruitXmlParser {

//    xml���� �о���� �±׸� ������ enum  �� ������ ������ �������� �ʰ� ������ ���� ����� ���
    private enum TagType { NONE, COMPANY_NAME, RECRUIT_URL, COMPANY_FORM , TITLE, WORKINGTYPE, REGDATE, DEADLINE  };     

    //    parsing ����� tag�� ����� ����
    private final static String ITEM_TAG = "dhsOpenEmpInfo";
    private final static String COMPANY_NAME_TAG = "empBusiNm";
    private final static String RECRUIT_URL_TAG = "empWantedHomepgDetail";
    private final static String COMPANY_FORM_TAG = "coClcdNm";
    private final static String TITLE_TAG = "empWantedTitle";
    private final static String WORKING_TYPE_TAG = "empWantedTypeNm";
    private final static String REGDATE_TAG = "empWantedStdt";
    private final static String DEADLINE_TAG = "empWantedEndt";

    private XmlPullParser parser;

    public RecruitXmlParser() {
//        xml �ļ� ���� �������� �ʿ信 ���� ��������� ���� �� �����ڿ��� �ʱ�ȭ
//        �ļ� �غ�
        XmlPullParserFactory factory = null;

//        �ļ� ����
        try {
            factory = XmlPullParserFactory.newInstance();
            parser = factory.newPullParser();
//         ���� ó��
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Recruit> recruitListFromWorknet() throws IOException {
        ArrayList<Recruit> recrList = new ArrayList();
        DateFormat dateFormat = new SimpleDateFormat ("yyyymmdd");
        Recruit rc = null;
        TagType tagType = TagType.NONE;     //  �±׸� �����ϱ� ���� enum ���� �ʱ�ȭ

        try {
            URL url;
            url = new URL("http://openapi.work.go.kr/opi/opi/opia/dhsOpenEmpInfoAPI.do?authKey=WNKG8R5KJBCBJU79NY0XX2VR1HK&"
            		+ "callTp=L&"
            		+ "returnType=XML&"
            		+ "startPage=1&"
            		+ "display=200");

            HttpURLConnection urlConn= (HttpURLConnection) url.openConnection(); //openConnection �ش� ��û�� ���ؼ� �� �� �ִ� connection ��ü 
            urlConn.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(),"UTF-8"));
            
            String data="";
            String msg;
            while((msg = br.readLine())!=null){
                data += msg;
            }
            parser.setInput(new StringReader(data));

            // �±� ���� ���� ���� �غ�
            int eventType = parser.getEventType();

            while (eventType!=XmlPullParser.END_DOCUMENT){
                switch(eventType){
                    case XmlPullParser.START_DOCUMENT:
                        break;
                        case XmlPullParser.START_TAG:
                        String tag = parser.getName();
                        if (tag.equals(ITEM_TAG)){ //���ο� �׸��� ǥ���ϴ� �±׸� ������ ��� dto ��ü �غ�
                            rc = new Recruit();
                        } else if (tag.equals(COMPANY_NAME_TAG)){
//                       �����ִ� �±׸� �о��� ���, �±� Ÿ���� �����ص�
                            tagType = TagType.COMPANY_NAME;
                        } else if (tag.equals(RECRUIT_URL_TAG)){
                            tagType = TagType.RECRUIT_URL;
                        } else if (tag.equals(COMPANY_FORM_TAG)) {
                        	tagType=TagType.COMPANY_FORM;
                        } else if (tag.equals(TITLE_TAG)){
                            tagType=TagType.TITLE;
                        } else if (tag.equals(WORKING_TYPE_TAG)){
                            tagType=TagType.WORKINGTYPE;
                        } else if (tag.equals(REGDATE_TAG)) {
                        	tagType =TagType.REGDATE;
                        }
                        else if (tag.equals(DEADLINE_TAG)){
                            tagType=TagType.DEADLINE;
                        }
                        break;
                    case XmlPullParser.END_TAG:
//                        �������� �������� ��Ÿ���Ƿ� �� �±׸� ������ list�� dto ����
                        if (parser.getName().equals(ITEM_TAG)){
                        	recrList.add(rc);
                        }
                        break;
                    case XmlPullParser.TEXT:
//                        START TAG�� �������� ����ص� tagType���� �±� ����
                        switch(tagType){
                            case COMPANY_NAME:
                                rc.setCompany_name(parser.getText());
                                break;
                            case RECRUIT_URL:
                                rc.setRecruit_url(parser.getText());
                                break;
                            case COMPANY_FORM:
                            	rc.setCompany_form(parser.getText());
                                break;
                            case TITLE:
                                rc.setTitle(parser.getText());
                                break;
                            case WORKINGTYPE:
                                rc.setWorkingType(parser.getText());
                                break;
                            case REGDATE:
                            	String rg = parser.getText();
                            	Date regDate = dateFormat.parse(rg);
                            	rc.setRegDate(regDate);
                            	break;
                            case DEADLINE:
                            	String dl = parser.getText();
                            	Date deadLine = dateFormat.parse(dl);
                                rc.setDeadLine(deadLine);
                                break;
                        }
//                  �����ִ� Tag�� �ƴϸ� or ó���� �� ������ NONE���� �ʱ�ȭ
                        tagType = TagType.NONE;
                        break;
                }
                eventType = parser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return recrList;
    }
}