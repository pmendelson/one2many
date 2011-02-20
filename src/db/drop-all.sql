/**
 * drops all database objects. update this script with every schema update.
 */

drop view  cas_user                       cascade constraints;

drop table user_organization              cascade constraints;
drop table user_role                      cascade constraints;

drop table secret_question                cascade constraints;
drop table organization                   cascade constraints;
drop table role                           cascade constraints;
drop table user_t                         cascade constraints;
drop table user_password_idx              cascade constraints;
drop table schema_version_history         cascade constraints;
drop table location                       cascade constraints;
drop table permission_type                cascade constraints;
drop table permission                     cascade constraints;
drop table org_default_role               cascade constraints;
drop table org_default_permission         cascade constraints;
drop table user_permission                cascade constraints;
drop table restriction_type               cascade constraints;
drop table access_level_type              cascade constraints;
drop table access_level                   cascade constraints;
drop table user_access_level              cascade constraints;
drop table org_default_access_level       cascade constraints;

drop sequence secret_question_seq;
drop sequence organization_seq;
drop sequence role_seq;
drop sequence user_seq;
drop sequence user_role_seq;
drop sequence schema_version_history_seq;
drop sequence user_password_idx_seq;
drop sequence location_seq;
drop sequence permission_type_seq;
drop sequence permission_seq;
drop sequence org_default_role_seq;
drop sequence org_default_permission_seq;
drop sequence user_permission_seq;
drop sequence access_level_type_seq;
drop sequence restriction_type_seq;
drop sequence access_level_seq;

drop trigger location_trg;
drop trigger organization_trg;
drop trigger role_trg;
drop trigger schema_version_history_trg;
drop trigger secret_question_trg;
drop trigger user_password_idx_trg;
drop trigger user_trg;
drop trigger permission_type_trg;
drop trigger permission_trg;
drop trigger org_default_role_trg;
drop trigger org_default_permission_trg;
drop trigger user_permission_trg;
drop trigger access_level_type_trg;
drop trigger restriction_type_trg;
drop trigger access_level_trg;