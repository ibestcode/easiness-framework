/**
 * Copyright 2020 the original author or authors.
 * <p>
 * Licensed to the IBESTCODE under one or more agreements.
 * The IBESTCODE licenses this file to you under the MIT license.
 * See the LICENSE file in the project root for more information.
 */
package cn.ibestcode.easiness.order.domain;

import cn.ibestcode.easiness.order.helper.EasinessOrderExtendHelper;
import cn.ibestcode.easiness.order.helper.EasinessOrderItemExtendHelper;
import cn.ibestcode.easiness.order.model.EasinessOrderExtend;
import cn.ibestcode.easiness.order.model.EasinessOrderItem;
import cn.ibestcode.easiness.order.model.EasinessOrderItemExtend;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author WFSO (仵士杰)
 * create by WFSO (仵士杰) at 2020/01/06 20:13
 */
@Setter
@Getter
@ToString
@ApiModel("添加订单项的VO")
public class EasinessOrderItemCreateVo implements Serializable {
  public EasinessOrderItemCreateVo() {

  }

  public EasinessOrderItemCreateVo(EasinessOrderItem orderItem) {
    this.orderItem = orderItem;
  }

  public EasinessOrderItemCreateVo(EasinessOrderItem orderItem, Map<String, String> map) {
    this(orderItem);
    orderItemExtends = EasinessOrderItemExtendHelper.getInstanceList(map);
  }

  @ApiModelProperty("订单项对象")
  @NotNull
  private EasinessOrderItem orderItem;

  @ApiModelProperty("订单项扩展列表")
  private List<EasinessOrderItemExtend> orderItemExtends = new ArrayList<>();
}
