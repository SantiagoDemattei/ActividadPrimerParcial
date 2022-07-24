package Api;

public class AeropuertoResponse {
    String pagination;
    String data;

    public void setPagination(String p){this.pagination = p;}
    public void setData(String d){this.data = d;}

    public String  getPagination(){return this.pagination;}
    public String  getData(){return this.data;}

}

