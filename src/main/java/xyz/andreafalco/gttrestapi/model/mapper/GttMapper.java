package xyz.andreafalco.gttrestapi.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import xyz.andreafalco.gttrestapi.model.dto.Line;
import xyz.andreafalco.gttrestapi.model.dto.Stop;
import xyz.andreafalco.gttrestapi.model.dto.Time;
import xyz.andreafalco.gttrestapi.data.entity.GttLine;
import xyz.andreafalco.gttrestapi.data.entity.GttStop;
import xyz.andreafalco.gttrestapi.data.entity.GttTime;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GttMapper {

    @Mapping(target = "id", ignore = true)
    GttStop dtoToEntity(Stop stop);

    @Mapping(target = "id", ignore = true)
    GttLine dtoToEntity(Line line);

    @Mapping(target = "id", ignore = true)
    GttTime dtoToEntity(Time time);

}
