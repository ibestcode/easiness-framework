/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed to the IBESTCODE under one or more agreements.
 * The IBESTCODE licenses this file to you under the MIT license.
 * See the LICENSE file in the project root for more information.
 */

package cn.ibestcode.easiness.sendsms.provider;

import cn.ibestcode.easiness.sendsms.sender.SmsSender;

/**
 * @author WFSO (仵士杰)
 * create by WFSO (仵士杰) at 2019/11/20 19:13
 */
public interface SmsProvider {

  SmsSender getSender();

  void clear();

  String getType();
}
