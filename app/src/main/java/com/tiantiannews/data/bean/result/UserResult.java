package com.tiantiannews.data.bean.result;

import java.util.List;

public class UserResult {

    public String cmd;
    public int result;
    public String resultNote;
    public int pages;
    public int pageNo;
    public Detail detail;

    public static class Detail {

        public String userId;
        public String userName;
        public String realName;
        public String nickName;
        public String userType;
        public String age;
        public String sex;
        public String birthday;
        public String email;
        public String address;
        public String end_platform;
        public String login_ipaddress;
        public String corpId;
        public String corpName;
        public int appType;
        public String appTypeName;
        public String appLogo;
        public String deptId;
        public String deptName;
        public String roleId;
        public String roleName;
        public String cityId;
        public String cityName;
        public String provinceId;
        public String provinceName;
        public String regionName;
        public String corpDomain;
        public String longitude;
        public String latitude;
        public String operatorCorpId;
        public String operatorCorpName;
        public String operatorDeptId;
        public String operatorDeptName;
        public String appName;
        public String mobile;
        public String operator_corp_id;
        public String operator_dept_id;
        public int lightFirePush;
        public String demo;
        public int isAutoOpenAlarmWin;
        public int isShowAlarmWin;
        public int openPhoneRenew;
        public String defaultPrivObjId;
        public String privLpno;
        public String privIdName;
        public String vspId;
        public String vspName;
        public String authId;
        public String authName;
        public String isBind;
        public String defaultShareObjId;
        public String shareLpno;
        public String shareIdName;
        public int openRentSet;
        public int openSecondhandSaleSet;
        public int openResuceSet;
        public int openBehalfDriveSet;
        public int openAccessorySet;
        public int openSendDangerSet;
        public String licensePackageId;
        public String logoURL;
        public String bindObjId;
        public String bindLpno;
        public String bindIdName;
        public String bindTime;
        public String qqTokenExpireTime;
        public String sinaTokenExpireTime;
        public boolean hasDailyAttendence;
        public int vipLevel;

        public List<UserPermissionsBean> userPermissions;

        public List<MenuBean> menu;

        public static class UserPermissionsBean {
            public String privilegeCode;
            public String privilegeName;
        }

        public static class MenuBean {
            public String productId;
            public String productname;
            public String productPackageId;
            public String productUrl;
            public String iconUrl;
            public int productOrder;
        }
    }
}
