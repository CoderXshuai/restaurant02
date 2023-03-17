package youyanzu.seu.restaurant02.service;

import com.baomidou.mybatisplus.extension.service.IService;
import youyanzu.seu.restaurant02.entity.MemberCategory;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author summerWang
 * @since 2023-03-13
 */
public interface IMemberCategoryService extends IService<MemberCategory> {
    int getIdByName(String mcName);

    MemberCategory getById(String id);
}
