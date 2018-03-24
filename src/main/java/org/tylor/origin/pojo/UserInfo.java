package org.tylor.origin.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author liubin10  2018/3/20
 */
@Entity
@Table(name = "user_info")
@NamedQuery(name = "UserInfo.findAll", query = "SELECT t FROM UserInfo t")
@Data
public class UserInfo implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "USER_INFO_ID_GENERATOR", sequenceName = "USER_INFO_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_INFO_ID_GENERATOR")
    private Long id;

    @Column(name = "username")
    private String username;

    private String password;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    private String remark;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Timestamp updateTime;

/*
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Message> messages;*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}