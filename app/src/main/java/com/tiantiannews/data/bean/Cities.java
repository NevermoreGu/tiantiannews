package com.tiantiannews.data.bean;

import java.util.List;

public class Cities {

    /**
     * id : 1
     * rank : S
     * acronym : bj
     * onlineTime : 1267632000
     * divisionStr : 北京--北京
     * name : 北京
     * locationId : 110000
     * lng : 116.395645
     * lat : 39.929986
     * pinyin : beijing
     * isOpen : true
     */

    public List<DataBean> data;

    public static class DataBean {
        public int id;
        public String rank;
        public String acronym;
        public long onlineTime;
        public String divisionStr;
        public String name;
        public String locationId;
        public double lng;
        public double lat;
        public String pinyin;
        public boolean isOpen;
    }
}
