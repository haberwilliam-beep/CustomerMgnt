package com.cms.dao;

import com.cms.model.Translation;

import java.util.List;

public interface TranslationDAO {
    Translation findById(Long id);
    Translation findByKeyAndLang(String key, String lang);
    List<Translation> findByLang(String lang);
    List<Translation> findAll();
    int save(Translation translation);
    int update(Translation translation);
    int delete(Long id);
    int upsert(Translation translation);
}
