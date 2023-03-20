package youyanzu.seu.restaurant02.service;

import com.baomidou.mybatisplus.extension.service.IService;
import youyanzu.seu.restaurant02.entity.Desk;

/**
 * @author CoderXshuai
 * @date 2023/3/15 14:13
 */

public interface IDeskService extends IService<Desk> {
    boolean updateByCode(Desk desk);

}
