package com.kentwen.security.core.validate.code;

import com.kentwen.security.core.properties.SecurityConstants;
import com.kentwen.security.core.properties.SecurityProperties;
import com.kentwen.security.core.validate.ValidateCodeProcessor;
import com.kentwen.security.core.validate.code.image.ImageCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by 毕文 on 2019/1/16.
 */

@Getter
@Setter
@Slf4j
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 验证码校验失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 系统配置
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 系统中的校验码处理器
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 初始化要拦截的url配置信息
     *
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);

    }

    /**
     * 将系统中配置的需要校验验证码的URL根据校验的类型放入map
     *
     * @param urlString
     * @param codeType
     */
    private void addUrlToMap(String urlString, ValidateCodeType codeType) {
        if (StringUtils.isNotBlank(urlString)) {
            String[] configUrls = StringUtils.split(urlString, ",");
            for (String configUrl : configUrls) {
                urlMap.put(configUrl, codeType);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        ValidateCodeType type = getValidateCodeType(httpServletRequest);
        if (type != null) {
            log.info("校验请求【{}】中的验证码，验证码类型 ：{}"
                    , httpServletRequest.getRequestURL(), type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type).validate(new ServletWebRequest(httpServletRequest, httpServletResponse));

            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param httpServletRequest
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest httpServletRequest) {
        ValidateCodeType result = null;
        if (!StringUtils.equalsAnyIgnoreCase(httpServletRequest.getMethod(), "get")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (pathMatcher.match(url, httpServletRequest.getRequestURI())) {
                    result = urlMap.get(url);
                    break;
                }
            }
        }
        return result;
    }



//    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
//        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");
//
//        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
//
//        if (StringUtils.isBlank(codeInRequest)) {
//            throw new ValidateCodeException("验证码的值不能为空");
//        }
//
//        if (codeInSession == null) {
//            throw new ValidateCodeException("验证码不存在");
//        }
//
//        if (codeInSession.isExpired()) {
//            sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");
//            throw new ValidateCodeException("验证码已过期");
//        }
//        if (!StringUtils.equalsAnyIgnoreCase(codeInSession.getCode(), codeInRequest)) {
//            throw new ValidateCodeException("验证码不匹配");
//        }
//
//        sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");
//    }


}
