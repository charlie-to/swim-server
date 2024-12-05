-- sqlfluff:dialect=duckdb

SELECT 
  code, name, code, name, code, name, name_for_relay, code, name, code, name, 
  swimmers.swimmer_name, swimmers.swimmer_code, eg.entry_group.code AS entry_group_code, eg.entry_group.name AS entry_group_name
FROM 
  (SELECT *, unnest(results, recursive := TRUE) AS records_data FROM read_json_auto('response.json')) t,
  LATERAL (SELECT json_extract(records, 'swimmers') AS swimmers) s,
  LATERAL (SELECT json_extract(swimmers, 'entry_group') AS entry_group) eg
limit 1;
