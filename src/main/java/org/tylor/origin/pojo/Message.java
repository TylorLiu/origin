//package org.tylor.origin.pojo;
//
//import java.io.Serializable;
//import java.sql.Timestamp;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQuery;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//import lombok.Data;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
///**
// * @author liubin10  2018/3/20
// */
//@Entity
//@Table(name = "message")
//@NamedQuery(name = "Message.findAll", query = "SELECT t FROM Message t")
//@Data
//public class Message implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @SequenceGenerator(name = "USER_INFO_ID_GENERATOR", sequenceName = "USER_INFO_ID_SEQ", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_INFO_ID_GENERATOR")
//    private Long id;
//
//    @CreationTimestamp
//    @Column(name = "create_time")
//    private Timestamp createTime;
//
//    private String content;
//
//    @UpdateTimestamp
//    @Column(name = "update_time")
//    private Timestamp updateTime;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private UserInfo user;
//
//}