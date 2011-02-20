create table Access_Level (
  Access_Level_ID           bigint not null,
  parent_uperm_id           bigint not null,
  parent_urole_id           bigint,
  restriction_type_id       integer,
  ACCESS_LEVEL_ENTITY_QUAL  integer,
  constraint ck_Access_Level_restriction_type_id check (restriction_type_id in (0,1)),
  constraint ck_Access_Level_ACCESS_LEVEL_ENTITY_QUAL check (ACCESS_LEVEL_ENTITY_QUAL in (0,1,2,3)),
  constraint pk_Access_Level primary key (Access_Level_ID))
;

create table authorization_schedule (
  authorization_schedule_id bigint not null,
  STATUS                    varchar(8),
  version                   integer not null,
  constraint ck_authorization_schedule_STATUS check (STATUS in ('PENDING','ACTIVE','LOCKED','INACTIVE')),
  constraint pk_authorization_schedule primary key (authorization_schedule_id))
;

create table LOCATION (
  LOCATION_ID               bigint not null,
  STATE                     varchar(255),
  COUNTRY                   varchar(255),
  constraint pk_LOCATION primary key (LOCATION_ID))
;

create table ORGANIZATION (
  ORGANIZATION_ID           bigint not null,
  REGION_CODE               varchar(255),
  LAB_CODE                  varchar(255),
  authorization_schedule_id bigint,
  ORGANIZATION_NAME         varchar(255),
  constraint pk_ORGANIZATION primary key (ORGANIZATION_ID))
;

create table PERMISSION (
  permission_id             bigint,
  permission_type_id        bigint,
  ROLE_ID                   bigint)
;

create table permission_assignment (
  PERMISSION_ASSIGNMENT_ID  bigint not null,
  role_assignment_id        bigint,
  PERMISSION_ID             bigint,
  constraint pk_permission_assignment primary key (PERMISSION_ASSIGNMENT_ID))
;

create table PERMISSION_TYPE (
  permission_type_id        bigint,
  CODE                      varchar(255),
  DISPLAY_NAME              varchar(255),
  DISPLAY_ORDER             integer)
;

create table ROLE (
  ROLE_ID                   bigint not null,
  ROLE_NAME                 varchar(255),
  FUNCTIONAL                boolean,
  ACCESS_LEVEL_APP_TYPE_CODE varchar(2),
  URL                       varchar(255),
  DISPLAY_NAME              varchar(255),
  DISPLAY_ORDER             integer,
  constraint ck_ROLE_ACCESS_LEVEL_APP_TYPE_CODE check (ACCESS_LEVEL_APP_TYPE_CODE in ('NA','S','O')),
  constraint pk_ROLE primary key (ROLE_ID))
;

create table role_assignment (
  ROLE_ASSIGNMENT_ID        bigint not null,
  PARENT_ID                 bigint,
  ROLE_ID                   bigint,
  ASSIGNMENT_STATUS         varchar(8),
  AUTHORIZATION_SOURCE      varchar(6),
  LAST_UPDATE_DATE          timestamp,
  LAST_UPDATE_FULL_NAME     varchar(255),
  version                   integer not null,
  constraint ck_role_assignment_ASSIGNMENT_STATUS check (ASSIGNMENT_STATUS in ('REJECTED','APPROVED','PENDING')),
  constraint ck_role_assignment_AUTHORIZATION_SOURCE check (AUTHORIZATION_SOURCE in ('SELF','ADMIN','PARENT')),
  constraint pk_role_assignment primary key (ROLE_ASSIGNMENT_ID))
;

create table USER_T (
  USER_ID                   bigint not null,
  USERNAME                  varchar(255),
  PASSWORD                  varchar(255),
  STATUS                    varchar(8),
  FIRST_NAME                varchar(255),
  LAST_NAME                 varchar(255),
  EMAIL                     varchar(255),
  BUSINESS_PHONE            varchar(255),
  ALT_PHONE                 varchar(255),
  FAX                       varchar(255),
  SECRET_QUESTION           varchar(255),
  SECRET_QUESTION_ANSWER    varchar(255),
  REGISTER_DATE             timestamp,
  TERMS_ACCEPTED_DATE       timestamp,
  LAST_LOGIN_DATE           timestamp,
  PASSWORD_UPDATE_DATE      timestamp,
  FIRST_FAILED_LOGIN_DATE   timestamp,
  UPDATE_EMAIL_SENT_DATE    timestamp,
  APPROVED_DATE             timestamp,
  FAILED_LOGIN_COUNT        integer,
  PASSWORD_CHANGE_REQUIRED  boolean,
  INCOMPLETE                boolean,
  authorization_schedule_id bigint,
  constraint ck_USER_T_STATUS check (STATUS in ('PENDING','ACTIVE','LOCKED','INACTIVE')),
  constraint pk_USER_T primary key (USER_ID))
