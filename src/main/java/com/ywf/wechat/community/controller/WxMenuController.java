package com.ywf.wechat.community.controller;

import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import static me.chanjar.weixin.common.api.WxConsts.MenuButtonType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/wx/menu/{appid}")
public class WxMenuController {
    private final WxMpService wxService;

    @GetMapping("/create")
    public String menuCreateSample(@PathVariable String appid) throws WxErrorException, MalformedURLException {
        WxMenu menu = new WxMenu();
        WxMenuButton button1 = new WxMenuButton();
        button1.setName("登记");
        button1.setKey("reg");
        WxMenuButton button11 = new WxMenuButton();
        button11.setName("访客");
        button11.setKey("visitor");
        button11.setType(MenuButtonType.VIEW);

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid +
                "&redirect_uri=http://yweifeng.free.idcfengye.com/checkin/visitor&response_type=code&scope=snsapi_base&state=ywf#wechat_redirect";
        button11.setUrl(url);
        WxMenuButton button12 = new WxMenuButton();
        button12.setName("工作人员");
        button12.setKey("worker");
        button12.setType(MenuButtonType.VIEW);
        button12.setUrl(url);
        WxMenuButton button13 = new WxMenuButton();
        button13.setName("居民");
        button13.setKey("resident");
        button13.setType(MenuButtonType.VIEW);
        button13.setUrl("http://www.soso.com?type=resident");
        button1.getSubButtons().add(button11);
        button1.getSubButtons().add(button12);
        button1.getSubButtons().add(button13);

        WxMenuButton button2 = new WxMenuButton();
        button2.setName("我的");
        button2.setKey("my");
        WxMenuButton button21 = new WxMenuButton();
        button21.setName("房屋管理");
        button21.setKey("house-manage");
        button21.setType(MenuButtonType.VIEW);
        button21.setUrl("http://www.soso.com?type=house-manage");
        WxMenuButton button22 = new WxMenuButton();
        button22.setName("人员管理");
        button22.setKey("user-manage");
        button22.setType(MenuButtonType.VIEW);
        button22.setUrl("http://www.soso.com?type=user-manage");
        WxMenuButton button23 = new WxMenuButton();
        button23.setName("个人信息");
        button23.setKey("userinfo");
        button23.setType(MenuButtonType.VIEW);
        button23.setUrl("http://www.soso.com?type=userinfo");
        WxMenuButton button24 = new WxMenuButton();
        button24.setName("拜访历史");
        button24.setKey("visitor-history");
        button24.setType(MenuButtonType.VIEW);
        button24.setUrl("http://www.soso.com?type=visitor-history");
        button2.getSubButtons().add(button21);
        button2.getSubButtons().add(button22);
        button2.getSubButtons().add(button23);
        button2.getSubButtons().add(button24);

        WxMenuButton button3 = new WxMenuButton();
        button3.setName("帮助");
        button3.setKey("help");
        button3.setType(MenuButtonType.VIEW);
        button3.setUrl("http://www.soso.com?type=help");

//        ServletRequestAttributes servletRequestAttributes =
//            (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (servletRequestAttributes != null) {
//            HttpServletRequest request = servletRequestAttributes.getRequest();
//            URL requestURL = new URL(request.getRequestURL().toString());
//            String url = this.wxService.switchoverTo(appid).oauth2buildAuthorizationUrl(
//                String.format("%s://%s/wx/redirect/%s/greet", requestURL.getProtocol(), requestURL.getHost(), appid),
//                WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
//            button34.setUrl(url);
//        }
        this.wxService.switchover(appid);
        menu.setButtons(Arrays.asList(button1, button2, button3));
        return this.wxService.getMenuService().menuCreate(menu);
    }

}
