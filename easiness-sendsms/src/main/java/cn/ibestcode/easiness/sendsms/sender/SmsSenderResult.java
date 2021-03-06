/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed to the IBESTCODE under one or more agreements.
 * The IBESTCODE licenses this file to you under the MIT license.
 * See the LICENSE file in the project root for more information.
 */

package cn.ibestcode.easiness.sendsms.sender;

/**
 * @author WFSO (仵士杰)
 * create by WFSO (仵士杰) at 2019/11/20 19:13
 */
public interface SmsSenderResult {
  /**
   * 获取短信平台的唯一标识
   *
   * @return 短信平台标识
   */
  String getId();

  /**
   * 是否发送成功
   *
   * @return 发送结果
   */
  boolean isSuccess();


  /**
   * 获取短信发送者的类型
   *
   * @return 短信发送者类型
   */
  String getSenderType();

  /**
   * 把当前对象转换成JSON字符串
   *
   * @return JSON字符串
   */
  String toJSON();
}