;

create table USER_PASSWORD_IDX (
  USER_PASSWORD_IDX_ID      bigint not null,
  USER_ID                   bigint,
  PASSWORD                  varchar(255),
  IDX                       integer,
  constraint pk_USER_PASSWORD_IDX primary key (USER_PASSWORD_IDX_ID))
;

create sequence Access_Level_SEQ;

create sequence authorization_schedule_seq;

create sequence LOCATION_SEQ;

create sequence ORGANIZATION_SEQ;

create sequence USER_PERMISSION_SEQ;

create sequence ROLE_SEQ;

create sequence USER_ROLE_SEQ;

create sequence USER_SEQ;

create sequence USER_PASSWORD_IDX_SEQ;

alter table Access_Level add constraint fk_Access_Level_permission_ass_1 foreign key (parent_uperm_id) references permission_assignment (PERMISSION_ASSIGNMENT_ID) on delete restrict on update restrict;
create index ix_Access_Level_permission_ass_1 on Access_Level (parent_uperm_id);
alter table ORGANIZATION add constraint fk_ORGANIZATION_authorizationS_2 foreign key (authorization_schedule_id) references authorization_schedule (authorization_schedule_id) on delete restrict on update restrict;
create index ix_ORGANIZATION_authorizationS_2 on ORGANIZATION (authorization_schedule_id);
alter table PERMISSION add constraint fk_PERMISSION_permissionType_3 foreign key (permission_type_id) references PERMISSION_TYPE (permission_type_id) on delete restrict on update restrict;
create index ix_PERMISSION_permissionType_3 on PERMISSION (permission_type_id);
alter table PERMISSION add constraint fk_PERMISSION_role_4 foreign key (ROLE_ID) references ROLE (ROLE_ID) on delete restrict on update restrict;
create index ix_PERMISSION_role_4 on PERMISSION (ROLE_ID);
alter table permission_assignment add constraint fk_permission_assignment_userR_5 foreign key (role_assignment_id) references role_assignment (ROLE_ASSIGNMENT_ID) on delete restrict on update restrict;
create index ix_permission_assignment_userR_5 on permission_assignment (role_assignment_id);
alter table permission_assignment add constraint fk_permission_assignment_permi_6 foreign key (PERMISSION_ID) references PERMISSION (PERMISSION_ID) on delete restrict on update restrict;
create index ix_permission_assignment_permi_6 on permission_assignment (PERMISSION_ID);
alter table role_assignment add constraint fk_role_assignment_car_7 foreign key (PARENT_ID) references authorization_schedule (authorization_schedule_id) on delete restrict on update restrict;
create index ix_role_assignment_car_7 on role_assignment (PARENT_ID);
alter table role_assignment add constraint fk_role_assignment_role_8 foreign key (ROLE_ID) references ROLE (ROLE_ID) on delete restrict on update restrict;
create index ix_role_assignment_role_8 on role_assignment (ROLE_ID);
alter table USER_T add constraint fk_USER_T_organization_9 foreign key (USER_ID) references ORGANIZATION (ORGANIZATION_ID) on delete restrict on update restrict;
create index ix_USER_T_organization_9 on USER_T (USER_ID);
alter table USER_T add constraint fk_USER_T_authorizationSchedu_10 foreign key (authorization_schedule_id) references authorization_schedule (authorization_schedule_id) on delete restrict on update restrict;
create index ix_USER_T_authorizationSchedu_10 on USER_T (authorization_schedule_id);
alter table USER_PASSWORD_IDX add constraint fk_USER_PASSWORD_IDX_user_11 foreign key (USER_ID) references USER_T (USER_ID) on delete restrict on update restrict;
create index ix_USER_PASSWORD_IDX_user_11 on USER_PASSWORD_IDX (USER_ID);


