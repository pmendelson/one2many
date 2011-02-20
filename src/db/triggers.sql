create or replace trigger schema_version_history_trg
 before insert on schema_version_history
 referencing old as old new as new
 for each row
begin
 if :new.schema_version_history_id is null then
  select schema_version_history_seq.nextval into :new.schema_version_history_id from dual;
 end if;
end;
/

create or replace trigger user_trg
 before insert on user_t
 referencing old as old new as new
 for each row
begin
 if :new.user_id is null then
  select user_seq.nextval into :new.user_id from dual;
 end if;
end;
/

create or replace trigger location_trg
 before insert on location
 referencing old as old new as new
 for each row
begin
 if :new.location_id is null then
  select location_seq.nextval into :new.location_id from dual;
 end if;
end;
/

create or replace trigger role_trg
 before insert on role
 referencing old as old new as new
 for each row
begin
 if :new.role_id is null then
  select role_seq.nextval into :new.role_id from dual;
 end if;
end;
/

create or replace trigger organization_trg
 before insert on organization
 referencing old as old new as new
 for each row
begin
 if :new.organization_id is null then
  select organization_seq.nextval into :new.organization_id from dual;
 end if;
end;
/

create or replace trigger secret_question_trg
 before insert on secret_question
 referencing old as old new as new
 for each row
begin
 if :new.secret_question_id is null then
  select secret_question_seq.nextval into :new.secret_question_id from dual;
 end if;
end;
/

create or replace trigger user_role_trg
 before insert on user_role
 referencing old as old new as new
 for each row
begin
 if :new.user_role_id is null then
  select user_role_seq.nextval into :new.user_role_id from dual;
 end if;
end;
/

create or replace trigger user_password_idx_trg
 before insert on user_password_idx
 referencing old as old new as new
 for each row
begin
 if :new.user_password_idx_id is null then
  select user_password_idx_seq.nextval into :new.user_password_idx_id from dual;
 end if;
end;
/

create or replace trigger permission_type_trg
 before insert on permission_type
 referencing old as old new as new
 for each row
begin
 if :new.permission_type_id is null then
  select permission_type_seq.nextval into :new.permission_type_id from dual;
 end if;
end;
/

create or replace trigger permission_trg
 before insert on permission
 referencing old as old new as new
 for each row
begin
 if :new.permission_id is null then
  select permission_seq.nextval into :new.permission_id from dual;
 end if;
end;
/

create or replace trigger org_default_role_trg
 before insert on org_default_role
 referencing old as old new as new
 for each row
begin
 if :new.org_default_role_id is null then
  select org_default_role_seq.nextval into :new.org_default_role_id from dual;
 end if;
end;
/

create or replace trigger org_default_permission_trg
 before insert on org_default_permission
 referencing old as old new as new
 for each row
begin
 if :new.org_default_permission_id is null then
  select org_default_permission_seq.nextval into :new.org_default_permission_id from dual;
 end if;
end;
/

create or replace trigger user_permission_trg
 before insert on user_permission
 referencing old as old new as new
 for each row
begin
 if :new.user_permission_id is null then
  select user_permission_seq.nextval into :new.user_permission_id from dual;
 end if;
end;
/

create or replace trigger access_level_trg
 before insert on access_level
 referencing old as old new as new
 for each row
begin
 if :new.access_level_id is null then
  select access_level_seq.nextval into :new.access_level_id from dual;
 end if;
end;
/
