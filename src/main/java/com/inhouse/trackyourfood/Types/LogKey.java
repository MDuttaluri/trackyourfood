package com.inhouse.trackyourfood.Types;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class LogKey implements Serializable {

    long id;

    long userId;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LogKey))
            return false;

        LogKey ob = (LogKey) obj;
        if (this == obj) {
            return true;
        } else if (this.id == ob.id && this.userId == ob.userId) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.userId);
    }

}
