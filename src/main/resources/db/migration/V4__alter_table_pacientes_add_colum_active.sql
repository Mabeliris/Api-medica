alter table pacientes add active tinyint;
update pacientes set active = 1;