create table if not exists organizations(
    id uuid not null primary key,
    organization_name varchar(255) not null
);

create table if not exists employees(
    id uuid not null primary key,
    organization_id uuid not null references organizations(id) on delete cascade,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    job_title varchar(255) not null
);