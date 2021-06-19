package plj.licona.club.acl.response;

import lombok.Data;

/**
 * 通用返回对象
 *
 * @author licona
 */
@Data
public class ResultResponse {
    /**
     * 状态码
     */
    private long code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;

    public ResultResponse() {
    }

    public ResultResponse(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultResponse(long code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 自定义异常
     *
     * @param be 异常
     * @return ResultResponse
     */
    public static ResultResponse customException(BusinessException be) {
        ResultResponse result = new ResultResponse();
        result.setCode(be.getErrorCode());
        result.setMsg(be.getErrorMsg());
        result.setData(null);
        return result;
    }

    /**
     * 其他异常
     *
     * @param resultEnum 结果枚举
     * @return ResultResponse
     */
    public static ResultResponse otherException(ResultEnum resultEnum) {
        ResultResponse result = new ResultResponse();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMessage());
        result.setData(null);
        return result;
    }
}
