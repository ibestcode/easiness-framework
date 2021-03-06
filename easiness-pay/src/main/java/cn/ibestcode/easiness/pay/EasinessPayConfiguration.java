/**
 * Copyright 2020 the original author or authors.
 * <p>
 * Licensed to the IBESTCODE under one or more agreements.
 * The IBESTCODE licenses this file to you under the MIT license.
 * See the LICENSE file in the project root for more information.
 */
package cn.ibestcode.easiness.pay;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author WFSO (仵士杰)
 * create by WFSO (仵士杰) at 2020/01/07 20:10
 */
@EntityScan
@ComponentScan
@Configuration
@EnableJpaRepositories
public class EasinessPayConfiguration {
}
