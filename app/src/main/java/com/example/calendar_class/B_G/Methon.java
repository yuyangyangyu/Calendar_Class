package com.example.calendar_class.B_G;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Methon {
	private static ArrayList<Class_detail> list =new ArrayList<Class_detail>();
	private static String[] Str1= {"双周","单周","3节","4节"};
	private Document document;
	public void Get_class(String Student_Num,String state) {
		try {
			if (state.equals("线路1")){
				document=Jsoup.connect(String.format("http://jwzx.cquptx.cn/kebiao/kb_stu.php?xh=%s", Student_Num))
						.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
						.header("Connection", "close")//如果是这种方式，这里务必带上
						.timeout(8000)//超时时间
						.get();
			}else{
				document=Jsoup.connect(String.format("http://jwzx.cqu.pt/kebiao/kb_stu.php?xh=%s", Student_Num))
						.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
						.header("Connection", "close")//如果是这种方式，这里务必带上
						.timeout(8000)//超时时间
						.get();
			}
			Elements elements=document.select("tbody").get(0).select("tr");
			//分别获取一天中1-12节课的信息（8次 午间间歇（2）下午间歇（5））
			for (int i = 0; i < elements.size(); i++) {
				if (i==2|i==5)
					continue;
				Elements element =elements.get(i).select("td");
				GetClass(element,i);	
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取课程信息
	 * @param elements
	 * @param Num
	 */
	public static void GetClass(Elements elements,int Num) {
		Elements elements2 =elements.select("td");
		for (int i = 1; i < elements2.size(); i++) {
			Element elements3 =elements2.get(i);
			if (elements3.text().length()==0)
				continue;
			String [] buffStrings =elements3.text().split(" ");
            buffStrings[2]=buffStrings[2].replace("地点：", " ");
			String S=buffStrings[3];
			String PP="NU";
			for (int j = 0; j < Str1.length; j++) {
				Pattern pattern =Pattern.compile(Str1[j]);
				Matcher matcher=pattern.matcher(S);
				while (matcher.find()) {
					PP=matcher.group();	
				}
				if (PP.equals("单周")) {
					Class_detail lesson = new Class_detail(buffStrings[1], buffStrings[2]+" "
					+buffStrings[4],buffStrings[3], Num,i,1);
					list.add(lesson);
					break;
				}
				if (PP.equals("双周")) {
					Class_detail lesson = new Class_detail(buffStrings[1], buffStrings[2]+" "
					+buffStrings[4],buffStrings[3], Num,i,2);
					list.add(lesson);
					break;
				}
				if (PP.equals("3节")) {
					Class_detail lesson = new Class_detail(buffStrings[1], buffStrings[2]+" "
					+buffStrings[4],buffStrings[3], Num,i,3);
					list.add(lesson);
					break;
				}
				if (PP.equals("4节")) {
					Class_detail lesson = new Class_detail(buffStrings[1], buffStrings[2]+" "
					+buffStrings[4],buffStrings[3], Num,i,4);
					list.add(lesson);
					break;
				}
			}
			if (PP.equals("NU")) {
				Class_detail lesson = new Class_detail(buffStrings[1], buffStrings[2]+" "
						+buffStrings[4],buffStrings[3], Num,i,0);
						list.add(lesson);
			}	
		}
	}
	/**
	 * 获取所有课程信息
	 * @return
	 */
	public static ArrayList<Class_detail> getList() {
		return list;
	}

}
