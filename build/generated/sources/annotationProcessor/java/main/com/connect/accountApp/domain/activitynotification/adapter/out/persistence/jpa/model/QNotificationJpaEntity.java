package com.connect.accountApp.domain.activitynotification.adapter.out.persistence.jpa.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNotificationJpaEntity is a Querydsl query type for NotificationJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotificationJpaEntity extends EntityPathBase<NotificationJpaEntity> {

    private static final long serialVersionUID = 937526251L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotificationJpaEntity notificationJpaEntity = new QNotificationJpaEntity("notificationJpaEntity");

    public final com.connect.accountApp.domain.bill.adapter.out.persistence.jpa.model.QBillJpaEntity billJpaEntity;

    public final com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.QExpenseJpaEntity expenseJpaEntity;

    public final EnumPath<com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory> notiCategory = createEnum("notiCategory", com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory.class);

    public final StringPath notiContent = createString("notiContent");

    public final DateTimePath<java.time.LocalDateTime> notiCreatedAt = createDateTime("notiCreatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> notiId = createNumber("notiId", Long.class);

    public final BooleanPath notiIsRead = createBoolean("notiIsRead");

    public final StringPath senderName = createString("senderName");

    public QNotificationJpaEntity(String variable) {
        this(NotificationJpaEntity.class, forVariable(variable), INITS);
    }

    public QNotificationJpaEntity(Path<? extends NotificationJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNotificationJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNotificationJpaEntity(PathMetadata metadata, PathInits inits) {
        this(NotificationJpaEntity.class, metadata, inits);
    }

    public QNotificationJpaEntity(Class<? extends NotificationJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.billJpaEntity = inits.isInitialized("billJpaEntity") ? new com.connect.accountApp.domain.bill.adapter.out.persistence.jpa.model.QBillJpaEntity(forProperty("billJpaEntity"), inits.get("billJpaEntity")) : null;
        this.expenseJpaEntity = inits.isInitialized("expenseJpaEntity") ? new com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.QExpenseJpaEntity(forProperty("expenseJpaEntity"), inits.get("expenseJpaEntity")) : null;
    }

}

