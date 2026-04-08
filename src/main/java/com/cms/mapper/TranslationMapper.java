package com.cms.mapper;

import com.cms.model.Translation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TranslationMapper {
    Translation findById(@Param("id") Long id);
    Translation findByKeyAndLang(@Param("key") String key, @Param("lang") String lang);
    List<Translation> findByLang(@Param("lang") String lang);
    List<Translation> findAll();
    int insert(Translation translation);
    int update(Translation translation);
    int deleteById(@Param("id") Long id);
    int upsert(Translation translation);
}
