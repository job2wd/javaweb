package org.javaweb.data.model.entity;

import org.javaweb.common.util.JavaObjectToStringUtils;


public abstract class BaseEntity4IntegerKey extends BaseEntity4Key<Integer> {

  private static final long serialVersionUID = 1328907996659470993L;

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof BaseEntity4IntegerKey) {
      if(obj != null) {
        BaseEntity4IntegerKey o = (BaseEntity4IntegerKey)obj;

        Integer id2 = o.getId();

        if(id2 == null && this.getId() == null) {
          return true;
        } else if(id2 == null || this.getId() == null) {
          return false;
        }

        return id2.intValue() == this.getId().intValue();
      }
    }

    return false;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return JavaObjectToStringUtils.toString(this);
  }

}
