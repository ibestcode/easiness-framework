/**
 * Copyright 2020 the original author or authors.
 * <p>
 * Licensed to the IBESTCODE under one or more agreements.
 * The IBESTCODE licenses this file to you under the MIT license.
 * See the LICENSE file in the project root for more information.
 */
package cn.ibestcode.easiness.pay.wechat.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wfso (仵士杰)
 * create by WFSO (仵士杰) at 2020/2/28 18:52
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "easiness.pay.webchat.app")
public class WechatAppProperties extends WechatProperties {
  /*
   * @ApiModelProperty("开放平台应用中的AppId") private String appId;
   */

  public WechatAppProperties() {
    setTradeType("APP");
  }
}
