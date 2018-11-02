package edu.fzu.lbs.controller.pub;

import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.util.HttpGetUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "尾号限行", description = "尾号限行接口，数据来源聚合数据")
@RestController
@RequestMapping("/limit")
public class LimitController {

    private static final String APP_KEY = "a0116011159c5621cbe483d2b0fd5568";

    @ApiOperation(value = "城市列表", notes = "获取支持的城市列表")
    @GetMapping("/city")
    public ResultDTO city() throws Exception {
        HttpGetUtil httpGetUtil = new HttpGetUtil("http://v.juhe.cn/xianxing/citys")
                .addParam("key", APP_KEY);

        //TODO:状态码
        return new ResultDTO(httpGetUtil.get().getResult());
    }

    @ApiOperation(value = "尾号限行", notes = "查询指定城市今天、明天、后天的尾号限行情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "city", value = "城市", example = "beijing", required = true),
            @ApiImplicitParam(name = "type", value = "类型，1:今日 2:明天 3:后天 4:第4天 5:第5天 6:第6天", defaultValue = "1")
    })
    @GetMapping
    public ResultDTO get(@RequestParam String city,
                         Integer type) throws Exception {
        type = type == null ? 1 : type;
        HttpGetUtil httpGetUtil = new HttpGetUtil("http://v.juhe.cn/xianxing/index")
                .addParam("key", APP_KEY)
                .addParam("city", city)
                .addParam("type", type.toString());
        //TODO:状态码
        return new ResultDTO(httpGetUtil.get().getResult());
    }

}
