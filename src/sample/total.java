package sample;

/**
 * Created By XuanRan on 2021/4/28
 */
public class total {

    String total_err_cause;
    int total_err_num;

    public total(String total_err_cause, int total_err_num) {
        this.total_err_cause = total_err_cause;
        this.total_err_num = total_err_num;
    }

    public String getTotal_err_cause() {
        return total_err_cause;
    }

    public void setTotal_err_cause(String total_err_cause) {
        this.total_err_cause = total_err_cause;
    }

    public int getTotal_err_num() {
        return total_err_num;
    }

    public void setTotal_err_num(int total_err_num) {
        this.total_err_num = total_err_num;
    }
}
