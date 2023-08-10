package com.moka.persnl.mapper;

import com.moka.persnl.dto.MokaPersnlPatchDto;
import com.moka.persnl.dto.MokaPersnlPostDto;
import com.moka.persnl.dto.MokaPersnlResponseDto;
import com.moka.persnl.entity.MokaPersnl;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersnlMapper {
    MokaPersnl mokaPersnlPostDtoToMokaPersnl(MokaPersnlPostDto mokaPersnlPostDto);

    MokaPersnl mokaPersnlPatchDtoToMokaPersnl(MokaPersnlPatchDto mokaPersnlPatchDto);

    MokaPersnlResponseDto mokaPersnlToMokaPersnlResponseDto(MokaPersnl mokaPersnl);
}
