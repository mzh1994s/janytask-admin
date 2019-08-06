package cn.mzhong.janytask.admin.center;

import cn.mzhong.janytask.admin.base.RestInfo;
import cn.mzhong.janytask.admin.base.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "center")
@RestController
public class CenterController {

    @Autowired
    DatabaseInitializer initializer;

    @RequestMapping(value = "isInited")
    public RestInfo isInited() {
        return RestInfo.success(initializer.isInited());
    }

    @RequestMapping(value = "awaitInit", method = RequestMethod.POST)
    public RestInfo awaitInit(@RequestBody @Validated User user) {
        if (!initializer.isInited()) {
            initializer.awaitInit(user);
        }
        return RestInfo.success();
    }
}
