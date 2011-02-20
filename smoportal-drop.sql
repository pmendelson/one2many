SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists Access_Level;

drop table if exists authorization_schedule;

drop table if exists LOCATION;

drop table if exists ORGANIZATION;

drop table if exists PERMISSION;

drop table if exists permission_assignment;

drop table if exists PERMISSION_TYPE;

drop table if exists ROLE;

drop table if exists role_assignment;

drop table if exists USER_T;

drop table if exists USER_PASSWORD_IDX;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists Access_Level_SEQ;

drop sequence if exists authorization_schedule_seq;

drop sequence if exists LOCATION_SEQ;

drop sequence if exists ORGANIZATION_SEQ;

drop sequence if exists USER_PERMISSION_SEQ;

drop sequence if exists ROLE_SEQ;

drop sequence if exists USER_ROLE_SEQ;

drop sequence if exists USER_SEQ;

drop sequence if exists USER_PASSWORD_IDX_SEQ;

