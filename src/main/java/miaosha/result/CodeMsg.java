package miaosha.result;

public class CodeMsg {
    private int code;
    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(0,"success");
    public static CodeMsg SEVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101,"参数校验异常:%s");

    //login module
    public static CodeMsg SESSION_ERROR = new CodeMsg(500210,"Session不存在或者已经失效");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "手机好不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "手机号码格式错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "手机号码不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误");


    private CodeMsg(int code, String msg){
        this.code = code;
        this.msg = msg;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args){
        int code = this.code;
        String message = String.format(this.msg,args);
        return new CodeMsg(code,message);
    }
}
