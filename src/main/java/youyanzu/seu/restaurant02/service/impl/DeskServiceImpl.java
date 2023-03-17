package youyanzu.seu.restaurant02.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import youyanzu.seu.restaurant02.entity.Desk;
import youyanzu.seu.restaurant02.mapper.DeskMapper;
import youyanzu.seu.restaurant02.service.DeskService;

/**
 * @author CoderXshuai
 * @date 2023/3/16 21:01
 */
@Service
public class DeskServiceImpl extends ServiceImpl<DeskMapper, Desk> implements DeskService {
    @Autowired
    DeskMapper deskMapper;
}
