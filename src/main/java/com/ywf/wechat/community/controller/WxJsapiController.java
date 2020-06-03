package com.ywf.wechat.community.controller;

import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * jsapi 演示接口的 controller.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-04-25
 */
@AllArgsConstructor
@RestController
@RequestMapping({"/api/wx/jsapi/", "/wx/jsapi/"})
public class WxJsapiController {
    private final WxMpService wxService;

    @GetMapping("/ticket")
    public String getJsapiTicket(@RequestParam String url) throws WxErrorException {
        final WxJsapiSignature jsapiSignature = this.wxService
                .switchoverTo(wxService.getWxMpConfigStorage().getAppId()).createJsapiSignature(url);
        System.out.println(jsapiSignature);
        return this.wxService.getJsapiTicket(true);
    }

    @GetMapping("/config")
    public WxJsapiSignature getConfig(HttpServletRequest request) throws WxErrorException {
        // 解决url=xxx&state=xxx 等情况
        String queryString = request.getQueryString().replace("url=","");
        final WxJsapiSignature jsapiSignature = this.wxService.createJsapiSignature(queryString);
        System.out.println(jsapiSignature);
        return jsapiSignature;
    }
}
