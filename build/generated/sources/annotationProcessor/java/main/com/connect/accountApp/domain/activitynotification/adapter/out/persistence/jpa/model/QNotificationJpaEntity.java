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

    private static final long serialVersionUID = -1398043014L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotificationJpaEntity notificationJpaEntity = new QNotificationJpaEntity("notificationJpaEntity");

    public final EnumPath<com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory> activityNotificationCategory = createEnum("activityNotificationCategory", com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory.class);

    public final NumberPath<Long> activityNotificationId = createNumber("activityNotificationId", Long.class);

    public final com.connect.accountApp.domain.bill.adapter.out.persistence.jpa.model.QBillJpaEntity billJpaEntity;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final BooleanPath isRead = createBoolean("isRead");

    public final StringPath message = createString("message");

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.QUserJpaEntity requesterJpaEntity;

    public final StringPath title = createString("title");

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
        this.requesterJpaEntity = inits.isInitialized("requesterJpaEntity") ? new com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.QUserJpaEntity(forProperty("requesterJpaEntity"), inits.get("requesterJpaEntity")) : null;
    }

}

