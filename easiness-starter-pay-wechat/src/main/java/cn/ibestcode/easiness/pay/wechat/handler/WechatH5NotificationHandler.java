/**
 * Copyright 2020 the original author or authors.
 * <p>
 * Licensed to the IBESTCODE under one or more agreements.
 * The IBESTCODE licenses this file to you under the MIT license.
 * See the LICENSE file in the project root for more information.
 */
package cn.ibestcode.easiness.pay.wechat.handler;

import cn.ibestcode.easiness.pay.wechat.EasinessPayWechatConstant;
import cn.ibestcode.easiness.pay.wechat.properties.WechatH5Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wfso (仵士杰)
 * create by WFSO (仵士杰) at 2020/3/4 18:52
 */
@Component
@Slf4j
public class WechatH5NotificationHandler extends WechatNotificationHandler {
  @Autowired
  private WechatH5Properties properties;

  public WechatH5Properties getProperties() {
    return properties;
  }

  @Override
  public String supportType() {
    return EasinessPayWechatConstant.EASINESS_PAY_TYPE_H5;
  }
}
