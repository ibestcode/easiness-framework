/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed to the IBESTCODE under one or more agreements.
 * The IBESTCODE licenses this file to you under the MIT license.
 * See the LICENSE file in the project root for more information.
 */

package cn.ibestcode.easiness.shiro.session.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * @author WFSO (仵士杰)
 * create by WFSO (仵士杰) at 2019/11/23
 */
public class DefaultEasinessSession extends AbstractEasinessSession {
  @Override
  protected Session getSession() {
    return SecurityUtils.getSubject().getSession();
  }
}
