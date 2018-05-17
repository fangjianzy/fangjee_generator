package ${p.basePackage}.service.${p.bizPackage};

import ${p.basePackage}.entity.${p.bizPackage}.${p.className};
import com.fangjian.framework.common.service.BaseMybatisService;
import ${p.basePackage}.query.${p.bizPackage}.${p.className}Query;
import com.fangjian.framework.common.PageInfo;
import java.util.List;
/**
 * 描述:${p.code_name}-服务接口</br>
 * 功能：</b>${p.code_name}接口类</br>
 * 作者：</b>fangjian</br>
 * 时间:${.now}</br>
 */
public interface ${p.className}Service extends BaseMybatisService<${p.className},${p.pk_type}> {
	//分页数据
	List<${p.className}> findPageListBySQL(int currentPage, int pageSize,${p.className}Query vo);
	//分页属性
	PageInfo findPageInfoBySQL(int currentPage, int pageSize, ${p.className}Query vo);
	//pagehelper框架实现的分页  by jimmy.fang
	com.github.pagehelper.PageInfo<${p.className}> getLists(Integer pageNum,Integer pageSize);
}
