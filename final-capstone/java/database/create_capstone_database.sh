#!/bin/bash
BASEDIR=$(dirname $0)
DATABASE=family_reading_tracker
export PGPASSWORD='postgres1'

psql -U postgres -f "$BASEDIR/01_drop_and_create_db.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/create_table_users.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/create_table_prizes.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/create_table_prizes_users.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/04_insert_seed_data.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/05_create_database_users.sql"
