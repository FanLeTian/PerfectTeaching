package com.fan.perfectteaching.beans;

public class RegisterBean extends StatusBean {

    /**
     * data : {"id":5,"num":222,"password":"222","phone":1121,"name":"2","sex":null,"birth":null,"cclass":null,"email":null,"status":null,"major":null}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5
         * num : 222
         * password : 222
         * phone : 1121
         * name : 2
         * sex : null
         * birth : null
         * cclass : null
         * email : null
         * status : null
         * major : null
         */

        private int id;
        private int num;
        private String password;
        private int phone;
        private String name;
        private Object sex;
        private Object birth;
        private Object cclass;
        private Object email;
        private Object status;
        private Object major;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getPhone() {
            return phone;
        }

        public void setPhone(int phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getBirth() {
            return birth;
        }

        public void setBirth(Object birth) {
            this.birth = birth;
        }

        public Object getCclass() {
            return cclass;
        }

        public void setCclass(Object cclass) {
            this.cclass = cclass;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getMajor() {
            return major;
        }

        public void setMajor(Object major) {
            this.major = major;
        }
    }
}
