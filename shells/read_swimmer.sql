-- sqlfluff:dialect=duckdb
create table swimmer as 
select * from (
select unnest(records,recursive:=true) from (
  select unnest(results,recursive:=true) as results from read_json_auto("response.json")
));

select * from swimmer;
