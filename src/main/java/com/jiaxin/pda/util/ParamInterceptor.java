package com.jiaxin.pda.util;



import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * 通过拦截<code>StatementHandler</code>的<code>prepare</code>方法，重写sql语句实现物理分页。
 * 老规矩，签名里要拦截的类型只能是接口。
 * @author futeng.xiongzhe
 */

@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class,Object.class}) })
public class ParamInterceptor implements Interceptor {
    private static final Log logger = LogFactory.getLog(ParamInterceptor.class);
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Executor executor = (Executor) invocation.getTarget();
        // 获取第一个参数
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        // 非 insert 语句 不处理
        if (ms.getSqlCommandType() != SqlCommandType.INSERT) {
            return invocation.proceed();
        }
        // mybatis的参数对象
        Object paramObj = invocation.getArgs()[1];
        if (paramObj == null) {
            return invocation.proceed();
        }
        Field[] fields = paramObj.getClass().getDeclaredFields();
        for(Field field:fields){
            if(field.getType().equals(String.class)){
                String fieldName = field.getName();
                PropertyDescriptor ps = BeanUtils.getPropertyDescriptor(paramObj.getClass(),fieldName);
                String originValue = (String) ps.getReadMethod().invoke(paramObj);
                logger.info(originValue);
                String valueWithoutJS = StringEscapeUtils.escapeJavaScript(originValue);
                String valueWithoutHtml = StringEscapeUtils.escapeHtml(valueWithoutJS);
                logger.info(valueWithoutHtml);
                String value = StringEscapeUtils.unescapeJava(valueWithoutHtml);
                ps.getWriteMethod().invoke(paramObj,value);
            }
        }
        return executor.update(ms, paramObj);
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
