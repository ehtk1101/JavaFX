package org.dimigo.gui.javaproject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpresswayCrawling {
    public static List<ExpressWay> getexpressList(String s) {
        try {
            Document doc = Jsoup.connect("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=%EA%B3%A0%EC%86%8D%EB%8F%84%EB%A1%9C+%EC%83%81%ED%99%A9").get();
            Elements upspeed = doc.select("[id=cs_traffic_realtime_up_frame] ul li dl dd");
            Elements upname = doc.select("[id=cs_traffic_realtime_up_frame] ul li dl dt a");
            Elements dnspeed = doc.select(  "[id=cs_traffic_realtime_dn_frame] ul li dl dd");
            Elements dnname = doc.select("[id=cs_traffic_realtime_dn_frame] ul li dl dt a");
            List<String> upnamelist = upname.eachText();
            List<String> upspeedlist = upspeed.eachText();
            List<String> dnnamelist = dnname.eachText();
            List<String> dnspeedlist = dnspeed.eachText();

            Map<String, String> upmap = makeMap(upnamelist, upspeedlist);
            Map<String, String> dnmap = makeMap(dnnamelist, dnspeedlist);

            List<ExpressWay> uplist = makeExpressWayList(upmap);
            List<ExpressWay> dnlist = makeExpressWayList(dnmap);

            if(s.equals("up")) return uplist;
            else if(s.equals("dn")) return dnlist;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<ExpressWay> makeExpressWayList(Map<String, String> map) {
        List<ExpressWay> list = new ArrayList<>();
        for(String key : map.keySet()){
            String[] array = map.get(key).split(" ");
            String subname = array[0];
            String distance = array[2];
            String speed = array[4];
            String time = array[5];
            list.add(new ExpressWay(key,subname,distance,speed,time));
        }
        return list;
    }

    private static Map<String, String> makeMap(List<String> namelist, List<String> speedlist){
        Map<String, String> map = new HashMap<>();
        List<String> speedlist2 = new ArrayList<>();
        int speed2size = namelist.size();
        for(int i = 0; i< speed2size; i++){
            String a = speedlist.get(0) + " " +speedlist.get(1);
            speedlist.remove(0);
            speedlist.remove(0);
            speedlist2.add(a);
        }
        for(int i = 0;i<speed2size;i++){
            map.put(namelist.get(i), speedlist2.get(i));
        }
        return map;
    }
}