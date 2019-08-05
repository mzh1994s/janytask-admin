package cn.mzhong.janytask.admin.frame.controller;

import cn.mzhong.janytask.admin.frame.core.ProviderManager;
import cn.mzhong.janytask.admin.frame.vo.ProviderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FrameController {

    @Autowired
    ProviderManager providerManager;

    @RequestMapping(value = "/providers")
    public List<ProviderVO> getProviders(){
        return providerManager.getProviders();
    }
}
