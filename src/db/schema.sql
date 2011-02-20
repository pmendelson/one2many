create sequence schema_version_history_seq start with 1;
create table schema_version_history (
    schema_version_history_id integer not null,
    version varchar2(20) not null,
    update_date date not null default sysdate,
    last_step integer not null default 0,
    comments varchar2(500),
    constraint un_svh_version unique (version),
    constraint pk_schema_version_history primary key (schema_version_history_id)
);

insert into schema_version_history values (schema_version_history_seq.nextval, '1.5.3', sysdate, 1, 'schema 1.5.3');

create sequence user_seq start with 1;

create table user_t (
    user_id integer not null,
    username varchar2(50) not null,
    password varchar2(100) not null,
    status varchar2(20) default 'PENDING' not null,
    first_name varchar2(50),
    last_name varchar2(50),
    email varchar2(320),
    business_phone varchar2(50),
    alt_phone varchar2(50),
    fax varchar2(50),
    address1 varchar2(200),
    address2 varchar2(200),
    city varchar2(100),
    state varchar2(2),
    country varchar2(40) default 'United States of America' not null,
    zip varchar2(10),
    secret_question varchar2(500),
    secret_question_answer varchar2(100),
    register_date date,
    terms_accepted_date date,
    last_login_date date,
    password_update_date date,
    first_failed_login_date date,
    failed_login_count integer default 0 not null,
    password_change_required number(1) default 0 not null,
    incomplete number(1) default 0 not null,
    update_email_sent_date timestamp,
    approved_date timestamp,
    authorization_schedule_id number(20),
    constraint un_user_username unique (username),
    constraint pk_user primary key (user_id)
);
comment on column user_t.terms_accepted_date is 'Date when the user accepted the terms and conditions.';
comment on column user_t.last_login_date is 'The last time when the user logged in.';
comment on column user_t.password_update_date is 'The last time when the user updated her password.';
comment on column user_t.first_failed_login_date is 'The time of the first one of successive failed login attempts.';
comment on column user_t.failed_login_count is 'The count of successive failed login attempts.';
comment on column user_t.password_change_required is 'Is the user required to change her password upon next login. 1 for yes, 0 for no.';

create table authorization_schedule (
  authorization_schedule_id number(20) not null,
  status varchar(8),
  version integer not null,
  constraint ck_auth_sched_STATUS check (STATUS in ('PENDING','ACTIVE','LOCKED','INACTIVE')),
  constraint pk_auth_sched primary key (authorization_schedule_id));


create sequence location_seq start with 1;
create table location (
    location_id integer not null,
    state varchar2(2) not null,
    country varchar2(40) not null,
    constraint pk_location primary key (location_id)
);
comment on table location is 'Stores available location information for user registration.';

create sequence role_seq start with 1;
create table role (
    role_id integer not null,
    role_name varchar2(50) not null,
    functional number(1) default 0,
    access_level_app_type_code varchar(10) not null,
    url varchar2(1000),
    display_name varchar2(200),
    display_order integer,
    constraint un_role_role_name unique (role_name),
    constraint pk_role primary key (role_id)
);
comment on column role.functional is 'Whether this role corresponds to a distinctive CLPSS function. 1 for yes, 0 for no.';
comment on column role.url is 'URL to an application page corresponding to this role. May be absolute (i.e. starts with http) or relative. May be null for non-functional roles.';
comment on column role.display_order is 'Position to display this role relative to others. May be null for non-functional roles.';

create sequence organization_seq start with 1;
create table organization (
    organization_id integer not null,
    organization_name varchar2(50) not null,
    region_code varchar2(20),
    lab_code varchar2(20),
    address1 varchar2(200),
    address2 varchar2(200),
    city varchar2(100),
    state varchar2(2),
    zip varchar2(10),
    country varchar2(40) default 'United States of America' not null,
    phone_number varchar2(50),
    alt_phone_number varchar2(50),
    authorization_schedule_id number(20),
    contact_1_name varchar2(200),
    contact_1_phone_number varchar2(50),
    contact_1_email varchar2(320),
    contact_2_name varchar2(200),
    contact_2_phone_number varchar2(50),
    contact_2_email varchar2(320),
    contact_3_name varchar2(200),
    contact_3_phone_number varchar2(50),
    contact_3_email varchar2(320),
    constraint un_org_organization_name unique (organization_name),
    constraint pk_organization primary key (organization_id)
);

create sequence secret_question_seq start with 1;
create table secret_question (
    secret_question_id integer not null,
    value varchar2(1000) not null,
    constraint un_sq_value unique (value),
    constraint pk_secret_question primary key (secret_question_id)
);
comment on table secret_question is 'Lookup table for secret questions';



create sequence user_role_seq start with 1;

create table role_assignment (
  ROLE_ASSIGNMENT_ID number(20,0) not null,
  PARENT_ID number(20,0),
  ROLE_ID number(10,0),
  AUTHORIZATION_SOURCE varchar(6),
  ASSIGNMENT_STATUS varchar(8),
  last_update_date date null,
  last_update_full_name varchar(60) null,
  version  integer not null,
  constraint ck_role_assignment_ASSIGNMENT_STATUS check (ASSIGNMENT_STATUS in ('REJECTED','APPROVED','PENDING')),
  constraint ck_role_assignment_AUTHORIZATION_SOURCE check (AUTHORIZATION_SOURCE in ('SELF','ADMIN','PARENT')),
  constraint pk_role_assignment primary key (ROLE_ASSIGNMENT_ID));
