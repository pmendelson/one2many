runscript from 'src/db/schema.sql';

insert into role direct select * from csvread('src/db/test_data/role.csv');
insert into permission_type direct select * from csvread('src/db/test_data/permission_type.csv');
insert into permission direct select * from csvread('src/db/test_data/permission.csv');
insert into secret_question direct select * from csvread('src/db/test_data/secret_question.csv');
insert into location direct select * from csvread('src/db/test_data/location.csv');
insert into access_level_type direct select * from csvread('src/db/test_data/access_level_type.csv');
insert into restriction_type direct select * from csvread('src/db/test_data/restriction_type.csv');

insert into organization direct select * from csvread('src/db/test_data/organization.csv');
alter sequence organization_seq restart with (select max(organization_id) + 1 from organization);

insert into org_default_role direct select * from csvread('src/db/test_data/org_default_role.csv');
alter sequence org_default_role_seq restart with (select max(org_default_role_id) + 1 from org_default_role);

insert into user_t direct select * from csvread('src/db/test_data/user_t.csv');
alter sequence user_seq restart with (select max(user_id) + 1 from user_t);

insert into authorization_schedule direct select * from csvread('src/db/test_data/authorization_schedule.csv');

insert into role_assignment direct select * from csvread('src/db/test_data/role_assignment.csv');
alter sequence user_role_seq restart with (select max(role_assignment_id) + 1 from role_assignment);

insert into permission_assignment direct select * from csvread('src/db/test_data/permission_assignment.csv');
alter sequence user_permission_seq restart with (select max(permission_assignment_id) + 1 from permission_assignment);

/*
insert into access_level direct select * from csvread('src/db/test_data/access_level.csv');
alter sequence access_level_seq restart with (select max(access_level_id) + 1 from access_level);

insert into user_organization direct select * from csvread('src/db/test_data/user_organization.csv');

insert into role_access_level_type direct select * from csvread('src/db/test_data/role_access_level_type.csv');
*/