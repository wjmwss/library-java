package cn.srd.itcp.sugar.convert.mapstruct.exception;

/**
 * 未找到转换方法时的异常
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
public class MapstructConvertMethodNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6760724713927618055L;

    public MapstructConvertMethodNotFoundException() {
    }

    public MapstructConvertMethodNotFoundException(String message) {
        super(message);
    }

    public MapstructConvertMethodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapstructConvertMethodNotFoundException(Throwable cause) {
        super(cause);
    }

}
