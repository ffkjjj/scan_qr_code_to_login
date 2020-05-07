package cf.zhul.scanqrcodetologin.controller;

import cf.zhul.scanqrcodetologin.common.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @RequestMapping(value = "/hello")
    public Result<?> hello() {
        return Result.of(true, "success");
    }
}
