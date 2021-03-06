/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed to the IBESTCODE under one or more agreements.
 * The IBESTCODE licenses this file to you under the MIT license.
 * See the LICENSE file in the project root for more information.
 */
package cn.ibestcode.easiness.pay.alipay.handler;

import cn.ibestcode.easiness.order.model.EasinessOrder;
import cn.ibestcode.easiness.pay.alipay.EasinessPayAlipayConstant;
import cn.ibestcode.easiness.pay.alipay.domain.AlipayPlaceOrderResult;
import cn.ibestcode.easiness.pay.alipay.properties.AlipayPhoneWebProperties;
import cn.ibestcode.easiness.pay.domain.EasinessPayPassbackParams;
import cn.ibestcode.easiness.pay.model.EasinessPay;
import cn.ibestcode.easiness.pay.utils.PriceUtils;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author WFSO (仵士杰)
 * create by WFSO (仵士杰) at 2020/1/12 20:29
 */
@Component
@Slf4j
public class AlipayPhoneWebPlaceOrderHandler extends AlipayPlaceOrderHandler {
  @Autowired
  private AlipayPhoneWebProperties properties;


  @Override
  public String supportType() {
    return EasinessPayAlipayConstant.EASINESS_PAY_TYPE_PHONE_WEB;
  }

  @Override
  protected AlipayTradeWapPayModel genBizModel(EasinessOrder order,
                                               EasinessPay pay,
                                               EasinessPayPassbackParams passbackParams,
                                               Map<String, String> params) {
    // 构造BizContent
    AlipayTradeWapPayModel bizModel = new AlipayTradeWapPayModel();
    bizModel.setTimeExpire(expireDateFormat.format(new Date(pay.getExpirationAt())));
    bizModel.setOutTradeNo(pay.getUuid());
    bizModel.setProductCode(properties.getProductCode());
    String price = PriceUtils.transformPrice(pay.getPrice());
    bizModel.setTotalAmount(price);
    bizModel.setSubject(order.getOrderName());
    bizModel.setBody(order.getOrderInfo());
    try {
      String passbackParamsJson = objectMapper.writeValueAsString(passbackParams);
      bizModel.setPassbackParams(passbackParamsJson);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return bizModel;
  }

  @Override
  protected boolean requireReturnUrl() {
    return true;
  }

  @Override
  protected AlipayPlaceOrderResult executeRequest(AlipayRequest request) throws AlipayApiException {
    AlipayResponse response = getAlipayClient(properties).pageExecute(request);
    AlipayPlaceOrderResult result = new AlipayPlaceOrderResult();
    result.setResponseBody(response.getBody());
    return result;
  }

  @Override
  protected AlipayTradeWapPayRequest newAlipayRequest() {
    return new AlipayTradeWapPayRequest();
  }

  @Override
  protected AlipayPhoneWebProperties getAlipayProperties() {
    return properties;
  }
}
