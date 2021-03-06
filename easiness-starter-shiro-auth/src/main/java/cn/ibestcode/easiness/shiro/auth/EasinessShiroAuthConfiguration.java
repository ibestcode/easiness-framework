/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed to the IBESTCODE under one or more agreements.
 * The IBESTCODE licenses this file to you under the MIT license.
 * See the LICENSE file in the project root for more information.
 */
package cn.ibestcode.easiness.shiro.auth;

import cn.ibestcode.easiness.auth.EnableEasinessAuth;
import cn.ibestcode.easiness.auth.biz.EasinessAuthBiz;
import cn.ibestcode.easiness.shiro.auth.biz.EasinessShiroAuthBiz;
import cn.ibestcode.easiness.shiro.authentication.DefaultEasinessAuthentication;
import cn.ibestcode.easiness.shiro.filter.SpringShiroFilterRegistrationBean;
import cn.ibestcode.easiness.shiro.realm.EasinessAuthorizationRealm;
import cn.ibestcode.easiness.shiro.session.utils.DefaultEasinessSession;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@ComponentScan
@EnableEasinessAuth
public class EasinessShiroAuthConfiguration {

  @Bean(name = "shiroFilter")
  @ConditionalOnMissingBean(name = "shiroFilter")
  public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager defaultWebSecurityManager) {
    ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
    shiroFilter.setSecurityManager(defaultWebSecurityManager);
    return shiroFilter;
  }

  @Bean
  @ConditionalOnMissingBean(DefaultEasinessSession.class)
  public DefaultEasinessSession defaultEasinessSession() {
    return new DefaultEasinessSession();
  }

  @Bean
  @ConditionalOnMissingBean(Realm.class)
  public Realm realm(DefaultWebSecurityManager defaultWebSecurityManager) {
    EasinessAuthorizationRealm realm = new EasinessAuthorizationRealm();
    defaultWebSecurityManager.setRealm(realm);
    return realm;
  }


  @Bean
  @ConditionalOnMissingBean(SecurityManager.class)
  public DefaultWebSecurityManager defaultWebSecurityManager() {
    return new DefaultWebSecurityManager();
  }


  // 支持 Shiro 注解权限控制
  @Bean
  @DependsOn("lifecycleBeanPostProcessor")
  @ConditionalOnMissingBean(AuthorizationAttributeSourceAdvisor.class)
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
  }

  @Bean
  @ConditionalOnMissingBean(LifecycleBeanPostProcessor.class)
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  @Bean
  @ConditionalOnMissingBean(SpringShiroFilterRegistrationBean.class)
  public SpringShiroFilterRegistrationBean springShiroFilterRegistrationBean() {
    return new SpringShiroFilterRegistrationBean();
  }

  @Bean
  @ConditionalOnMissingBean(DefaultEasinessAuthentication.class)
  public DefaultEasinessAuthentication defaultEasinessAuthentication() {
    return new DefaultEasinessAuthentication();
  }

  @Bean
  @ConditionalOnMissingBean(EasinessAuthBiz.class)
  public EasinessAuthBiz easinessAuthBiz() {
    return new EasinessShiroAuthBiz();
  }

}
