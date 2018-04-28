package com.fan.perfectteaching.beans;

public class SelectBean extends StatusBean {


    /**
     * data : {"id":1,"num":1306,"password":"123456","phone":123456,"name":"小美","sex":"女","birth":"2018-04-04 10:54:55.0","cclass":"1","email":"stu@qq.com","status":"1","major":{"id":1,"num":100106,"name":"软件工程","tel":"123456789","assistant":"小高","dept":{"id":1,"num":1001,"name":"计算机科学与工程","chairman":"小高","tel":"123456789","desc":"计算机"}}}
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
         * id : 1
         * num : 1306
         * password : 123456
         * phone : 123456
         * name : 小美
         * sex : 女
         * birth : 2018-04-04 10:54:55.0
         * cclass : 1
         * email : stu@qq.com
         * status : 1
         * major : {"id":1,"num":100106,"name":"软件工程","tel":"123456789","assistant":"小高","dept":{"id":1,"num":1001,"name":"计算机科学与工程","chairman":"小高","tel":"123456789","desc":"计算机"}}
         */

        private int id;
        private int num;
        private String password;
        private int phone;
        private String name;
        private String sex;
        private String birth;
        private String cclass;
        private String email;
        private String status;
        private MajorBean major;

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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getCclass() {
            return cclass;
        }

        public void setCclass(String cclass) {
            this.cclass = cclass;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public MajorBean getMajor() {
            return major;
        }

        public void setMajor(MajorBean major) {
            this.major = major;
        }

        public static class MajorBean {
            /**
             * id : 1
             * num : 100106
             * name : 软件工程
             * tel : 123456789
             * assistant : 小高
             * dept : {"id":1,"num":1001,"name":"计算机科学与工程","chairman":"小高","tel":"123456789","desc":"计算机"}
             */

            private int id;
            private int num;
            private String name;
            private String tel;
            private String assistant;
            private DeptBean dept;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getAssistant() {
                return assistant;
            }

            public void setAssistant(String assistant) {
                this.assistant = assistant;
            }

            public DeptBean getDept() {
                return dept;
            }

            public void setDept(DeptBean dept) {
                this.dept = dept;
            }

            public static class DeptBean {
                /**
                 * id : 1
                 * num : 1001
                 * name : 计算机科学与工程
                 * chairman : 小高
                 * tel : 123456789
                 * desc : 计算机
                 */

                private int id;
                private int num;
                private String name;
                private String chairman;
                private String tel;
                private String desc;

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

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getChairman() {
                    return chairman;
                }

                public void setChairman(String chairman) {
                    this.chairman = chairman;
                }

                public String getTel() {
                    return tel;
                }

                public void setTel(String tel) {
                    this.tel = tel;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }
            }
        }
    }
}
