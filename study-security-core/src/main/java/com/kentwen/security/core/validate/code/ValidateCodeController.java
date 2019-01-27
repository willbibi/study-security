package com.kentwen.security.core.validate.code;

import com.kentwen.security.core.validate.ValidateCodeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 毕文 on 2019/1/11.
 */
@RestController
@Slf4j
public class ValidateCodeController {

    //    @Autowired
//    private Map<String, ValidateCodeProcessor> validateCodeProcessors;
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 创建验证码，根据验证码类型不同，调用不同的{@link ValidateCodeProcessor} 接口实现
     *
     * @param request
     * @param response
     * @param type
     * @throws Exception
     */
    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
//        validateCodeProcessors.get(type + "CodeProcessor").create(new ServletWebRequest(request, response));
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }

//    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
//
//    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
//
//    @Autowired
//    ValidateCodeGenerator imageCodeGenerator;
//
//    @Autowired
//    ValidateCodeGenerator smsCodeGenerator;
//
//    @Autowired
//    SmsCodeSender smsCodeSender;
//
//    @GetMapping("/code/image")
//    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        ServletWebRequest webRequest = new ServletWebRequest(request, response);
//        // 1.根据随机数生成图片
//        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(webRequest);
//        // 2.将随机数存到session中
//        sessionStrategy.setAttribute(webRequest, SESSION_KEY, imageCode);
//        // 3.将生成的图片写到接口的响应中
//        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
//    }
//
//    @GetMapping("/code/sms")
//    public void createCode1(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
//
//        ServletWebRequest webRequest = new ServletWebRequest(request, response);
//        // 1.根据随机数生成图片
//        ValidateCode smsCode = smsCodeGenerator.generate(webRequest);
//        // 2.将随机数存到session中
//        sessionStrategy.setAttribute(webRequest, SESSION_KEY, smsCode);
//        // 3.将手机验证码发送给手机
//        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
//        smsCodeSender.send(mobile, smsCode.getCode());
//
//    }


}
