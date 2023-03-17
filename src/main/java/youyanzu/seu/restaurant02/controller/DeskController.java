package youyanzu.seu.restaurant02.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import youyanzu.seu.restaurant02.entity.Desk;
import youyanzu.seu.restaurant02.entity.Result;
import youyanzu.seu.restaurant02.service.DeskService;
import youyanzu.seu.restaurant02.utils.ResultUtil;

import java.util.List;

/**
 * @author CoderXshuai
 * @date 2023/3/16 15:04
 */
@Controller
@RequestMapping("/desk")
@CrossOrigin
public class DeskController {
    @Autowired
    DeskService deskService;

    /**
     * 分页获取餐桌列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    private Result<List<Desk>> getDeskList(int pageNum, int pageSize) {
        Page<Desk> page = Page.of(pageNum, pageSize);
        Page<Desk> deskPage = deskService.page(page);
        return ResultUtil.success(deskPage.getRecords(), (int) deskPage.getTotal());
    }
}
