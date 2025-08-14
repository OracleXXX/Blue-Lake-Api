package com.bluelakeapi.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.bluelakeapi.model.BaseDO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public abstract class BaseDAO<M extends BaseMapper<T>, T extends BaseDO> {

    @Autowired
    protected M baseMapper;

    public boolean save(T entity) {
        if (entity.getId() == null) {
            return this.baseMapper.insert(entity) > 0;
        } else {
            return this.baseMapper.updateById(entity) > 0;
        }
    }

    public boolean saveAll(List<T> entities) {
        for (T entity : entities) {
            if (!save(entity)) {
                return false;
            }
        }
        return true;
    }

    public T selectOneById(Long id) {
        return this.baseMapper.selectById(id);
    }

    protected T selectOne(Wrapper<T> queryWrapper) {
        return this.baseMapper.selectOne(queryWrapper);
    }

    protected Integer selectCount(Wrapper<T> queryWrapper) {
        return Math.toIntExact(this.baseMapper.selectCount(queryWrapper));
    }

    protected List<T> selectList(Wrapper<T> wrapper) {
        return this.baseMapper.selectList(wrapper);
    }

    protected PageInfo<T> selectPage(Wrapper<T> wrapper, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<T> list = this.baseMapper.selectList(wrapper);

        return new PageInfo<>(list);
    }

    protected T selectOne(SFunction<T, ?> key, Object val) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(key, val);

        return this.selectOne(queryWrapper);
    }

    protected boolean update(UpdateWrapper<T> updateWrapper, T entity) {
        return this.baseMapper.update(entity, updateWrapper) > 0;
    }

    protected boolean updateByUk(SFunction<T, ?> uk, Object val, T entity) {
        UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().and(o -> o.eq(uk, val));

        return this.baseMapper.update(entity, updateWrapper) > 0;
    }

    protected QueryWrapper<T> newQueryWrapper() {
        return new QueryWrapper<>();
    }

    protected UpdateWrapper<T> newUpdateWrapper() {
        return new UpdateWrapper<>();
    }

    /**
     * 插入（批量）
     *
     * @param entityList 实体对象集合
     */
    @Transactional(rollbackFor = Exception.class)
    protected boolean saveBatch(Collection<T> entityList) {
        return saveBatch(entityList, 1000);
    }

    /**
     * 批量插入
     *
     * @param entityList ignore
     * @param batchSize  ignore
     * @return ignore
     */
    @Transactional(rollbackFor = Exception.class)
    protected boolean saveBatch(Collection<T> entityList, int batchSize) {
        String sqlStatement = sqlStatement(SqlMethod.INSERT_ONE);
        int size = entityList.size();
        executeBatch(sqlSession -> {
            int i = 1;
            for (T entity : entityList) {
                sqlSession.insert(sqlStatement, entity);
                if ((i % batchSize == 0) || i == size) {
                    sqlSession.flushStatements();
                }
                i++;
            }
        });
        return true;
    }

    /**
     * 获取 SqlStatement
     *
     * @param sqlMethod ignore
     * @return ignore
     */
    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(currentModelClass()).getSqlStatement(sqlMethod.getMethod());
    }

    /**
     * 执行批量操作
     *
     * @param fun fun
     * @since 3.3.0
     */
    protected void executeBatch(Consumer<SqlSession> fun) {
        Class<T> tClass = currentModelClass();
        //SqlHelper.clearCache(tClass);
        SqlSessionFactory sqlSessionFactory = SqlHelper.sqlSessionFactory(tClass);
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            fun.accept(sqlSession);
            sqlSession.commit();
        } catch (Throwable t) {
            sqlSession.rollback();
            Throwable unwrapped = ExceptionUtil.unwrapThrowable(t);
            if (unwrapped instanceof RuntimeException) {
                MyBatisExceptionTranslator myBatisExceptionTranslator = new MyBatisExceptionTranslator(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(), true);
                throw Objects.requireNonNull(myBatisExceptionTranslator.translateExceptionIfPossible((RuntimeException) unwrapped));
            }
            throw ExceptionUtils.mpe(unwrapped);
        } finally {
            sqlSession.close();
        }
    }

    protected Class<T> currentModelClass() {
        return null;

    }
}
