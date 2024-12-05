-- sqlfluff:diarect=duckdb
create table json as 
select unnest(results) as results 
from read_json('response.json');


create table results as
select
  results->'gender'->>'code' as 'gender_code',
  results->'gender'->>'name' as 'gender_name',
  results->'swimming_style'->>'code' as 'swimming_style_code',
  results->'swimming_style'->>'name' as 'swimming_style_name',
  results->'distance'->>'code' as 'distance_code',
  results->'distance'->>'name' as 'distance_name',
  results->>'name_for_relay' as 'name_for_relay',
  results->'class'->>'code' as 'class_code',
  results->'class'->>'name' as 'class_name',
  results->'race_division'->>'code' as 'division_code',
  results->'race_division'->>'name' as 'division_name',
  json_array_length(results->'records') as 'records_length',
  results->'records' as 'records'
from json;


CREATE table master as
SELECT 
  gender_name,
  swimming_style_name,
  rtrim(distance_name,'m')::INT as distance,
  class_name,
  division_name,
  record->'unnest'->'swimmers'->>'swimmer_name' as swimmer_name,
  record->'unnest'->'swimmers'->>'swimmer_code' as swimmer_code,
  record->'unnest'->'swimmers'->'entry_group'->>'code' as entry_group_code,
  record->'unnest'->'swimmers'->'entry_group'->>'name' as entry_group_name,
  record->'unnest'->>'result_time' as result_time,
  record->'unnest'->>'reaction_times' as reaction_times,
  record->'unnest'->'lap_detail' as lap_detail,
  record->'unnest'->'swimmers'->'team_members' as team_members
FROM 
  results,
  UNNEST(from_json(json(records),'["JSON"]')) AS record;



SELECT
  gender_name,
  swimming_style_name,
  distance,
  class_name,
  division_name,
  result_time,
  reaction_times,
  team_members,
  team_members->>'$[0].swimmer_name' as swimmer1_name,
  team_members->>'$[0].swimmer_code' as swimmer1_code,
  team_members->>'$[1].swimmer_name' as swimmer2_name,
  team_members->>'$[1].swimmer_code' as swimmer2_code,
  team_members->>'$[2].swimmer_name' as swimmer3_name,
  team_members->>'$[2].swimmer_code' as swimmer3_code,
  team_members->>'$[3].swimmer_name' as swimmer4_name,
  team_members->>'$[3].swimmer_code' as swimmer4_code
FROM
  master
WHERE
  swimmer_name IS NULL;
