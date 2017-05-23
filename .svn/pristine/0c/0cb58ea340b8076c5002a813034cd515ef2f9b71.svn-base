package ${p.basePackage}.query.${p.bizPackage};
import java.util.Date;
import java.math.BigDecimal;
/**
 * 描述:${p.code_name}</br>
 * 功能：</b>${p.code_name}实体表查询专属实体vo</br>
 * 作者：</b>方坚</br>
 * 时间:${.now}</br>
 */
public class ${p.className}Query implements java.io.Serializable{
 <#list p.gci_columns as u>
 	public static final String ALIAS_${u.clumn_name} = "${u.column_comment}";
 </#list>
<#list p.gci_columns as u>
 	/**
 	* ${u.column_comment}
 	*/
 	<#if u.column_javatype == 'java.util.Date'>
 	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 	</#if>
   	private ${u.column_javatype} ${u.clumn_name};
 </#list>
 
 <#list p.gci_columns as u>
   	public void set${u.column_getset}(${u.column_javatype} ${u.clumn_name}){
   		this.${u.clumn_name} = ${u.clumn_name};
   	}
   	public ${u.column_javatype} get${u.column_getset}() {
		return ${u.clumn_name};
	}
 </#list>	
	
}
