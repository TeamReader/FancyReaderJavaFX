package zz.reader.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zz on 2016-07-06.
 */
public class MyException extends RuntimeException{

    private String errorCode;

    private Map<String,Object> parameters = new HashMap<>();

    public MyException(){
        super();
    }

    public MyException(Throwable cause){super(cause);}

    public String getErrorCode(){return errorCode;}

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, Object> getParameter() {
        return parameters;
    }

    public void addParameter(String name, Object value) {
        parameters.put(name,value);
    }

    @Override
    public String getMessage(){
        StringBuilder sb = new StringBuilder();
        sb.append("errorCode: ").append(errorCode).append(", ");
        sb.append("parameters: ").append(parameters);
        return sb.toString();
    }

}
