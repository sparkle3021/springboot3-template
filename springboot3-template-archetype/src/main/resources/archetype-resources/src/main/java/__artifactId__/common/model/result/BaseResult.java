package ${package}.${artifactId}.common.model.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 基础返回实体
 *
 * @author alex meng
 * @createDate 2020-11-13 13:17
 */
@Data
@Schema(description = "基础返回实体")
public class BaseResult implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "请求状态码")
    private String code;

    @Schema(description = "业务信息")
    private String message;

    @Schema(description = "请求结果")
    private Boolean success;

    public BaseResult() {
    }

    public BaseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(String code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }
}
