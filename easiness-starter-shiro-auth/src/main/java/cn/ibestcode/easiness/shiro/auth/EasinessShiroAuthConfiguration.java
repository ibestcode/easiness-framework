package cn.ibestcode.easiness.shiro.auth;

import cn.ibestcode.easiness.shiro.authentication.DefaultEasinessAuthentication;
import cn.ibestcode.easiness.shiro.filter.SpringShiroFilterRegistrationBean;
import cn.ibestcode.easiness.shiro.realm.EasinessAuthorizationRealm;
import cn.ibestcode.easiness.shiro.session.cache.RedissonSessionCache;
import cn.ibestcode.easiness.shiro.session.dao.RedissonSessionDAO;
import cn.ibestcode.easiness.shiro.session.manager.EasinessSessionManager;
import cn.ibestcode.easiness.shiro.session.properties.EasinessSessionProperties;
import cn.ibestcode.easiness.shiro.session.utils.DefaultEasinessSession;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.Serializable;

@Configuration
@ComponentScan
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
  public DefaultEasinessSession sessionUtil() {
    return new DefaultEasinessSession();
  }

  @Bean
  @ConditionalOnMissingBean(Realm.class)
  public EasinessAuthorizationRealm defaultAuthorizationRealm(DefaultWebSecurityManager defaultWebSecurityManager) {
    EasinessAuthorizationRealm realm = new EasinessAuthorizationRealm();
    defaultWebSecurityManager.setRealm(realm);
    return realm;
  }

  @Bean
  @ConditionalOnMissingBean(EasinessSessionProperties.class)
  public EasinessSessionProperties shiroSessionProperties() {
    return new EasinessSessionProperties();
  }

  @Bean
  @ConditionalOnMissingBean(EasinessSessionManager.class)
  public EasinessSessionManager easinessSessionManager(EasinessSessionProperties sessionProperties, RedissonClient redissonClient, DefaultWebSecurityManager defaultWebSecurityManager) {

    EasinessSessionManager sessionManager = new EasinessSessionManager();
    sessionManager.setTokenIdentification(sessionProperties.getTokenIdentification());
    sessionManager.setGlobalSessionTimeout(sessionProperties.getTimeout());
    sessionManager.setDeleteInvalidSessions(true);
    sessionManager.setSessionValidationSchedulerEnabled(true);
    sessionManager.setSessionValidationInterval(sessionProperties.getSessionValidationInterval());

    // 把 Session 存储到 redis 数据库中
    RMapCache<Serializable, Session> rMap = redissonClient.getMapCache(sessionProperties.getRedissonStoreName());
    RMapCache<Serializable, Session> rMapCache = redissonClient.getMapCache(sessionProperties.getRedissonStoreName() + "-cache");
    EnterpriseCacheSessionDAO sessionDAO = new RedissonSessionDAO(rMap);
    sessionManager.setSessionDAO(sessionDAO);
    sessionManager.setCacheManager(new AbstractCacheManager() {
      @Override
      protected Cache createCache(String s) throws CacheException {
        return new RedissonSessionCache(rMapCache);
      }
    });

    defaultWebSecurityManager.setSessionManager(sessionManager);
    return sessionManager;
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
  public AuthorizationAttributeSourceAdvisor advisor(SecurityManager securityManager) {
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

}
