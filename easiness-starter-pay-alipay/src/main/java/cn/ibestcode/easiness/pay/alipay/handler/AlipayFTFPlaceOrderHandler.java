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
import cn.ibestcode.easiness.pay.alipay.properties.AlipayFTFProperties;
import cn.ibestcode.easiness.pay.domain.EasinessPayPassbackParams;
import cn.ibestcode.easiness.pay.model.EasinessPay;
import cn.ibestcode.easiness.pay.utils.PriceUtils;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayRequest;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author WFSO (仵士杰)
 * create by WFSO (仵士杰) at 2020/1/12 20:29
 */
@Component
@Slf4j
public class AlipayFTFPlaceOrderHandler extends AlipayPlaceOrderHandler {
  @Autowired
  private AlipayFTFProperties properties;


  @Override
  public String supportType() {
    return EasinessPayAlipayConstant.EASINESS_PAY_TYPE_FTF;
  }

  @Override
  protected AlipayTradePrecreateModel genBizModel(EasinessOrder order,
                                                  EasinessPay pay,
                                                  EasinessPayPassbackParams passbackParams,
                                                  Map<String, String> params) {
    // 构造BizContent
    AlipayTradePrecreateModel bizModel = new AlipayTradePrecreateModel();
    String timeoutExpress = ((pay.getExpirationAt() - pay.getCreatedAt()) / 60000) + "m";
    bizModel.setTimeoutExpress(timeoutExpress);
    bizModel.setOutTradeNo(pay.getUuid());
    bizModel.setProductCode(properties.getProductCode());
    String price = PriceUtils.transformPrice(pay.getPrice());
    bizModel.setTotalAmount(price);
    bizModel.setSubject(order.getOrderName());
    bizModel.setBody(order.getOrderInfo());
    if (params.containsKey("operatorId")) {
      bizModel.setOperatorId(params.get("operatorId"));
    }
    if (params.containsKey("storeId")) {
      bizModel.setStoreId(params.get("storeId"));
    }
    if (params.containsKey("terminalId")) {
      bizModel.setTerminalId(params.get("terminalId"));
    }
    return bizModel;
  }

  @Override
  protected boolean requireReturnUrl() {
    return false;
  }

  @Override
  protected AlipayPlaceOrderResult executeRequest(AlipayRequest request) throws AlipayApiException {
    AlipayTradePrecreateResponse response = (AlipayTradePrecreateResponse) getAlipayClient(properties).execute(request);
    AlipayPlaceOrderResult result = new AlipayPlaceOrderResult();
    result.setResponseBody(response.getQrCode());
    result.setSucceed(response.isSuccess());
    return result;
  }

  @Override
  protected AlipayTradePrecreateRequest newAlipayRequest() {
    return new AlipayTradePrecreateRequest();
  }

  @Override
  protected AlipayFTFProperties getAlipayProperties() {
    return properties;
  }
}
