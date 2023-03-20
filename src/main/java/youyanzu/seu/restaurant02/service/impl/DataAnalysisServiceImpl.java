package youyanzu.seu.restaurant02.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import youyanzu.seu.restaurant02.entity.chart.GoodsSales;
import youyanzu.seu.restaurant02.mapper.DataAnalysisDao;
import youyanzu.seu.restaurant02.service.DataAnalysisService;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * @author CoderXshuai
 * @date 2023/3/20 14:14
 */

public class DataAnalysisServiceImpl implements DataAnalysisService {
    private DataAnalysisDao dataAnalysisDao;

    @Override
    public boolean saveBatch(Collection<GoodsSales> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<GoodsSales> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<GoodsSales> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(GoodsSales entity) {
        return false;
    }

    @Override
    public GoodsSales getOne(Wrapper<GoodsSales> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<GoodsSales> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<GoodsSales> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<GoodsSales> getBaseMapper() {
        return null;
    }
}
