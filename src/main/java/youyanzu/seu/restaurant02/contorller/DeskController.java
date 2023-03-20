package youyanzu.seu.restaurant02.contorller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import youyanzu.seu.restaurant02.entity.Desk;
import youyanzu.seu.restaurant02.entity.Result;
import youyanzu.seu.restaurant02.enums.ResultEnum;
import youyanzu.seu.restaurant02.service.IDeskService;
import youyanzu.seu.restaurant02.utils.ResultUtil;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author CoderXshuai
 * @date 2023/3/16 15:04
 */
@RestController
@RequestMapping("/desk")
@CrossOrigin
public class DeskController {
    @Autowired
    IDeskService deskService;

    /**
     * 分页获取餐桌列表
     *
     * @param pageNum  当前页
     * @param pageSize 每页条数
     * @return Result 餐桌列表
     */
    @GetMapping("/list")
    private Result<List<Desk>> getDeskList(int pageNum, int pageSize) {
        Page<Desk> page = new Page<>(pageNum, pageSize);
        IPage<Desk> deskPage = deskService.page(page);
        System.out.println("deskPage.getRecords" + deskPage.getRecords());
        return ResultUtil.success(deskPage.getRecords(), deskPage.getTotal());
    }

    /**
     * 添加餐桌
     *
     * @param desk 餐桌信息
     * @return Result 餐桌编号
     */
    @PostMapping("/add")
    private Result addDesk(Desk desk) {
        desk.setCreateTime(LocalDateTime.now());
        desk.setModifyTime(desk.getCreateTime());
        if (deskService.save(desk)) {
            return ResultUtil.success(desk.getDeskCode());
        }
        //添加失败，桌位编号已存在
        return ResultUtil.error(ResultEnum.DESK_CODE_IS_EXIST);
    }

    /**
     * 修改餐桌信息
     *
     * @param desk 餐桌信息
     * @return Result 餐桌编号
     */
    @PostMapping("/edit")
    private Result editDesk(Desk desk) {
        desk.setModifyTime(LocalDateTime.now());
        System.out.println("LocalDateTime.now()" + LocalDateTime.now());
        if (deskService.updateByCode(desk)) {
            return ResultUtil.success(desk.getDeskCode());
        }
        return ResultUtil.error(ResultEnum.DESK_CODE_NO_EXIST);
    }

    @PostMapping("/del")
    private Result delDesk(Desk desk) {
        if (deskService.removeByMap(Collections.singletonMap("desk_code", desk.getDeskCode()))) {
            return ResultUtil.success();
        }
        return ResultUtil.error(ResultEnum.DESK_CODE_NO_EXIST);
    }
}
