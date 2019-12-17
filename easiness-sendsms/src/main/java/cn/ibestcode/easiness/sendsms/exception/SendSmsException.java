/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed to the IBESTCODE under one or more agreements.
 * The IBESTCODE licenses this file to you under the MIT license.
 * See the LICENSE file in the project root for more information.
 */

package cn.ibestcode.easiness.sendsms.exception;

/**
 * @author WFSO (仵士杰)
 * create by WFSO (仵士杰) at 2019/11/23
 */
public class SendSmsException extends RuntimeException {

  private String[] params;

  public SendSmsException(String message) {
    super(message);
    this.params = new String[]{};
  }

  public SendSmsException(String message, String... params) {
    super(message);
    this.params = params;
  }

  public SendSmsException(String message, Throwable cause) {
    super(message, cause);
    this.params = new String[]{};
  }

  public SendSmsException(String message, Throwable cause, String... params) {
    super(message, cause);
    this.params = params;
  }

  public String[] getParams() {
    return params;
  }
}
