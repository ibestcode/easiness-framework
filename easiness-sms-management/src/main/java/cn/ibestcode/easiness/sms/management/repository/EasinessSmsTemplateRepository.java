/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed to the IBESTCODE under one or more agreements.
 * The IBESTCODE licenses this file to you under the MIT license.
 * See the LICENSE file in the project root for more information.
 */

package cn.ibestcode.easiness.sms.management.repository;

import cn.ibestcode.easiness.core.base.repository.UuidBaseJpaRepository;
import cn.ibestcode.easiness.sms.management.model.EasinessSmsTemplate;

/**
 * @author WFSO (仵士杰)
 * create by WFSO (仵士杰) at 2019/12/15 23:31
 */
public interface EasinessSmsTemplateRepository extends UuidBaseJpaRepository<EasinessSmsTemplate> {
  EasinessSmsTemplate findByTemplate(String template);
}
