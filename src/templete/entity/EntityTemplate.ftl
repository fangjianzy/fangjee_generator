package ${p.basePackage}.entity.${p.bizPackage};
import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 
 * 描述:${p.code_name}</br>
 * 功能：${p.code_name}实体表实体po</br>
 * 作者：fangjian</br>
 * 时间:${.now}</br>
 */
public class ${p.className} implements java.io.Serializable{
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

