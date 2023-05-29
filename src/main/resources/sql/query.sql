-- Clean requests
truncate table gtt_request;

-- Request -> Stop
select  r.id, r.timestamp, s."number", s."name", s.area, s.accessible
from    gtt_request r
        join gtt_request_stop rs on rs.request_id = r.id
        join gtt_stop s ON s.id = rs.stop_id
order by r.timestamp desc;

-- Request -> Timetables
select  r.id, l.number, l.destination,
        string_agg(
            to_char(t."time", 'HH24:MI')
            ||
            case
                when t.realtime = true then '*'
                else ''
            end
            ||
            case
                when t.accessible = true then ' ♿'
                else ''
            end,
            ' - '
        ) times
from	gtt_request r
        join gtt_request_timetable rtt on rtt.request_id = r.id
        join gtt_timetable tt on tt.id = rtt.timetable_id
        join gtt_line l on l.id = tt.line_id
        join gtt_timetable_time ttt on ttt.timetable_id = tt.id
        join gtt_time t on t.id = ttt.time_id
where   1 = 1
		--and r.id = :requestId
group by r.id, l.number, l.destination
order by l."number";
