package wsy.userLogin.controller.result;

public class ResultAPI<T> {
	public static final int OK = 200;
	public static final int ERROR = 400;
	private int code;
	private String mes;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	private T t;
	public ResultAPI(int code,T t){
		this.t = t;
		this.code = code;
	}
	public ResultAPI(int code,String mes){
		this.code = code;
		this.mes = mes;
	}
	public static <T> ResultAPI<?> error(Integer code, String msg) {
	    return new ResultAPI<T>(code, msg);
	}
	public static <T> ResultAPI<?> ok(T t) {
	    return new ResultAPI<T>(OK, t);
	}
	public static <T> ResultAPI<?> error(String msg) {
	    return new ResultAPI<T>(ERROR, msg);
	}
	
}
