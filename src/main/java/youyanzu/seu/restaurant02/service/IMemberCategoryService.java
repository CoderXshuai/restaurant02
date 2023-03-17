package youyanzu.seu.restaurant02.service;

import youyanzu.seu.restaurant02.entity.MemberCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author summerWang
 * @since 2023-03-13
 */
public interface IMemberCategoryService extends IService<MemberCategory> {
    int getIdByName(String mcName);
    MemberCategory getById(String id);
}
