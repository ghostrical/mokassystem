package com.moka.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @Column(name = "CREATE_PERSNL")
    private String createPersnl;

    @CreatedDate
    @Column(name = "CREATE_DTTM", updatable = false)
    private LocalDateTime createDttm;

    @Column(name = "CREATE_IP")
    private String createIp;

    @Column(name = "UPDATE_PERSNL")
    private String updatePersnl;

    @LastModifiedDate
    @Column(name = "UPDATE_DTTM")
    private LocalDateTime updateDttm;

    @Column(name = "UPDATE_IP")
    private String updateIp;

}
