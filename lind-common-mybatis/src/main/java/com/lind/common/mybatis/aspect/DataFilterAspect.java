/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.lind.common.mybatis.aspect;

import cn.hutool.core.collection.CollUtil;

import cn.hutool.core.util.StrUtil;
import com.lind.common.mybatis.annotation.DataFilter;
import com.lind.common.mybatis.base.CurrentAuditor;
import com.lind.common.mybatis.interceptor.DataScope;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 数据过滤，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Component
public class DataFilterAspect {

    @Autowired
    CurrentAuditor currentAuditor;

    @Pointcut("@annotation(io.renren.common.annotation.DataFilter)")
    public void dataFilterCut() {

    }

    @Before("dataFilterCut()")
    public void dataFilter(JoinPoint point) {
        Object params = point.getArgs()[0];
        if(params != null && params instanceof Map){


            //如果是超级管理员，则不进行数据过滤
            if(currentAuditor.getSuperAdmin()) {
                return ;
            }

            try {
                //否则进行数据过滤
                Map map = (Map)params;
                String sqlFilter = getSqlFilter(currentAuditor, point);
                map.put("sqlFilter", new DataScope(sqlFilter));
            }catch (Exception e){

            }

            return ;
        }

        throw new RuntimeException("DATA_SCOPE_PARAMS_ERROR");
    }

    /**
     * 获取数据过滤的SQL
     */
    private String getSqlFilter(CurrentAuditor currentAuditor, JoinPoint point) throws Exception {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = point.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        DataFilter dataFilter = method.getAnnotation(DataFilter.class);

        //获取表的别名
        String tableAlias = dataFilter.tableAlias();
        if(StrUtil.isNotBlank(tableAlias)){
            tableAlias +=  ".";
        }

        StringBuilder sqlFilter = new StringBuilder();
        sqlFilter.append(" (");

        //部门ID列表
        List<Long> deptIdList = currentAuditor.getDeptIdList();
        if(CollUtil.isNotEmpty(deptIdList)){
            sqlFilter.append(tableAlias).append(dataFilter.deptId());

            sqlFilter.append(" in(").append(StrUtil.join(",",deptIdList)).append(")");
        }

        //查询本人数据
        if(CollUtil.isNotEmpty(deptIdList)){
            sqlFilter.append(" or ");
        }
        sqlFilter.append(tableAlias).append(dataFilter.userId()).append("=").append(currentAuditor.getUserId());

        sqlFilter.append(")");

        return sqlFilter.toString();
    }
}
