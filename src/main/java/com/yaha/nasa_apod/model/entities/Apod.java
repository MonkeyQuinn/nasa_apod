package com.yaha.nasa_apod.model.entities;

import com.yaha.nasa_apod.model.common.BasicTimedEntityLarge;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Apod extends BasicTimedEntityLarge {

    @Column(unique = true)
    private LocalDate date;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    private String hdurl;
    private String mediaType;
    private String serviceVersion;
    private String title;
    private String url;
    private String copyright;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Apod apod = (Apod) o;
        return getId() != null && Objects.equals(getId(), apod.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

}
