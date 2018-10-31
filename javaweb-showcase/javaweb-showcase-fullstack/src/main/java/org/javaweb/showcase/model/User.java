package org.javaweb.showcase.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.javaweb.showcase.util.ObjectToStringUtils;


/**
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2015-3-5 上午9:53:40$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
@Entity
@Table(name="user_info")
@org.hibernate.annotations.Cache(usage =org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)// 使用 hibernate 二级缓存
public class User extends BaseEntity {

  private static final long serialVersionUID = -7584937486245495898L;
  
  @Id
  //@GeneratedValue(strategy=GenerationType.AUTO)
  //@SequenceGenerator(name="EMP_SEQ", allocationSize=25)
  @Column(name = "id")
  private Integer id;
  
  @NotBlank(message = "标题不能为空")
  @Length(min = 3, max = 30, message = "标题长度必须介于 3 个字符到 30 个字符之间")
  private String name;
  
  @NotNull
  @Min(value = 0, message = "{valid.filed.org.javaweb.showcase.web.model.User.age.min}")
  @Max(value = 120, message = "{valid.filed.org.javaweb.showcase.web.model.User.age.max}")
  private Integer age;
  
  //@NotNull
  //@Future
  //@javax.validation.constraints.Past
  //@org.springframework.format.annotation.DateTimeFormat(iso=ISO.DATE, pattern="yyyy-MM-dd HH:mm:ss")
  private Date createDate;
  
  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  @Override
  public String toString() {
    return ObjectToStringUtils.toString(this);
  }

}
