package com.security.demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.security.demo.security.jwt.JwtTokenUtil;
import com.security.demo.security.jwt.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @author Administrator
 * @desc
 * @date 2019-06-25 19:54
 */
@Slf4j
public class PasswordCoder {
    private final String secret = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKo++i9J9dzAFtbxwowKDCo2mxi7MXxE8A8VvssaydWjjgmEz/HHMPLOhi1182a1si4pWL0/MizKnquD7T2Bu4jpQbAFnkNYEMEyq/kw904Xl0JCQHYFuvnI99RE8Q3KlTP6kEUGDjV34EL6vBGJcQvArLtj1xoP8y0nIfJ2Pw5TAgMBAAECgYAGGB8IllMwxceLhjf6n1l0IWRH7FuHIUieoZ6k0p6rASHSgWiYNRMxfecbtX8zDAoG0QAWNi7rn40ygpR5gS1fWDAKhmnhKgQIT6wW0VmD4hraaeyP78iy8BLhlvblri2nCPIhDH5+l96v7D47ZZi3ZSOzcj89s1eS/k7/N4peEQJBAPEtGGJY+lBoCxQMhGyzuzDmgcS1Un1ZE2pt+XNCVl2b+T8fxWJH3tRRR8wOY5uvtPiK1HM/IjT0T5qwQeH8Yk0CQQC0tcv3d/bDb7bOe9QzUFDQkUSpTdPWAgMX2OVPxjdq3Sls9oA5+fGNYEy0OgyqTjde0b4iRzlD1O0OhLqPSUMfAkEAh5FIvqezdRU2/PsYSR4yoAdCdLdT+h/jGRVefhqQ/6eYUJJkWp15tTFHQX3pIe9/s6IeT/XyHYAjaxmevxAmlQJBAKSdhvQjf9KAjZKDEsa7vyJ/coCXuQUWSCMNHbcR5aGfXgE4e45UtUoIE1eKGcd6AM6LWhx3rR6xdFDpb9je8BkCQB0SpevGfOQkMk5i8xkEt9eeYP0fi8nv6eOUcK96EXbzs4jV2SAoQJ9oJegPtPROHbhIvVUmNQTbuP10Yjg59+8=";

    @Test
    public void encoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(14);
        log.info(passwordEncoder.encode("123456"));
    }

    @Test
    public void jjwt() {
        JwtTokenUtil util = new JwtTokenUtil();
        JwtUser user = new JwtUser();
        user.setId(1L).setName("Relic").setUsername("Relic");
        String token = util.generateToken(user);
        log.info("token:{}", token);
        //eyJhbGciOiJIUzUxMiJ9.eyJjcmVhdGVkIjoxNTYxNTE2OTI5NzcwLCJuYW1lIjoiUmVsaWMiLCJpZCI6IjEiLCJleHAiOjE1NjIxMjE3Mjl9.AAmDoD95pfAu8rjnEjzeWhDo2i3kMm6LpQVxbZkBDWOY53BZYKVdCNTKACEokBb6RQKwrJ-KU52sEzzC0ytbPw
    }

    @Test
    public void jwt() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJjcmVhdGVkIjoxNTYxNTE2OTI5NzcwLCJuYW1lIjoiUmVsaWMiLCJpZCI6IjEiLCJleHAiOjE1NjIxMjE3Mjl9.AAmDoD95pfAu8rjnEjzeWhDo2i3kMm6LpQVxbZkBDWOY53BZYKVdCNTKACEokBb6RQKwrJ-KU52sEzzC0ytbPw";
        DecodedJWT decode = JWT.decode(token);
        Map<String, Claim> claims = decode.getClaims();
        log.info("claims:{}\n", claims);
        Collection<Claim> values = claims.values();
        for (Claim value : values) {
            String s = value.asString();
            log.info("claim:{}\n", s);
        }
    }

    @Test
    public void generate() {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        String sign = JWT.create().withClaim("name", "Relic").withClaim("id", 1L)
                .withExpiresAt(new Date(System.currentTimeMillis() + 604800 * 1000)).withClaim("created", new Date()).sign(algorithm);
        log.info("token:{}\n", sign);
        //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkIjoxNTYxNTE3NzY2LCJuYW1lIjoiUmVsaWMiLCJpZCI6MSwiZXhwIjoxNTYyMTIyNTY2fQ.LC9i1RAvXMyIT4oy32xgrK2oSnZRoWIZ_nT6uPvFV8Y
    }

    @Test
    public void parse() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkIjoxNTYxNTE3NzY2LCJuYW1lIjoiUmVsaWMiLCJpZCI6MSwiZXhwIjoxNTYyMTIyNTY2fQ.LC9i1RAvXMyIT4oy32xgrK2oSnZRoWIZ_nT6uPvFV8Y";
        JwtTokenUtil util = new JwtTokenUtil();
    }

    @Test
    public void log() {
        log.info("org.springframework.http.converter.HttpMessageNotReadableException: Required request body is missing: public com.security.demo.common.Result com.security.demo.security.controller.HomeController.createAuthenticationToken(com.security.demo.model.dto.AdminDTO) throws org.springframework.security.core.AuthenticationException\r\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.readWithMessageConverters(RequestResponseBodyMethodProcessor.java:160)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.resolveArgument(RequestResponseBodyMethodProcessor.java:130)\r\n\tat org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:126)\r\n\tat org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:167)\r\n\tat org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:134)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:104)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:892)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:797)\r\n\tat org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n\tat org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1039)\r\n\tat org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:942)\r\n\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1005)\r\n\tat org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:908)\r\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:660)\r\n\tat org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:882)\r\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n\tat org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:103)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n\tat com.security.demo.security.jwt.JwtAuthenticationTokenFilter.doFilterInternal(JwtAuthenticationTokenFilter.java:75)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:109)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:320)\r\n\tat org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:127)\r\n\tat org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:91)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n\tat org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n\tat org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:137)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n\tat org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:111)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n\tat org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:170)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n\tat org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n\tat com.security.demo.security.jwt.JwtAuthenticationTokenFilter.doFilterInternal(JwtAuthenticationTokenFilter.java:75)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:109)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n\tat org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:116)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n\tat org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:74)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:109)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n\tat org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:105)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n\tat org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:56)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:109)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\r\n\tat org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:215)\r\n\tat org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:178)\r\n\tat org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:357)\r\n\tat org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:270)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:109)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n\tat org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:92)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:109)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n\tat org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:93)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:109)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:109)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\r\n\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)\r\n\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n\tat org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n\tat org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n\tat org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:853)\r\n\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1587)\r\n\tat org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\r\n\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\r\n\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n\tat java.lang.Thread.run(Thread.java:748)\r\n");
    }
}
