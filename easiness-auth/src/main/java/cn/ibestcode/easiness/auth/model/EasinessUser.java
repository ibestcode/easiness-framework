package cn.ibestcode.easiness.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户实体")
public interface EasinessUser {

  @ApiModelProperty("唯一标识")
  Long getUserId();

  @ApiModelProperty("登录用户名")
  String getLoginName();

  @JsonIgnore
  @ApiModelProperty("密码")
  String getPassword();

  @JsonIgnore
  @ApiModelProperty("盐")
  String getSalt();

  @ApiModelProperty("最后一次登录的SessionId")
  String getLastLoginSessionId();

  @ApiModelProperty("最后一次登录的Ip地址")
  String getLastLoginIp();

  @ApiModelProperty("是否可用")
  boolean isAvailable();

}
