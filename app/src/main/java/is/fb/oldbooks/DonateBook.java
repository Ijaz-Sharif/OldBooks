package is.fb.oldbooks;

class DonateBook {
   private String b_name,name,cate,mobile,key;
    public DonateBook(){

    }
    public DonateBook( String b_name,String catagore ,String mobile,String name){
        this.b_name=b_name;
        this.cate=catagore;
        this.mobile=mobile;
        this.name=name;
    }

    public String getB_name() {
        return b_name;
    }
    public String getCate() {
        return cate;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }
}
