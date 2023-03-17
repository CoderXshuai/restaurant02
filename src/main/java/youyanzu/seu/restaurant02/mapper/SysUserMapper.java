package youyanzu.seu.restaurant02.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import youyanzu.seu.restaurant02.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author summerWang
 * @since 2023-03-14
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