comment on table role_assignment is 'Stores user-role associations';
comment on column role_assignment.AUTHORIZATION_SOURCE is 'Role was requested by user (1=True, 0=False)';
comment on column role_assignment.ASSIGNMENT_STATUS is 'Current status on role (1=Rejected, 3=Approved, 5=Pending)';

create table user_organization (
    user_id integer not null,
    organization_id integer not null,
    constraint fk_user_uo foreign key (user_id) references user_t (user_id) on delete cascade,
    constraint fk_org_uo foreign key (organization_id) references organization (organization_id) on delete cascade
);
comment on table user_organization is 'Stores user-organization associations';

create sequence user_password_idx_seq start with 1;
create table user_password_idx (
    user_password_idx_id integer not null,
    user_id integer not null,
    password varchar2(100) not null,
    idx integer not null,
    constraint fk_user_upi foreign key (user_id) references user_t (user_id) on delete cascade,
    constraint pk_user_password_idx primary key (user_password_idx_id)
);
comment on table user_password_idx is 'Stores maximum six old passwords for users.';



insert into user_password_idx
    select user_password_idx_seq.NEXTVAL, user_id, password, 1 from user_t;
commit;

create sequence permission_type_seq start with 1;
create table permission_type (
  permission_type_id integer not null,
  code varchar(50) not null,
  display_name varchar2(200) not null,
  display_order integer null,
  constraint pk_permission_type primary key (permission_type_id),
  constraint un_permission_type_code unique (code)
  );

create sequence permission_seq start with 1;
create table permission (
  permission_id integer not null,
  role_id integer not null,
  permission_type_id integer not null,
  constraint pk_permission primary key (permission_id),
  constraint fk_role_p foreign key (role_id) references role (role_id),
  constraint fk_permission_type_p foreign key (permission_type_id) references permission_type (permission_type_id),
  constraint un_permission unique (role_id, permission_type_id)
  );

create sequence org_default_role_seq start with 1;
create table org_default_role (
  org_default_role_id integer not null,
  organization_id integer not null,
  role_id integer not null,
  constraint pk_org_default_role primary key (org_default_role_id),
  constraint fk_organization_odr foreign key (organization_id) references organization (organization_id),
  constraint fk_role_odr foreign key (role_id) references role (role_id),
  constraint un_org_default_role unique (organization_id, role_id)
  );

create sequence org_default_permission_seq start with 1;
create table org_default_permission (
  org_default_permission_id integer not null,
  permission_id integer not null,
  org_default_role_id integer not null,
  constraint pk_org_default_permission primary key (org_default_permission_id),
  constraint fk_permission_odp foreign key (permission_id) references permission (permission_id),
  constraint fk_org_default_role_odp foreign key (org_default_role_id) references org_default_role (org_default_role_id),
  constraint un_org_default_permission unique (permission_id, org_default_role_id)
  );

create sequence user_permission_seq start with 1;
create table permission_assignment (
  permission_assignment_id integer not null,
  permission_id integer not null,
  role_assignment_id integer not null,
  AUTHORIZATION_SOURCE varchar(6),
  constraint pk_user_permission primary key (permission_assignment_id),
  constraint fk_permission_up foreign key (permission_id) references permission (permission_id),
  constraint fk_role_assignment_up foreign key (role_assignment_id) references role_assignment (role_assignment_id),
  constraint un_user_permission unique (permission_id, role_assignment_id)
  );

create table access_level_type (
  access_level_type_id integer not null,
  display_name varchar2(200) not null,
  display_order integer null,
  constraint pk_access_level_type primary key (access_level_type_id)
  );

create table restriction_type (
  restriction_type_id integer not null,
  display_name varchar(200) not null,
  display_order integer null,
  constraint pk_restriction_type primary key (restriction_type_id)
  );

create sequence access_level_seq start with 1;
create table access_level (
  access_level_id integer not null,
  access_level_entity_qual integer not null,
  access_level_entity_id number(20,0) null,
  access_level_entity_display varchar(200) null,
  restriction_type_id number(1,0) null,
  parent_urole_id number(20,0) null,
  parent_uperm_id number(20,0) null,
  constraint pk_access_level primary key (access_level_id),
  constraint fk_access_level_type_al foreign key (access_level_entity_qual) references access_level_type (access_level_type_id),
  constraint fk_restriction_type_al foreign key (restriction_type_id) references restriction_type (restriction_type_id)
  );
comment on column access_level.restriction_type_id is 'Additivity of this restriction (1=Include, 0=Exclude)';

create table role_access_level_type (
  role_id integer not null,
  access_level_type_id integer not null,
  constraint pk_role_access_level_type primary key (role_id, access_level_type_id),
  constraint fk_role_ralt foreign key (role_id) references role (role_id),
  constraint fk_access_level_type_ralt foreign key (access_level_type_id) references access_level_type (access_level_type_id)
  );

create table user_access_level (
  user_permission_id integer not null,
  access_level_id integer not null,
  constraint pk_user_access_level primary key (user_permission_id, access_level_id),
  constraint fk_user_permission_ual foreign key (user_permission_id) references permission_assignment (permission_assignment_id),
  constraint fk_access_level_ual foreign key (access_level_id) references access_level (access_level_id)
  );

create table org_default_access_level (
  org_default_permission_id integer not null,
  access_level_id integer not null,
  constraint pk_org_default_access_level primary key (org_default_permission_id, access_level_id),
  constraint fk_org_default_permission_dal foreign key (org_default_permission_id) references org_default_permission (org_default_permission_id),
  constraint fk_access_level_dal foreign key (access_level_id) references access_level (access_level_id)
  );


create or replace view cas_user as
    select user_id, username, password, status, password_update_date
    from user_t;
comment on table cas_user is 'User information required by smoprotal-cas';

commit;