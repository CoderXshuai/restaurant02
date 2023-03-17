package youyanzu.seu.restaurant02.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import youyanzu.seu.restaurant02.entity.MemberCategory;
import youyanzu.seu.restaurant02.mapper.MemberCategoryMapper;
import youyanzu.seu.restaurant02.service.IMemberCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author summerWang
 * @since 2023-03-13
 */
@Service
public class MemberCategoryServiceImpl extends ServiceImpl<MemberCategoryMapper, MemberCategory> implements IMemberCategoryService {
    @Autowired
    private MemberCategoryMapper mapper;
    @Override
    public int getIdByName(String mcName) {
        QueryWrapper<MemberCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("mc_name", mcName);
        MemberCategory memberCategory = mapper.selectOne(wrapper);
        return memberCategory.getMcId();
    }

    @Override
    public MemberCategory getById(String id) {
        QueryWrapper<MemberCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("mc_id", id);
        MemberCategory category = null;
        category = mapper.selectOne(wrapper);
        return category;
    }
}
