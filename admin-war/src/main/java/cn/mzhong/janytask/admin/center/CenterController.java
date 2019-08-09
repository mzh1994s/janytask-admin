package cn.mzhong.janytask.admin.center;

import cn.mzhong.janytask.admin.base.ResponseException;
import cn.mzhong.janytask.admin.base.ResponseInfo;
import cn.mzhong.janytask.admin.base.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(value = "center")
@RestController
public class CenterController {

    @Autowired
    BaseInitializer initializer;

    @RequestMapping(value = "isInitialize")
    public ResponseInfo isInitialize() {
        return ResponseInfo.success(initializer.isInitialized());
    }

    @RequestMapping(value = "awaitInitialize", method = RequestMethod.POST)
    public ResponseInfo awaitInitialize(@RequestBody @Valid InitPrepareData prepareData) throws ResponseException {
        if (!initializer.isInitialized()) {
            initializer.awaitInitialize(prepareData);
        }
        return ResponseInfo.success();
    }
}
