package com.ywf.wechat.community.controller;

import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping({"/api/wx/user", "/wx/user"})
public class WxUserController {
    private final WxMpService wxService;

    @GetMapping("/{code}")
    public WxMpUser getUserInfo(@PathVariable String code) throws WxErrorException {
        WxMpOAuth2AccessToken accessToken = this.wxService.oauth2getAccessToken(code);
        if (null != accessToken) {
            // 获取用户基本信息
            WxMpUser wxMpUser = this.wxService.getUserService().userInfo(accessToken.getOpenId());
            return wxMpUser;
        }
        return new WxMpUser();
    }
}
