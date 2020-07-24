package is.fb.oldbooks;

class SellBook {
     private String b_name,b_price,cate,mobile,name,key;
 public SellBook(){

 }
    public SellBook( String b_name,String b_price,String catagore ,String mobile,String name){
       this.b_name=b_name;
       this.b_price=b_price;
       this.cate=catagore;
       this.mobile=mobile;
       this.name=name;
    }

    public String getName() {
        return name;
    }

    public String getB_name() {
        return b_name;
    }

    public String getB_price() {
        return b_price;
    }

    public String getCate() {
        return cate;
    }

    public String getMobile() {
        return mobile;
    }

    public String getKey() {
        return key;
    }
}
