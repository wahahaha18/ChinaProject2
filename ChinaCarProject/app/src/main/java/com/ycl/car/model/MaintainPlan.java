package com.ycl.car.model;

import java.util.List;

/**
 * 维修计划
 * Created by y11621546 on 2017/2/15.
 */

public class MaintainPlan {

    /**
     * equip : {"ext2":null,"eqwksp_id":1,"eqtype_id":1,"eqspeci":"型号","eqno":"eq_01","adduser_id":1,"eqfactory":"厂家","id":1,"eqdepartment_id":1,"eqname":"滚床1","ext1":null,"sort":1,"useless":"使用情况","ext3":null,"installocation":"安装地点","eqsystem_id":1}
     * files : [{"fname":"Book1","fileurl":"/file/Book1.xlsx","filext":"xlsx"}]
     * o : {"pmcontent":"检查电机轴承，并进行润滑，若发现轴承损坏， 则予以更换","status":-1,"pmark":"备注","plandate":"计划制定中","pmid":1}
     */

    private EquipBean equip;
    private OBean o;
    private List<FilesBean> files;

    public EquipBean getEquip() {
        return equip;
    }

    public void setEquip(EquipBean equip) {
        this.equip = equip;
    }

    public OBean getO() {
        return o;
    }

    public void setO(OBean o) {
        this.o = o;
    }

    public List<FilesBean> getFiles() {
        return files;
    }

    public void setFiles(List<FilesBean> files) {
        this.files = files;
    }

    public static class EquipBean {
        /**
         * ext2 : null
         * eqwksp_id : 1
         * eqtype_id : 1
         * eqspeci : 型号
         * eqno : eq_01
         * adduser_id : 1
         * eqfactory : 厂家
         * id : 1
         * eqdepartment_id : 1
         * eqname : 滚床1
         * ext1 : null
         * sort : 1
         * useless : 使用情况
         * ext3 : null
         * installocation : 安装地点
         * eqsystem_id : 1
         */

        private Object ext2;
        private int eqwksp_id;
        private int eqtype_id;
        private String eqspeci;
        private String eqno;
        private int adduser_id;
        private String eqfactory;
        private int id;
        private int eqdepartment_id;
        private String eqname;
        private Object ext1;
        private int sort;
        private String useless;
        private Object ext3;
        private String installocation;
        private int eqsystem_id;

        public Object getExt2() {
            return ext2;
        }

        public void setExt2(Object ext2) {
            this.ext2 = ext2;
        }

        public int getEqwksp_id() {
            return eqwksp_id;
        }

        public void setEqwksp_id(int eqwksp_id) {
            this.eqwksp_id = eqwksp_id;
        }

        public int getEqtype_id() {
            return eqtype_id;
        }

        public void setEqtype_id(int eqtype_id) {
            this.eqtype_id = eqtype_id;
        }

        public String getEqspeci() {
            return eqspeci;
        }

        public void setEqspeci(String eqspeci) {
            this.eqspeci = eqspeci;
        }

        public String getEqno() {
            return eqno;
        }

        public void setEqno(String eqno) {
            this.eqno = eqno;
        }

        public int getAdduser_id() {
            return adduser_id;
        }

        public void setAdduser_id(int adduser_id) {
            this.adduser_id = adduser_id;
        }

        public String getEqfactory() {
            return eqfactory;
        }

        public void setEqfactory(String eqfactory) {
            this.eqfactory = eqfactory;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getEqdepartment_id() {
            return eqdepartment_id;
        }

        public void setEqdepartment_id(int eqdepartment_id) {
            this.eqdepartment_id = eqdepartment_id;
        }

        public String getEqname() {
            return eqname;
        }

        public void setEqname(String eqname) {
            this.eqname = eqname;
        }

        public Object getExt1() {
            return ext1;
        }

        public void setExt1(Object ext1) {
            this.ext1 = ext1;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getUseless() {
            return useless;
        }

        public void setUseless(String useless) {
            this.useless = useless;
        }

        public Object getExt3() {
            return ext3;
        }

        public void setExt3(Object ext3) {
            this.ext3 = ext3;
        }

        public String getInstallocation() {
            return installocation;
        }

        public void setInstallocation(String installocation) {
            this.installocation = installocation;
        }

        public int getEqsystem_id() {
            return eqsystem_id;
        }

        public void setEqsystem_id(int eqsystem_id) {
            this.eqsystem_id = eqsystem_id;
        }
    }

    public static class OBean {
        /**
         * pmcontent : 检查电机轴承，并进行润滑，若发现轴承损坏， 则予以更换
         * status : -1
         * pmark : 备注
         * plandate : 计划制定中
         * pmid : 1
         */

        private String pmcontent;
        private int status;
        private String pmark;
        private String plandate;
        private int pmid;

        public String getPmcontent() {
            return pmcontent;
        }

        public void setPmcontent(String pmcontent) {
            this.pmcontent = pmcontent;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getPmark() {
            return pmark;
        }

        public void setPmark(String pmark) {
            this.pmark = pmark;
        }

        public String getPlandate() {
            return plandate;
        }

        public void setPlandate(String plandate) {
            this.plandate = plandate;
        }

        public int getPmid() {
            return pmid;
        }

        public void setPmid(int pmid) {
            this.pmid = pmid;
        }
    }

    public static class FilesBean {
        /**
         * fname : Book1
         * fileurl : /file/Book1.xlsx
         * filext : xlsx
         */

        private String fname;
        private String fileurl;
        private String filext;

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getFileurl() {
            return fileurl;
        }

        public void setFileurl(String fileurl) {
            this.fileurl = fileurl;
        }

        public String getFilext() {
            return filext;
        }

        public void setFilext(String filext) {
            this.filext = filext;
        }
    }
}
