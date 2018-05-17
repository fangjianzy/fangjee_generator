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
	private static final long serialVersionUID = 1L;
<#list p.gci_columns as u>
 	/**
 	* ${u.column_comment}
 	*/
 	<#if u.column_javatype == 'java.util.Date' && u.data_type == 'datetime'>
 	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 	</#if>
 	<#if u.column_javatype == 'java.util.Date' && u.data_type == 'date'>
 	@DateTimeFormat(pattern = "yyyy-MM-dd")
 	</#if>
   	private ${u.column_javatype} ${u.javaColumnFileNameCode};
</#list>
 
 <#list p.gci_columns as u>
   	public void set${u.javaColumnGetSetFileNameCode}(${u.column_javatype} ${u.javaColumnFileNameCode}){
   		this.${u.javaColumnFileNameCode} = ${u.javaColumnFileNameCode};
   	}
   	public ${u.column_javatype} get${u.javaColumnGetSetFileNameCode}() {
		return ${u.javaColumnFileNameCode};
	}
 </#list>
	 
    
   
    
	
}

